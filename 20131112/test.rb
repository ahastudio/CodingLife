class FuzzBizz < Array
  def initialize(n, a=3, b=5)
    @a = a
    @b = b
    n.times{|i| self << value_at(i)}
  end

  def value_at(index)
    FuzzBizz.value_at(index, @a, @b)
  end

  def self.value_at(index, a, b)
    n = index + 1
    if n % (a * b) == 0
      "FuzzBizz"
    elsif n % a == 0
      "Fuzz"
    elsif n % b == 0
      "Bizz"
    else
      index + 1
    end
  end
end

puts "------------------------------"

describe "FuzzBizz" do
  describe "with small case" do
    it "should equal FuzzBizz array" do
      array = [1, 2, 'Fuzz', 4, 'Bizz', 'Fuzz', 7, 8, 'Fuzz', 'Bizz', 11, 'Fuzz', 13, 14, 'FuzzBizz']
      array.length.times do |i|
        FuzzBizz.new(i + 1).should == array[0..i]
      end
    end
  end

  describe "with large case" do
    let(:n) { 1000 }
    subject { FuzzBizz.new(n) }

    it "should have Fuzz" do
      (n / 3).times do |i|
        number = 3 * (i + 1)
        subject[number - 1].should == (number % 5 == 0 ? "FuzzBizz" : "Fuzz")
      end
    end

    it "should have Bizz" do
      (n / 5).times do |i|
        number = 5 * (i + 1)
        subject[number - 1].should == (number % 3 == 0 ? "FuzzBizz" : "Bizz")
      end
    end

    it "should have right words count" do
      subject.count{|i| i == "Fuzz"}.should == n / 3 - n / 15
      subject.count{|i| i == "Bizz"}.should == n / 5 - n / 15
      subject.count{|i| i == "FuzzBizz"}.should == n / 15
    end
  end

  describe "#value_at" do
    it "should return number when input is normal" do
      FuzzBizz.value_at(0, 3, 5).should == 1
      FuzzBizz.value_at(1, 3, 5).should == 2
    end

    it "should return Fuzz when input is 3 * n" do
      FuzzBizz.value_at(3 - 1, 3, 5).should == "Fuzz"
      FuzzBizz.value_at(6 - 1, 3, 5).should == "Fuzz"
    end

    it "should return Bizz when input is 5 * n" do
      FuzzBizz.value_at(5 - 1, 3, 5).should == "Bizz"
      FuzzBizz.value_at(10 - 1, 3, 5).should == "Bizz"
    end

    it "should return FuzzBizz when input is 3 * n and 5 * n" do
      FuzzBizz.value_at(15 - 1, 3, 5).should == "FuzzBizz"
      FuzzBizz.value_at(30 - 1, 3, 5).should == "FuzzBizz"
    end
  end
end

describe "FuzzBizz (4, 7)" do
  describe "with small case" do
    it "should equal FuzzBizz array" do
      array = [1, 2, 3, 'Fuzz', 5, 6, 'Bizz', 'Fuzz', 9, 10, 11, 'Fuzz', 13, 'Bizz']
      array.length.times do |i|
        FuzzBizz.new(i + 1, 4, 7).should == array[0..i]
      end
    end
  end
end
