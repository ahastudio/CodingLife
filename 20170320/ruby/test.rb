require 'test/unit'

class Dollar
  attr_reader :amount

  def initialize(amount)
    @amount = amount
  end

  def *(multiplier)
    Dollar.new(@amount * multiplier)
  end

  def ==(other)
    @amount == other.amount
  end
end

class MoneyTest < Test::Unit::TestCase
  def test_multiplication
    assert_equal Dollar.new(10), Dollar.new(5) * 2
  end
end
