#!/usr/bin/env ruby

lib_dir = File.join(__dir__, 'lib')
$LOAD_PATH.unshift(lib_dir) unless $LOAD_PATH.include?(lib_dir)

require 'chat_services_pb'

class ChatRoomServer < Chat::ChatRoom::Service
  def say(say_req, _call)
    puts say_req.text
    Chat::SayReply.new(error: 0)
  end
end

class App
  def main
    addr = '0.0.0.0:5005'
    s = GRPC::RpcServer.new
    s.add_http2_port(addr, :this_port_is_insecure)
    puts "... running insecurely on #{addr}"
    s.handle(ChatRoomServer.new)
    s.run_till_terminated
  end
end

App.new.main
