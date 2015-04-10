class TrianglePath
  def initialize(lines)
    @lines = lines
    @cache = {}
  end

  def at(x, y)
    if y < @lines.size
      @cache[[x, y]] ||= @lines[y][x] + [at(x, y + 1), at(x + 1, y + 1)].max
    else
      0
    end
  end

  def max
    at(0, 0)
  end
end

puts '----------------------------------'

describe TrianglePath do
  let(:large_case) do
    open('largecase.txt').readlines.map { |line| line.split.map(&:to_i) }
  end

  def f(lines)
    TrianglePath.new(lines).max
  end

  it 'returns maximum path sum' do
    expect(f([
      [6],
      [1, 2],
      [3, 7, 4],
      [9, 4, 1, 7],
      [2, 7, 5, 9, 4]
    ])).to eq(28)

    expect(f([
      [1],
      [2, 4],
      [8, 16, 8],
      [32, 64, 32, 64],
      [128, 256, 128, 256, 128]
    ])).to eq(341)

    expect(f(large_case)).to eq(7273)
  end
end
