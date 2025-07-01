# frozen_string_literal: true

require 'graphql'
require 'singleton'

# SimplePubSub class to manage subscriptions and broadcasting messages
class SimplePubSub
  include Singleton

  def initialize
    @subscriptions = {}
  end

  def subscribe(topic, id, websocket)
    @subscriptions[topic] ||= []
    @subscriptions[topic] << [id, websocket]
  end

  def unsubscribe(topic, websocket)
    return unless @subscriptions[topic]

    @subscriptions[topic].delete_if { |_, client_ws| client_ws == websocket }
    @subscriptions.delete(topic) if @subscriptions[topic].empty?
  end

  def remove(websocket)
    @subscriptions.each_key do |topic|
      unsubscribe(topic, websocket)
    end
  end

  def broadcast(topic, data)
    return unless @subscriptions[topic]

    @subscriptions[topic].each do |id, ws|
      ws.send({ type: 'next', id: id, payload: { data: data } }.to_json)
    end
  end
end
