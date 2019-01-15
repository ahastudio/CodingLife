# frozen_string_literal: true

@messages = {}

def messages(name)
  @messages[name] || []
end

def init
  @names = []
  @message = nil
end

def put_messages
  return if @message.nil?

  @names.each do |name|
    @messages[name] ||= []
    @messages[name] << @message
  end
end

def send_message
  init
  yield
  put_messages
end

def to(name)
  @names ||= []
  @names << name
end

def text(message)
  @message = message
end

def assert_equal(expected, actual)
  return if expected == actual

  puts '-- not equal --'
  puts "Expected: #{expected.inspect}"
  puts "Actual  : #{actual.inspect}"

  raise 'error!'
end

send_message do
  to 'JOKER'
  to 'Ashal'
  text 'Hi!'
end

send_message do
  text 'Hello!'
  to 'Ashal'
end

send_message do
  to 'Ashal'
end

assert_equal ['Hi!'], messages('JOKER')
assert_equal ['Hi!', 'Hello!'], messages('Ashal')
assert_equal [], messages('DongWon')

puts 'ok!'
