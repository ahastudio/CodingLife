describe 'Lambda' do
  it 'calls a lambda' do
    expect(-> x { x + 1 }[2]).to eq(3)
  end

  it 'returns a function' do
    add = -> (a, b) { a + b }

    increase = -> x {
      -> i { add[i, x] }
    }

    expect(increase[1][2]).to eq(3)
  end
end
