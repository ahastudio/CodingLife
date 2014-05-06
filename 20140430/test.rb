class Array
  def sum
    reduce{|a, b| a + b}
  end

  def average
    sum.to_f / size
  end
end

def get_min_day_cost(band, costs)
  margin = costs.size - band
  (0..margin).map{|i|
    (0..margin - i).map{|n| costs[i, band + n].average}
  }.flatten.min
end

puts "------------"

describe "RockFestival" do
  context "band=3, costs=1,2,3,1,2,3" do
    subject { get_min_day_cost(3, [1, 2, 3, 1, 2, 3]) }
    it { is_expected.to eq(1.75) }
  end

  context "band=3, costs=3,1,2,3,1,2,3" do
    subject { get_min_day_cost(3, [3, 1, 2, 3, 1, 2, 3]) }
    it { is_expected.to eq(1.75) }
  end

  context "band=2, costs=1,2,3,1,2,3" do
    subject { get_min_day_cost(2, [1, 2, 3, 1, 2, 3]) }
    it { is_expected.to eq(1.5) }
  end

  context "band=2, costs=5,5,5,1,3,1,2,1,1" do
    subject { get_min_day_cost(2, [5, 5, 5, 1, 3, 1, 2, 1, 1]) }
    it { is_expected.to eq(1.0) }
  end
end
