puts '--------------------------'

describe Array do
  # 2.2.1 배열 만들기
  it 'creates a new array instance' do
    numbers = []
    expect(numbers.size).to eq(0)

    numbers = [1, 2, 3, 4, 5]
    expect(numbers.size).to eq(5)

    numbers = Array.new
    expect(numbers.size).to eq(0)

    numbers = Array.new([1, 2, 3, 4, 5])
    expect(numbers.size).to eq(5)

    numbers = Array.new(10)
    expect(numbers.size).to eq(10)

    number = 3
    arr = [7, 4, 1776]
    expect(number.is_a?(Array)).to be_falsey
    expect(arr.is_a?(Array)).to be_truthy
  end

  # 2.2.2 배열 요소 접근하고 값 고치기
  it 'accesses elements' do
    nums = []
    100.times { |i| nums[i] = i + 1 }
    expect(nums).to eq([*1..100])

    numbers = [1, 2, 3, 4, 5]
    expect(numbers[0] + numbers[1] + numbers[2] + numbers[3] + numbers[4])
      .to eq(15)

    numbers = [1, 2, 3, 5, 8, 13, 21]
    sum = 0
    numbers.size.times { |i| sum += numbers[i] }
    expect(sum).to eq(53)
  end

  # 2.2.3 문자열로 배열 만들기
  it 'splits a string' do
    sentence = 'the quick brown fox jumped over the lazy dog'
    words = sentence.split(' ')
    expect(words.size).to be(9)
    expect(words[0]).to match('the')
    expect(words[8]).to match('dog')
    expect(words).to match(%w(the quick brown fox jumped over the lazy dog))
  end

  # 2.2.4 배열 전체에 적용되는 기능
  it 'copies the array' do
    nums = [*1..100]
    samenums = nums
    nums[0] = 400
    expect(samenums[0]).to eq(400)

    nums = [*1..100]
    samenums = nums.dup
    nums[0] = 400
    expect(samenums[0]).to eq(1)

    nums = [*1..100]
    samenums = nums.clone
    nums[0] = 400
    expect(samenums[0]).to eq(1)

    nums = [*1..100]
    samenums = Array.new(nums)
    nums[0] = 400
    expect(samenums[0]).to eq(1)
  end
end
