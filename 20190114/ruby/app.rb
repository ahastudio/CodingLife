# frozen_string_literal: true

require 'sinatra'
require 'net/http'

set :bind, '0.0.0.0'
set :port, 3000

WORDS = %w[Hi Hello Bye Oh Yes No lol ...].freeze

post '/webhook' do
  data = JSON.parse(request.body.read)
  data['events'].each do |event|
    next unless event['source']['type'] == 'user'

    user_id = event['source']['userId']
    message = WORDS.sample
    push_message(user_id, message)
  end
  'ok'
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
  puts response.inspect
end
