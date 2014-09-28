class Array
  def sum
    reduce { |a, b| a + b }
  end
end

class Board
  BLANK = "."
  FILL = "#"

  BLOCKS = [
    [[0, 0], [1, 0], [1, 1]], # ┐
    [[0, 0], [0, 1], [-1, 1]], # ┘
    [[0, 0], [0, 1], [1, 1]], # └
    [[0, 0], [1, 0], [0, 1]], # ┌
  ]

  def initialize(lines)
    @lines = lines.map { |line| line.clone.to_s }
    if invalid?
      raise Exception.new("invalid data")
    end
  end

  def solve_count
    if impossible?
      0
    else
      solve_count_recursive
    end
  end

  def solve_count_recursive
    if fill?
      1
    else
      x, y = find_blank_position
      BLOCKS.map { |block|
        if can_put_block?(x, y, block)
          put_block(x, y, block, FILL)
          count = solve_count_recursive
          put_block(x, y, block, BLANK)
          count
        else
          0
        end
      }.sum
    end
  end

  def valid?
    @lines.join.split('').all? { |i| [BLANK, FILL].include?(i) }
  end

  def invalid?
    not valid?
  end

  def possible?
    @lines.join.count(BLANK) % 3 == 0
  end

  def impossible?
    not possible?
  end

  def fill?
    @lines.join.count(BLANK) == 0
  end

  def find_blank_position
    y = @lines.index { |line| line.include?(BLANK) }
    [@lines[y].index(BLANK), y]
  end

  def can_put_block?(x, y, block)
    block.all? { |dx, dy| blank_on?(x + dx, y + dy) }
  end

  def put_block(x, y, block, what)
    block.each { |dx, dy| @lines[y + dy][x + dx] = what }
  end

  def blank_on?(x, y)
    x >= 0 and y >= 0 and @lines[y][x] == BLANK
  end
end

puts "------------------------------------------------"

describe "BoardCover" do
  describe "impossible" do
    subject do
      Board.new([
        "#.....#",
        "#.....#",
        "##...##",
      ]).solve_count
    end

    it { is_expected.to eq(0) }
  end

  describe "example" do
    subject do
      Board.new([
        "#.....#",
        "#.....#",
        "##..###",
      ]).solve_count
    end

    it { is_expected.to eq(2) }
  end

  describe "simple case" do
    subject do
      Board.new([
        "#..#",
        "#.##",
      ]).solve_count
    end

    it { is_expected.to eq(1) }
  end

  describe "large case" do
    subject do
      Board.new([
        "##########",
        "#........#",
        "#........#",
        "#........#",
        "#........#",
        "#........#",
        "#........#",
        "##########",
      ]).solve_count
    end

    it { is_expected.to eq(1514) }
  end

  it "#find_blank_position" do
    expect(Board.new(["...", "..."]).find_blank_position).to eq([0, 0])
    expect(Board.new(["#..", "..."]).find_blank_position).to eq([1, 0])
    expect(Board.new(["##.", "..."]).find_blank_position).to eq([2, 0])
    expect(Board.new(["###", "..."]).find_blank_position).to eq([0, 1])
    expect(Board.new(["###", "#.."]).find_blank_position).to eq([1, 1])
  end

  it "#put_block" do
    board = Board.new(["#..#", "#.##"])
    expect(board).not_to be_fill
    board.put_block(1, 0, Board::BLOCKS[3], Board::FILL)
    expect(board).to be_fill
  end
end
