class Fixnum
  def self.fizz_number=(n)
    @@fizz_number = n
  end

  def self.buzz_number=(n)
    @@buzz_number = n
  end

  def normal?
    not (fizz? or buzz?)
  end

  def fizz?
    self % @@fizz_number == 0
  end

  def buzz?
    self % @@buzz_number == 0
  end

  def to_fizzbuzz
    normal? ? self : (fizz? ? "Fizz" : "") + (buzz? ? "Buzz" : "")
  end
end

def fizzbuzz(n, a=3, b=5)
  Fixnum.fizz_number = a
  Fixnum.buzz_number = b
  (1..n).map{|i| i.to_fizzbuzz}
end

puts "------------------------------"

describe "FizzBuzz" do
  describe "with small case" do
    it "should equal FizzBuzz array" do
      array = [1, 2, 'Fizz', 4, 'Buzz', 'Fizz', 7, 8, 'Fizz', 'Buzz', 11, 'Fizz', 13, 14, 'FizzBuzz']
      array.length.times do |i|
        fizzbuzz(i + 1).should == array[0..i]
      end
    end
  end

  describe "with large case" do
    let(:n) { 1000 }
    subject { fizzbuzz(n) }

    it "should have Fizz" do
      (n / 3).times do |i|
        number = 3 * (i + 1)
        subject[number - 1].should =~ /Fizz/
      end
    end

    it "should have Buzz" do
      (n / 5).times do |i|
        number = 5 * (i + 1)
        subject[number - 1].should =~ /Buzz/
      end
    end

    it "should have FizzBuzz" do
      (n / 15).times do |i|
        number = 15 * (i + 1)
        subject[number - 1].should == "FizzBuzz"
      end
    end

    it "should have right words count" do
      subject.count{|i| i =~ /Fizz/}.should == n / 3
      subject.count{|i| i =~ /Buzz/}.should == n / 5
      subject.count{|i| i == "FizzBuzz"}.should == n / 15
    end
  end
end

describe "FizzBuzz (4, 7)" do
  describe "with small case" do
    it "should equal FizzBuzz array" do
      array = [1, 2, 3, 'Fizz', 5, 6, 'Buzz', 'Fizz', 9, 10, 11, 'Fizz', 13, 'Buzz']
      array.length.times do |i|
        fizzbuzz(i + 1, 4, 7).should == array[0..i]
      end
    end
  end
end

describe Fixnum do
  before(:each) do
    Fixnum.fizz_number = 3
    Fixnum.buzz_number = 5
  end

  it "should know what it is" do
    1.should == 1
    1.should be_normal
    1.should_not be_fizz
    1.should_not be_buzz
    3.should_not be_normal
    3.should be_fizz
    6.should be_fizz
    5.should be_buzz
    10.should be_buzz
    15.should be_fizz
    15.should be_buzz
  end
end
