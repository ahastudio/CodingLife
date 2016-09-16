require 'test/unit'
require 'date'

def reverse_date(date)
  date.to_i.to_s(2).reverse.to_i(2).to_s
end

def reverse_date?(date)
  reverse_date(date) == date.to_s
end

class DateTest < Test::Unit::TestCase
  test 'reverse' do
    assert_equal '19660713', reverse_date('19660713')
    assert reverse_date?('19660713')
  end
end

def main
  puts '-=-=-=-=-=-=-=-=-=-=-'
  puts (Date.new(1964, 10, 10)..Date.new(2020, 7, 24))
    .map { |i| i.strftime('%Y%m%d') }
    .select { |i| reverse_date?(i) }
end

main
