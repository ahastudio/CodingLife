class Money
  attr_reader :amount, :currency

  def initialize(amount, currency)
    @amount = amount
    @currency = currency
  end

  def ==(other)
    @amount == other.amount && @currency == other.currency
  end

  def *(times)
    Money.new(@amount * times, @currency)
  end

  def +(other)
    if @currency == other.currency
      Money.new(@amount + other.amount, @currency)
    else
      self + other.exchange(@currency)
    end
  end

  def exchange(currency)
    return self if @currency == currency
    Money.new(@amount * Money.rate(@currency, currency), currency)
  end

  def self.dollar(amount)
    Money.new(amount, :USD)
  end

  def self.franc(amount)
    Money.new(amount, :CHF)
  end

  def self.rate(from, to)
    {
      USD: { CHF: 2 },
      CHF: { USD: 0.5 }
    }[from][to]
  end
end

puts '---------------------------------------------'

describe Money do
  it 'multiply money and times' do
    expect(Money.dollar(5) * 2).to eq(Money.dollar(10))
    expect(Money.franc(5) * 2).to eq(Money.franc(10))
  end

  it 'add USD and CHF' do
    expect(Money.dollar(5) + Money.franc(10)).to eq(Money.dollar(10))
    expect(Money.franc(10) + Money.dollar(5)).to eq(Money.franc(20))
  end
end
