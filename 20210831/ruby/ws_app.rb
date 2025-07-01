# frozen_string_literal: true

require 'sinatra'
require 'sinatra/cors'

require 'faye/websocket'

require_relative './models/ticket'

require_relative './graphql/schema'

require_relative './utils/pubsub'

# WebSocketApp class to handle WebSocket connections
class WebSocketApp
  KEEPALIVE_TIME = 15

  def call(env)
    unless Faye::WebSocket.websocket?(env)
      # If the request is not a WebSocket request, return an error response
      return [426, { 'Content-Type' => 'text/plain' }, ['Expected WebSocket']]
    end

    ws = Faye::WebSocket.new(env, ['graphql-transport-ws'])

    ws.on :open do |_event|
      puts '⭐️ WebSocket connection opened'
    end

    ws.on :message do |event|
      puts "⭐️ WebSocket message received: #{event.data}"

      data = JSON.parse(event.data)
      type = data['type']
      puts "✉️ Received message type: #{type}"

      case type
      when 'connection_init'
        ws.send({ type: 'connection_ack' }.to_json)
      when 'subscribe'
        id = data['id']
        query = data['payload']['query']
        topic = query.match(/subscription\s*{\s*(\w+)/i)&.captures&.first
        puts "✉️ Subscribing to topic: #{topic}"
        SimplePubSub.instance.subscribe(topic, id, ws)
      when 'unsubscribe'
        query = data['payload']['query']
        topic = query.match(/subscription\s*{\s*(\w+)/i)&.captures&.first
        puts "✉️ Unsubscribing from topic: #{topic}"
        SimplePubSub.instance.unsubscribe(topic, ws)
      end
    end

    ws.on :close do |_event|
      puts '⭐️ WebSocket connection closed'
      SimplePubSub.instance.remove(ws)
    end

    puts '⭐️ WebSocket connection established'
    ws.rack_response
  end
end
