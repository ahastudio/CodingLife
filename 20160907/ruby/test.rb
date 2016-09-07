require 'test/unit'

puts "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" * 3

class Array
  def sum(&block)
    map(&block).reduce(&:+)
  end
end

class Cutter
  def initialize(count)
    @count = count
  end

  def cut(sticks)
    sorted_sticks = sticks.sort.reverse
    sorted_sticks[0...@count]
      .sum { |n| n == 1 ? [n] : [n / 2 + n % 2, n / 2] } +
      (sorted_sticks[@count..-1] || [])
  end

  def cutting_count(n)
    def recursive(sticks, count)
      return count if sticks.all? { |i| i == 1 }
      recursive(cut(sticks), count + 1)
    end
    recursive([n], 0)
  end
end

class CutTest < Test::Unit::TestCase
  test 'sample' do
    cutter = Cutter.new(3)
    assert_equal [4, 3], cutter.cut([7])
    assert_equal [4, 4], cutter.cut([8])
    assert_equal [2, 2, 2, 2], cutter.cut([4, 4])
    assert_equal [1, 1, 1, 1, 1, 1, 2], cutter.cut([2, 2, 2, 2])
    assert_equal [1, 1, 1, 1, 1, 1, 1, 1], cutter.cut([1, 1, 1, 1, 1, 1, 2])

    assert_equal 4, Cutter.new(3).cutting_count(8)
  end
end

def main
  puts '>>>>>>>>>>>>>>>>>>>>>>>>>>>> solve <<<<<<<<<<<<<<<<<<<<<<<<<<'
  puts Cutter.new(3).cutting_count(20)
  puts Cutter.new(5).cutting_count(100)
  puts '>>>>>>>>>>>>>>>>>>>>>>>>>>>>  end  <<<<<<<<<<<<<<<<<<<<<<<<<<'
end

main
