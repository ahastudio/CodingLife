# frozen_string_literal: true

require './app'
require './ws_app'

map '/subscriptions' do
  run WebSocketApp.new
end

run Sinatra::Application
