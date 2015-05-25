def sum_using_for(list)
  sum = 0
  for i in list
    sum += i
  end
  sum
end

def sum_using_while(list)
  sum = 0
  while list.size > 0
    sum += list.pop
  end
  sum
end

def sum_using_recursion(list)
  def recur(acc, list)
    list.empty? ? acc : recur(acc + list.first, list[1..-1])
  end

  recur(0, list)
end

def combine(a, b)
  a.zip(b).flatten
end

def fib(n)
  [*0...n].reduce([]) do |list, i|
    list + [(i < 2) ? i : list[-2] + list[-1]]
  end
end

def largest_formed(numbers)
  numbers.map { |i| "#{i}a" }.sort.reverse.join.gsub('a', '').to_i
end

def possibilities(n)
  def recur(expressions, i)
    if i > 9
      expressions
    else
      ['', ' + ', ' - '].flat_map do |x|
        expressions.flat_map { |e| recur([e + "#{x}#{i}"], i + 1) }
      end
    end
  end

  recur(['1'], 2).select { |e| eval(e) == n }
end

puts '---------------------------------------'

describe "Five programming problems" do
  describe "Sum" do
    it "calculate sum of list" do
      expect(sum_using_for([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])).to eq(55)
      expect(sum_using_while([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])).to eq(55)
      expect(sum_using_recursion([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])).to eq(55)
    end
  end

  describe "Combine two lists" do
    it "combines two lists by alternatingly taking elements" do
      expect(combine([:a, :b, :c], [1, 2, 3])).to eq([:a, 1, :b, 2, :c, 3])
    end
  end

  describe "Fibonacci numbers" do
    it "computes the list of fibonacci numbers" do
      expect(fib(10)).to eq([0, 1, 1, 2, 3, 5, 8, 13, 21, 34])
      expect(fib(100).last).to eq(218922995834555169026)
    end
  end

  describe "Largest formed number" do
    it "finds largest formed number" do
      expect(largest_formed([50, 2, 1, 9])).to eq(95021)
      expect(largest_formed([200, 2, 1, 9])).to eq(922001)
    end
  end

  describe "100" do
    it "finds all possibilities" do
      expect(possibilities(100)).to include('1 + 2 + 34 - 5 + 67 - 8 + 9')
      expect(possibilities(100).size).to eq(11)
    end
  end
end
