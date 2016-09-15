require 'test/unit'

module Enumerable
  def sum(&block)
    map(&block).reduce(&:+)
  end
end

COINS = [500, 100, 50, 10]

def next_coin(coin)
  COINS[COINS.index(coin) + 1]
end

def cases_count(amount, coin = COINS.first)
  return 1 if next_coin(coin).nil?
  return cases_count(amount, next_coin(coin)) if amount < coin
  (0..amount / coin).sum do |i|
    cases_count(amount - coin * i, next_coin(coin))
  end
end

puts "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" * 3

class CoinsTest < Test::Unit::TestCase
  test 'simple' do
    assert_equal 1, cases_count(10)
    assert_equal 1, cases_count(30)
    assert_equal 2, cases_count(50)
    assert_equal 2, cases_count(70)
    assert_equal 4, cases_count(100)
    assert_equal 4, cases_count(120)
    assert_equal 6, cases_count(150)
    assert_equal 9, cases_count(200)
    assert_equal 158, cases_count(1000)
    # WTF? 책에선 동전 갯수를 최대 15개로 제약해서 풀고 있다.
    # 문제에선 분명히 “모든 동전은 충분한 개수가 마련되어 있다고 가정”이라면서?
  end

  test 'sum' do
    assert_equal 55 * 2, (1..10).sum { |i| i * 2 }
  end
end
