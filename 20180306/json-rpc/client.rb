# frozen_string_literal: true

require 'net/http'
require 'json'

class JsonRpcError < RuntimeError
end

class MyRPC
  REQUEST_URL = 'http://localhost:8321'
  NAMES = %i[hello say].freeze
  PARAMS = {
    'hello': %i[name],
    'say': %i[message]
  }.freeze

  def method_missing(name, *args)
    return super unless rpc_method?(name)
    params = PARAMS[name].zip(args).to_h
    response = http_request(jsonrpc: '2.0', id: '1004',
                            method: name, params: params)
    raise JsonRpcError, response['error'] if response['error']
    response['result']
  end

  def respond_to_missing?(name, include_all)
    rpc_method?(name) || super
  end

  private

  def rpc_method?(name)
    NAMES.include?(name)
  end

  def http_request(body)
    uri = URI(REQUEST_URL)
    response = Net::HTTP.start(uri.host, uri.port) do |http|
      http.post('/', body.to_json)
    end
    JSON.parse(response.body)
  end
end

class App
  def main
    rpc = MyRPC.new
    puts rpc.respond_to?(:hello)
    puts rpc.respond_to?(:say)
    puts rpc.respond_to?(:jump)
    puts '---'
    puts rpc.hello('world')
    puts rpc.say('good-bye')
  rescue StandardError => e
    puts "[ERROR] #{e.inspect}"
  end
end

App.new.main
