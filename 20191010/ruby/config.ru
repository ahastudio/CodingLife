# frozen_string_literal: true

require 'opal'

map '/static' do
  run Rack::File.new('static')
end

Opal.append_path 'app'

run(Opal::SimpleServer.new { |s| s.index_path = 'index.html' })
