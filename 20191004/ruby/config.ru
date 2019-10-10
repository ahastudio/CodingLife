# frozen_string_literal: true

require 'opal'

map '/static' do
  run Rack::File.new('static')
end

Opal.append_path 'app'

run(Opal::SimpleServer.new do |s|
  s.main = 'application'
  s.index_path = 'index.html.erb'
end)
