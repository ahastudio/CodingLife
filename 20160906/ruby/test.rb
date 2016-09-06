require 'test/unit'

class Cards
  def initialize(size)
    @cards = [false] * size
  end

  def size
    @cards.size
  end

  def flip?(position)
    @cards[position - 1]
  end

  def flip(position)
    index = position - 1
    @cards[index] = !@cards[index]
  end

  def flip_cards(n)
    (1..size).select { |i| i % n == 0 }.each { |i| flip(i) }
  end

  def hidden?(position)
    !flip?(position)
  end

  def hidden_cards
    (1..@cards.size).select { |i| hidden?(i) }
  end
end

puts '**************************************************'

class FlipCardsTest < Test::Unit::TestCase
  def setup
    @cards = Cards.new(100)
  end

  test 'flip a card' do
    assert_false @cards.flip?(2)

    @cards.flip(2)
    assert @cards.flip?(2)

    @cards.flip(2)
    assert_false @cards.flip?(2)

    @cards.flip(1)
    assert_false @cards.flip?(2)
  end

  test 'flip cards' do
    @cards.flip_cards(2)

    [2, 4, 6, 8, 10, 12, 98, 100].each do |i|
      assert @cards.flip?(i)
    end

    [1, 3, 5, 7, 9, 97, 99].each do |i|
      assert_false @cards.flip?(i)
    end
  end

  test 'sample' do
    @cards.flip_cards(2)
    assert_equal [1, 3, 5, 7, 9], @cards.hidden_cards.select { |i| i < 10 }

    @cards.flip_cards(3)
    assert_equal [1, 5, 6, 7], @cards.hidden_cards.select { |i| i < 10 }

    @cards.flip_cards(4)
    assert_equal [1, 4, 5, 6, 7, 8], @cards.hidden_cards.select { |i| i < 10 }
  end
end

def main
  puts '>>>>>>>>>>>>>>>>>>>>> solve <<<<<<<<<<<<<<<<<<<<<<<<'
  cards = Cards.new(100)
  (2..100).each { |i| cards.flip_cards(i) }
  puts cards.hidden_cards
  puts '>>>>>>>>>>>>>>>>>>>>>  end  <<<<<<<<<<<<<<<<<<<<<<<<'
end

main
