class Array
  def sum
    reduce { |a, b| a + b }
  end
end

class Pmf < Hash
  def initialize(hypos=[])
    @likelihood = nil
    hypos.each do |hypo|
      self[hypo] = 1.0
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
end

puts "---------------------------------------------------------"

# 2.1 분포
describe "Word PMF" do
  let(:words) {
    %w( Lorem Ipsum is simply dummy text of the printing and typesetting
        industry Lorem Ipsum has been the industry standard dummy text ever
        since the 1500s when an unknown printer took a galley of type and
        scrambled it to make a type specimen book
    )
  }

  it "returns probability" do
    pmf = Pmf.new
    words.each do |word|
      pmf[word] ||= 0
      pmf[word] += 1
    end
    pmf.normalize!
    expect(pmf['the']).to eq(words.count('the').to_f / words.size)
  end
end

# 2.2 쿠키 문제
describe "Cookie Problem" do
  it "returns probability" do
    pmf = Pmf.new
    # 사전확률
    pmf[:bowl1] = 0.5
    pmf[:bowl2] = 0.5
    # 사전확률 * 우도
    pmf[:bowl1] *= 0.75
    pmf[:bowl2] *= 0.5
    # 사후분포
    pmf.normalize!
    # 사후확률
    expect(pmf[:bowl1]).to eq(0.6)
  end
end

# 2.3 베이지안 프레인워크
describe "Cookie Problem with Bayesian Framework" do
  let(:mixes) {
    { bowl1: { vanilla: 0.75, chocolate: 0.25 },
      bowl2: { vanilla: 0.5, chocolate: 0.5 },
    }
  }

  it "returns probability" do
    pmf = Pmf.new(%i(bowl1 bowl2))
    expect(pmf[:bowl1]).to eq(0.5)
    expect(pmf[:bowl2]).to eq(0.5)
    pmf.likelihood do |data, hypo|
      mixes[hypo][data]
    end
    pmf.update!(:vanilla)
    expect(pmf[:bowl1]).to eq(0.6)
    expect(pmf[:bowl2]).to eq(0.4)
    %i(vanilla chocolate vanilla).each do |cookie|
      pmf.update!(cookie)
    end
    expect(pmf[:bowl1]).to eq(0.6279069767441859)
  end
end

# 2.4 몬티 홀 문제
describe "Monty Problem" do
  it "returns probability" do
    pmf = Pmf.new(%i(a b c))
    pmf.likelihood do |data, hypo|
      case hypo
      when data then 0
      when :a then 0.5
      else 1
      end
    end
    pmf.update!(:b)
    expect(pmf[:a]).to eq(0.3333333333333333)
    expect(pmf[:b]).to eq(0.0)
    expect(pmf[:c]).to eq(0.6666666666666666)
  end
end

# 2.6 M&M 문제
describe "M&M Problem" do
  let(:mix94) {
    { brown: 30, yellow: 20, red: 20, green: 10, orange: 10, tan: 10 }
  }

  let(:mix96) {
    { blue: 24, green: 20, orange: 16, yellow: 14, red: 13, brown: 13 }
  }

  let(:hypotheses) {
    { a: { bag1: mix94, bag2: mix96 },
      b: { bag1: mix96, bag2: mix94 },
    }
  }

  it "returns probability" do
    pmf = Pmf.new(%i(a b))
    pmf.likelihood do |data, hypo|
      bag, color = data
      hypotheses[hypo][bag][color]
    end
    pmf.update!([:bag1, :yellow])
    pmf.update!([:bag2, :green])
    expect(pmf[:a]).to eq(0.7407407407407408)
    expect(pmf[:b]).to eq(0.25925925925925924)
  end
end

# 연습문제 2-1
describe "Cookie Problem (General)" do
  let(:mixes) {
    { bowl1: { vanilla: 0.75, chocolate: 0.25 },
      bowl2: { vanilla: 0.5, chocolate: 0.5 },
    }
  }

  it "returns probability" do
    pmf = Pmf.new(%i(bowl1 bowl2))
    pmf.likelihood do |data, hypo|
      @mixes ||= {
        bowl1: { vanilla: 30, chocolate: 10 },
        bowl2: { vanilla: 20, chocolate: 20 },
      }
      mix = @mixes[hypo]
      like = mix[data].to_f / mix.values.sum
      mix[data] -= 1
      like
    end

    pmf.update!(:vanilla)
    expect(pmf[:bowl1]).to eq(0.6)
    expect(pmf[:bowl2]).to eq(0.4)

    pmf.update!(:vanilla)
    expect(pmf[:bowl1]).to be > 0.6
    expect(pmf[:bowl2]).to be < 0.4

    18.times { pmf.update!(:vanilla) }
    expect(pmf[:bowl1]).to be > 0.9999999
    expect(pmf[:bowl2]).to be < 0.0000001

    pmf.update!(:vanilla)
    expect(pmf[:bowl1]).to eq(1.0)
    expect(pmf[:bowl2]).to eq(0.0)
  end
end
