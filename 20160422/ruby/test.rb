# http://www.4clojure.com/problem/195

$CACHE = {}

def f(n)
  return [''] if n == 0
  $CACHE[n] ||= (0...n).flat_map { |x|
    f(x).product(f(n - x - 1)).flat_map { |i, j| "(#{i})#{j}" }
  }.uniq.sort
end

puts '-----------------------'

describe 'Parentheses... Again' do
  it 'makes ()s' do
    expect(f(0)).to eq([''])
    expect(f(1)).to eq(['()'])
    expect(f(2)).to eq(['(())', '()()'])
    expect(f(3)).to eq(['((()))', '(()())', '(())()', '()(())', '()()()'])

    expect(f(10).size).to eq(16796)
    expect(f(9).select { |i| i.include?('(()()()())') }.sort[6])
      .to eq('(((()()()())(())))')
    expect(f(12)[5000]).to eq("(((((()()()()()))))(()))")
  end
end
