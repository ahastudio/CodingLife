class ZigZag
  def initialize(rows)
    @board = Array.new(rows) { [] }
  end

  def position(index)
    inner = index % set
    col = index / set * 2
    if inner < rows
      [col, inner]
    else
      [col + 1, set - inner]
    end
  end

  def set
    @set ||= rows * 2 - 2
  end

  def rows
    @rows ||= @board.size
  end

  def put(x, y, character)
    @board[y][x] = character
  end

  def to_s
    @board.join.gsub(' ', '')
  end
end

def convert(text, rows)
  if rows == 1
    text
  else
    zigzag = ZigZag.new(rows)
    text.split('').each_with_index do |character, index|
      x, y = zigzag.position(index)
      zigzag.put(x, y, character)
    end
    zigzag.to_s
  end
end

puts "---------------------------------------------"

describe "ZigZag Conversion" do
  it "converts strings" do
    expect(convert("PAYPALISHIRING", 3)).to eq("PAHNAPLSIIGYIR")
    expect(convert("PAYPALISHIRING", 1)).to eq("PAYPALISHIRING")
    expect(convert("PAYP", 3)).to eq("PAPY")
    expect(convert("PAYPA", 3)).to eq("PAAPY")
    expect(convert("PAYPA", 4)).to eq("PAYAP")
    expect(convert("PAYPAL", 4)).to eq("PALYAP")
    expect(convert("PAYPALISHIRING", 5)).to eq("PHASIYIRPLIGAN")
    expect(convert("PAYPALISHIRING", 15)).to eq("PAYPALISHIRING")
  end
end

describe "ZigZag" do
  context "with 3 rows" do
    subject { ZigZag.new(3) }

    context "#position" do
      it "returns (x, y) position" do
        expect(subject.position(0)).to eq([0, 0])
        expect(subject.position(1)).to eq([0, 1])
        expect(subject.position(2)).to eq([0, 2])
        expect(subject.position(3)).to eq([1, 1])

        expect(subject.position(4)).to eq([2, 0])
        expect(subject.position(5)).to eq([2, 1])
        expect(subject.position(6)).to eq([2, 2])
        expect(subject.position(7)).to eq([3, 1])
      end
    end
  end

  context "with 4 rows" do
    subject { ZigZag.new(4) }

    context "#position" do
      it "returns (x, y) position" do
        expect(subject.position(0)).to eq([0, 0])
        expect(subject.position(1)).to eq([0, 1])
        expect(subject.position(2)).to eq([0, 2])
        expect(subject.position(3)).to eq([0, 3])
        expect(subject.position(4)).to eq([1, 2])
        expect(subject.position(5)).to eq([1, 1])
      end
    end
  end
end
