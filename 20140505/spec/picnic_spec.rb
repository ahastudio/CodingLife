# frozen_string_literal: true

class Array
  def sum(&block)
    map(&block).reduce { |a, b| a + b }
  end

  def head_pairs
    [first].product(self - [first])
  end

  def pair_ways
    if empty?
      [[]]
    else
      head_pairs.sum do |head|
        (self - head).pair_ways.map { |tail| [head] + tail }
      end
    end
  end
end

def count_pair_ways(kids_count, friends)
  [*0...kids_count].pair_ways.select do |pairs|
    pairs.all? { |pair| friends.map(&:sort).include?(pair) }
  end.size
end

describe 'Picnic' do
  context '2 kids' do
    let(:kids_count) { 2 }
    let(:friends) { [[0, 1]] }

    it 'makes 1 pair-way' do
      expect(count_pair_ways(kids_count, friends)).to eq(1)
    end
  end

  context '4 kids' do
    let(:kids_count) { 4 }
    let(:friends) { [[0, 1], [1, 2], [2, 3], [3, 0], [0, 2], [1, 3]] }

    it 'makes 3 pair-ways' do
      expect(count_pair_ways(kids_count, friends)).to eq(3)
    end
  end

  context '6 kids' do
    let(:kids_count) { 6 }
    let(:friends) do
      [[0, 1], [2, 0], [1, 2], [1, 3], [1, 4], [2, 3],
       [2, 4], [3, 4], [3, 5], [4, 5]]
    end

    it 'makes 4 pair-ways' do
      expect(count_pair_ways(kids_count, friends)).to eq(4)
    end
  end
end

describe Array do
  describe '#pair_ways' do
    it 'makes pair ways' do
      expect([0, 1].pair_ways).to match_array([[[0, 1]]])
      expect([2, 3].pair_ways).to match_array([[[2, 3]]])
    end

    context 'with 4 elements' do
      it 'makes pair ways' do
        expect([*0...4].pair_ways).to match_array(
          [
            [[0, 1], [2, 3]],
            [[0, 2], [1, 3]],
            [[0, 3], [1, 2]]
          ]
        )
      end
    end

    context 'with 6 elements' do
      it 'makes pair ways' do
        expect([*0...6].pair_ways[0, 3]).to match_array(
          [
            [[0, 1], [2, 3], [4, 5]],
            [[0, 1], [2, 4], [3, 5]],
            [[0, 1], [2, 5], [3, 4]]
          ]
        )
        expect([*0...6].pair_ways[3, 3]) .to match_array(
          [
            [[0, 2], [1, 3], [4, 5]],
            [[0, 2], [1, 4], [3, 5]],
            [[0, 2], [1, 5], [3, 4]]
          ]
        )
        expect([*0...6].pair_ways[6, 3]) .to match_array(
          [
            [[0, 3], [1, 2], [4, 5]],
            [[0, 3], [1, 4], [2, 5]],
            [[0, 3], [1, 5], [2, 4]]
          ]
        )
      end
    end

    context 'with 8 elements' do
      it 'makes pair ways' do
        expect([*0...8].pair_ways[0, 4]) .to match_array(
          [
            [[0, 1], [2, 3], [4, 5], [6, 7]],
            [[0, 1], [2, 3], [4, 6], [5, 7]],
            [[0, 1], [2, 3], [4, 7], [5, 6]],
            [[0, 1], [2, 4], [3, 5], [6, 7]]
          ]
        )
      end
    end
  end
end
