# frozen_string_literal: true

require 'sinatra/base'
require 'net/http'
require 'active_support/all'
require 'rufus-scheduler'

URL = 'http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1168064000'

def max_pop
  rss = Net::HTTP.get(URI(URL))
  data = Hash.from_xml(rss).with_indifferent_access
  items = data.dig(:rss, :channel, :item, :description, :body, :data)
  items[0, 3].pluck(:pop).map(&:to_i).max
end

# LINE Messenger Bot.
class LineBot < Sinatra::Base
  configure do
    set :bind, '0.0.0.0'
    set :port, 3000
  end

  before do
    scheduler.every '3s' do
      push_messages
    end

    Thread.start do
      scheduler.join
    end
  end

  def scheduler
    @scheduler ||= Rufus::Scheduler.new
  end

  def user_ids
    @user_ids ||= []
  end

  def add_user(user_id)
    return if user_ids.include?(user_id)

    user_ids << user_id
  end

  def remove_user(user_id)
    user_ids.delete(user_id)
  end

  def push_message(user_id, text)
    response = Net::HTTP.post(
      URI('https://api.line.me/v2/bot/message/push'),
      {
        to: user_id,
        messages: [{ type: 'text', text: text }]
      }.to_json,
      "Content-Type": 'application/json',
      'Authorization': "Bearer #{ENV['CHANNEL_ACCESS_TOKEN']}"
    )
    throw 'error' unless response.is_a?(Net::HTTPSuccess)
  end

  def push_messages
    pop = max_pop
    message = "강수 확률: #{pop}%" + (pop >= 40 ? "\n우산을 챙기세요!" : '')
    user_ids.each do |user_id|
      push_message(user_id, message)
    rescue StandardError
      puts 'Error!'
      remove_user(user_id)
    end
  end

  post '/webhook' do
    data = JSON.parse(request.body.read)
    data['events'].each do |event|
      next unless event['source']['type'] == 'user'

      user_id = event['source']['userId']
      add_user(user_id)
    end
    'ok'
  end
end

LineBot.run!
