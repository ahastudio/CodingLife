require 'matrix'

UNIT_PRICE = 8
DISCOUNT = [0, 0, 0.05, 0.10, 0.20, 0.25]
PACKAGES = {}
PRICES = {}

class Array
  def sum
    reduce { |a, b| a + b }
  end

  def combinations
    (1..size).map { |i| combination(i).to_a }.sum
  end
end

def min_price(*numbers)
  books = numbers.select { |i| i > 0 }
  if books.all? { |i| i == 1 }
    UNIT_PRICE * books.size * (1 - DISCOUNT[books.size])
  else
    price(*numbers)
  end
end

def packages(numbers)
  indices = numbers.map.with_index { |x, i| x >= 1 ? i : nil }.compact
  indices.combinations.map do |i|
    i.reduce([0] * numbers.size) { |a, b| a[b] = 1; a }
  end
end

def price(*numbers)
  PRICES[numbers] ||= packages(numbers).map { |package|
    remain = Vector[*numbers] - Vector[*package]
    min_price(*package) + min_price(*remain.to_a)
  }.min
end

puts "---------------------------------"

describe "Harry Potter" do
  it "calculates the lowest price" do
    expect(price(2, 2, 2, 1, 1)).to eq(51.20)
    expect(price(4, 4, 4, 2, 2)).to eq(102.40)
    expect(price(1, 0, 0, 0, 0)).to eq(8.00)
    expect(price(1, 1, 0, 0, 0)).to eq(15.20)
    expect(price(1, 1, 1, 0, 0)).to eq(21.60)
    expect(price(1, 1, 1, 1, 0)).to eq(25.60)
    expect(price(1, 1, 1, 1, 1)).to eq(30.00)
    expect(price(2, 0, 0, 0, 0)).to eq(16.00)
    expect(price(2, 1, 0, 0, 0)).to eq(23.20)
  end

  it "returns packages" do
    expect(packages([1, 1])).to eq([[1, 0], [0, 1], [1, 1]])
  end
end

describe Array do
  context "#combinations" do
    it "returns combination arrays" do
      expect([1, 2, 3].combinations)
        .to eq([[1], [2], [3], [1, 2], [1, 3], [2, 3], [1, 2, 3]])
    end
  end
end
