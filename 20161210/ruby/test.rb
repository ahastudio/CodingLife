require 'test/unit'

class Array
  def sum
    reduce(&:+)
  end

  def plus(other)
    zip(other).map(&:sum)
  end
end

class Robot
  DIRECTIONS = [[0, 1], [0, -1], [1, 0], [-1, 0]]

  def path_count(steps)
    return 4 if steps == 1
    path = [[0, 0], [0, 1]]
    step(path, steps - 1) * 4
  end

  def step(path, steps)
    return 1 if steps.zero?
    DIRECTIONS.map { |direction|
      position = path.last.plus(direction)
      next if path.include?(position)
      step(path + [position], steps - 1)
    }.compact.sum
  end
end

class RobotCleanerTest < Test::Unit::TestCase
  test 'sample' do
    assert_equal 4, Robot.new.path_count(1)
    assert_equal 12, Robot.new.path_count(2)
    assert_equal 36, Robot.new.path_count(3)
    assert_equal 324_932, Robot.new.path_count(12)
  end
end

puts '---------------------------------------------------------'
