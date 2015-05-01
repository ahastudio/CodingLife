class Array
  def sum
    reduce { |a, b| a + b }
  end
end

class Pmf < Hash
  def initialize(hypos=[], alpha=0.0)
    @likelihood = nil
    hypos.each do |hypo|
      self[hypo] = hypo ** (-alpha)
    end
    normalize!
  end

  def normalize!
    sum = values.sum.to_f
    keys.each do |hypo|
      self[hypo] /= sum
    end
  end

  def likelihood(&block)
    @likelihood = block
  end

  def update!(data)
    keys.each do |hypo|
      self[hypo] *= @likelihood.call(data, hypo)
    end
    normalize!
  end

  def mean
    reduce(0) { |a, b| a + b[0] * b[1] }
  end

  def percentile(percentage)
    p = percentage.to_f / 100
    total = 0.0
    each do |val, prob|
      total += prob
      return val if total >= p
    end
  end
end

puts "---------------------------------------------------------"

# 3.1 주사위 문제
describe "Dice Problem" do
  let(:mixes) {
    { bowl1: { vanilla: 0.75, chocolate: 0.25 },
      bowl2: { vanilla: 0.5, chocolate: 0.5 },
    }
  }

  it "returns probability" do
    pmf = Pmf.new([4, 6, 8, 12, 20])

    pmf.likelihood do |data, hypo|
      (hypo < data) ? 0 : 1.0 / hypo
    end

    pmf.update!(6)

    expect(pmf[4]).to eq(0.0)
    expect(pmf[6]).to eq(0.3921568627450981)
    expect(pmf[8]).to eq(0.2941176470588236)
    expect(pmf[12]).to eq(0.19607843137254904)
    expect(pmf[20]).to eq(0.11764705882352945)

    [6, 8, 7, 7, 5, 4].each { |i| pmf.update!(i) }

    expect(pmf[4]).to eq(0.0)
    expect(pmf[6]).to eq(0.0)
    expect(pmf[8]).to eq(0.9432484536722127)
    expect(pmf[12]).to eq(0.055206128061290875)
    expect(pmf[20]).to eq(0.0015454182664965536)
  end
end

# 3.2 기관차 문제
describe "Train Problem" do
  it "returns probability and mean" do
    pmf = Pmf.new([*1..1000])

    pmf.likelihood do |data, hypo|
      (hypo < data) ? 0 : 1.0 / hypo
    end

    pmf.update!(60)

    expect(pmf[1]).to eq(0.0)

    expect([*1..1000].max_by { |i| pmf[i] }).to eq(60)
    expect(pmf[60]).to eq(0.005905417875729855)

    expect(pmf.mean).to eq(333.41989326371095)

    # 3.3 사전 확률로 할 수 있는 것
    [30, 90].each { |i| pmf.update!(i) }
    expect(pmf.mean).to eq(164.3055864227336)
  end
end

# 3.4 사전 확률의 대안
describe "Train Problem with Zipf's law" do
  it "returns mean" do
    pmf = Pmf.new([*1..2000], 1.0)

    pmf.likelihood do |data, hypo|
      (hypo < data) ? 0 : 1.0 / hypo
    end

    [60, 30, 90].each { |i| pmf.update!(i) }

    expect(pmf.mean).to eq(133.99746308073063)
  end
end

# 3.5 신뢰구간
describe "Confidence Interval of Train Problem" do
  it "returns percentile" do
    pmf = Pmf.new([*1..2000], 1.0)

    pmf.likelihood do |data, hypo|
      (hypo < data) ? 0 : 1.0 / hypo
    end

    [60, 30, 90].each { |i| pmf.update!(i) }

    expect(pmf.percentile(5)).to eq(91)
    expect(pmf.percentile(95)).to eq(243)
  end
end
