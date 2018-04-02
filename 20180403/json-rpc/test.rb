# https://en.wikipedia.org/wiki/JSON-RPC
# https://github.com/fxposter/jsonrpc-client

require 'jsonrpc-client'

client = JSONRPC::Client.new('http://localhost:8123')
result = client.invoke('echo', message: 'Hello, world!')
puts result.inspect
