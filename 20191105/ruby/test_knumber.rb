# frozen_string_literal: true

require 'minitest/autorun'

def knumbers(numbers, commands)
  commands.map { |b, e, k| numbers[b - 1...e].sort[k - 1] }
end

# Test
class KNumberTest < MiniTest::Unit::TestCase
  def test_sample
    assert_equal [5, 6, 3],
                 knumbers([1, 5, 2, 6, 3, 7, 4],
                          [[2, 5, 3], [4, 4, 1], [1, 7, 3]])
  end
end
