f = -> (n) { [*1..n].map { |i| 'O' * (n - i) + 'X' * i } }
puts f[6]

puts '----------------------------------'

def oxs(n)
  [*0...n].reverse.zip([*1..n]).map { |i, j| 'O' * i + 'X' * j }
end

describe 'Printing OXs' do
  it 'returns OXs' do
    expect(oxs(6)).to eq([
      'OOOOOX', 'OOOOXX', 'OOOXXX',
      'OOXXXX', 'OXXXXX', 'XXXXXX'
    ])
  end
end
