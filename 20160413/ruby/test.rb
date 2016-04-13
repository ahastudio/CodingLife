class Array
  def sum
    reduce(&:+)
  end
end

def multiply_digits(n)
  n.to_s.split('').map(&:to_i).reduce(&:*)
end

puts '-----------------'

describe 'Multiply digits' do
  it 'muliplies digits' do
    expect(multiply_digits(10)).to be(0)
    expect(multiply_digits(11)).to be(1)
    expect(multiply_digits(12)).to be(2)
    expect(multiply_digits(32)).to be(6)

    expect([*10..15].map { |i| multiply_digits(i) }.sum).to be(15)

    expect([*10..1000].map { |i| multiply_digits(i) }.sum).to be(93150)
  end
end
