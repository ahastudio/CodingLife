class String
  def letters
    split('')
  end

  def blank?
    ['', ' ', "\t", "\r", "\n"].include?(self)
  end
end

class Array
  def at(index)
    index >= 0 and self[index]
  end

  def indices(x)
    (0...size).select{|i| self[i] == x}
  end
end

class Boggle
  def initialize(data)
    @width = data.index(' ') || data.size
    @data = data.split('').reject{|i| i.blank?}
  end

  def include?(word)
    letters = word.letters
    @data.indices(letters.first).any?{|i| connect?(i, letters[1..-1])}
  end

  def connect?(index, letters)
    if letters.empty?
      true
    else
      near_indices = near_indices(index)
      near_letters(index, near_indices).indices(letters.first).any? do |i|
        connect?(near_indices[i], letters[1..-1])
      end
    end
  end

  def near_letters(index, near_indices)
    near_indices.map{|i| @data.at(i)}
  end

  def near_indices(index)
    [-1, +1].product([1, @width, @width + 1, @width - 1]).map do |i, j|
      index + i * j
    end
  end
end

puts "----------------"

describe "Boggle" do
  subject { Boggle.new("ABCAD") }
  it { is_expected.to be_include("A") }
  it { is_expected.not_to be_include("Z") }
  it { is_expected.to be_include("BA") }
  it { is_expected.not_to be_include("CD") }
  it { is_expected.to be_include("ABC") }
  it { is_expected.not_to be_include("ABZ") }
  it { is_expected.to be_include("AD") }
end

describe "Boggle", "(5x5)" do
  subject { Boggle.new("URLPM XPRET GIAET XTNZY XOQRS") }
  it { is_expected.to be_include("AR") }
  it { is_expected.to be_include("AN") }
  it { is_expected.to be_include("AP") }
  it { is_expected.to be_include("AZ") }
  it { is_expected.to be_include("AT") }
  it { is_expected.to be_include("XR") }
  it { is_expected.to be_include("PRETTY") }
  it { is_expected.to be_include("GIRL") }
  it { is_expected.to be_include("REPEAT") }
  it { is_expected.not_to be_include("KARA") }
  it { is_expected.not_to be_include("PANDORA") }
  it { is_expected.to be_include("GIAZAPX") }
end
