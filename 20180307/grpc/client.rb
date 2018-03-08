#!/usr/bin/env ruby

lib_dir = File.join(__dir__, 'lib')
$LOAD_PATH.unshift(lib_dir) unless $LOAD_PATH.include?(lib_dir)

require 'chat_services_pb'

class App
  def initialize
    @chat_room = Chat::ChatRoom::Stub.new(
      'localhost:5005', :this_channel_is_insecure
    )
  end

  def main
    5.times do |i|
      say("Hi! [#{i + 1}]")
      sleep(1)
    end
  end

  def say(text)
    reply = @chat_room.say(Chat::SayRequest.new(text: text))
    puts "Error - #{reply.error}" unless reply.error.zero?
  rescue GRPC::Unavailable
    puts 'gRPC unavailable!'
  end
end

App.new.main
