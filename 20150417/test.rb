UNIT_PRICE = 8
DISCOUNT = [0, 1.0, 0.95, 0.9, 0.8, 0.75]
CACHE = {}

class Array
  def sum
    reduce { |a, b| a + b }
  end

  def sub(other)
    zip(other).map { |i| i[0] - i[1] }
  end
end

def price(books)
  if books.all? { |i| i <= 1 }
    UNIT_PRICE * books.sum * DISCOUNT[books.sum]
  else
    sorted_books = books.sort
    unless CACHE.include?(sorted_books)
      packages = (0...5).map { |i| [0] * i + [1] * (5 - i) }
        .select { |package| sorted_books.sub(package).all? { |i| i >= 0 } }
      CACHE[sorted_books] = packages.map { |package|
        price(package) + price(sorted_books.sub(package))
      }.min
    end
    CACHE[sorted_books]
  end
end

describe "Harry Potter Kata" do
  it "calculate total price" do
    expect(price([2, 2, 2, 1, 1])).to eq(51.20)
    expect(price([1, 1, 1, 0, 0])).to eq(21.60)
    expect(price([1, 1, 1, 1, 0])).to eq(25.60)
    expect(price([2, 2, 1, 0, 0])).to eq(36.80)
    expect(price([2, 1, 0, 0, 0])).to eq(23.20)
    expect(price([4, 4, 4, 2, 2])).to eq(102.40)
    expect(price([5, 5, 5, 5, 5])).to eq(150.00)
  end
end
