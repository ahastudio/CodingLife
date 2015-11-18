class Money
  attr_reader :units

  def initialize(units)
    fail unless units.all? { |i| i.is_a?(MoneyUnit) }
    @units = units
  end

  def ==(other)
    @units.size == other.units.size &&
      @units.zip(other.units).all? { |a, b| a == b }
  end

  def *(times)
    Money.new(@units.map { |i| i * times })
  end

  def +(other)
    Money.new(@units.concat(other.units))
  end

  def exchange(currency)
    reduced = @units.reduce(MoneyUnit.new(0, currency)) do |a, e|
      a + e.exchange(currency)
    end
    Money.unit(reduced.amount, currency)
  end

  def self.unit(amount, currency)
    Money.new([MoneyUnit.new(amount, currency)])
  end

  def self.dollar(amount)
    Money.unit(amount, :USD)
  end

  def self.franc(amount)
    Money.unit(amount, :CHF)
  end
end

class MoneyUnit
  attr_reader :amount, :currency

  def initialize(amount, currency)
    @amount = amount
    @currency = currency
  end

  def ==(other)
    @amount == other.amount && @currency == other.currency
  end

  def *(times)
    MoneyUnit.new(@amount * times, @currency)
  end

  def +(other)
    fail unless @currency == other.currency
    MoneyUnit.new(@amount + other.amount, @currency)
  end

  def exchange(currency)
    return self if @currency == currency
    MoneyUnit.new(@amount * MoneyRate.rate(@currency, currency), currency)
  end
end

class MoneyRate
  def self.rate(from, to)
    {
      USD: { CHF: 2 },
      CHF: { USD: 0.5 }
    }[from][to]
  end
end

puts '---------------------------------------------'

describe Money do
  it 'multiplies money and times' do
    expect(Money.dollar(5) * 2).to eq(Money.dollar(10))
    expect(Money.franc(5) * 2).to eq(Money.franc(10))
  end

  it 'addes money' do
    expect((Money.dollar(5) + Money.dollar(10)).units.reduce(&:+))
      .to eq(MoneyUnit.new(15, :USD))
  end

  it 'exchanges the money' do
    expect(Money.franc(10).exchange(:USD)).to eq(Money.dollar(5))
  end

  it 'addes USD and CHF' do
    expect((Money.dollar(5) + Money.franc(10)).exchange(:USD))
      .to eq(Money.dollar(10))
    expect((Money.franc(10) + Money.dollar(5)).exchange(:CHF))
      .to eq(Money.franc(20))
  end
end
