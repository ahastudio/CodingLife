class Array
  def sum
    reduce(&:+)
  end
end

module Paper
  def h_index
    uniq.sort.reverse.find { |n| count { |i| i >= n } >= n }
  end

  def g_index
    top_papers = sort.reverse
    [*0..size].reverse.find { |n| top_papers.take(n).sum >= n ** 2 }
  end
end

puts '----------------------'

describe 'h-index & g-index' do
  describe Paper do
    subject { [0, 15, 4, 0, 7, 10, 0].extend(Paper) }

    it 'returns h-index' do
      expect(subject.h_index).to eq(4)
    end

    it 'returns g-index' do
      expect(subject.g_index).to eq(6)
    end
  end
end
