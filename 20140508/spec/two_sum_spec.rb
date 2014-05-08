def two_sum(numbers, target)
  [*1..numbers.size].combination(2).find do |index1, index2|
    numbers[index1 - 1] + numbers[index2 - 1] == target
  end
end

describe "Two Sum" do
  it "finds indices" do
    expect(two_sum([2, 7, 11, 15], 9)).to eq([1, 2])
    expect(two_sum([2, 7, 11, 15], 13)).to eq([1, 3])
    expect(two_sum([2, 7, 11, 15], 17)).to eq([1, 4])
    expect(two_sum([2, 7, 11, 15], 18)).to eq([2, 3])
    expect(two_sum([2, 7, 11, 15], 26)).to eq([3, 4])
    expect(two_sum([2, 7, 11, 15], -1)).to be_nil
    expect(two_sum([2, 7, 11, 15], 14)).to be_nil
  end
end
