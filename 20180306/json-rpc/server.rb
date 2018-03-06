# frozen_string_literal: true

require 'sinatra'

set :port, 8321

class Service
  NAMES = %i[hello].freeze

  def call(name, params)
    return { error: 'method_not_found' } unless NAMES.include?(name)
    args = params.each_with_object({}) { |i, o| o[i[0].to_sym] = i[1] }
    { result: send(name, args) }
  end

  def hello(name:)
    "Hello, #{name}"
  end
end

service = Service.new

post '/' do
  body = request.body.read
  return if body.empty?
  data = JSON.parse(body)
  puts data.inspect
  service.call(data['method'].to_sym, data['params']).merge(
    jsonrpc: '2.0',
    id: data['id']
  ).to_json
end
