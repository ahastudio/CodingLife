$cache = {}

class Integer
  def next_3n_plus_1
    even? ? (self / 2) : (self * 3 + 1)
  end

  def length_3n_plus_1
    if self == 1
      1
    else
      $cache[self] ||= 1 + next_3n_plus_1.length_3n_plus_1
    end
  end
end

class TheeNplusOne
  def initialize(a, b=nil)
    @a = a
    @b = b
    @cache = {}
  end

  def length
    (@a..@b).map { |i| i.length_3n_plus_1 }.max
  end
end

puts "-----------------------"

describe "3n + 1" do
  it "returns max length" do
    expect(TheeNplusOne.new(1, 10).length).to eq(20)
    expect(TheeNplusOne.new(100, 200).length).to eq(125)
    expect(TheeNplusOne.new(201, 210).length).to eq(89)
    expect(TheeNplusOne.new(900, 1000).length).to eq(174)
    expect(TheeNplusOne.new(1, 100000).length).to eq(351)
    expect(TheeNplusOne.new(1, 200000).length).to eq(383)
    expect(TheeNplusOne.new(1, 1000000).length).to eq(525)
  end

  it "returns 3n+1 length" do
    expect(3.length_3n_plus_1).to eq(8)
    expect(10.length_3n_plus_1).to eq(7)
  end
end
