require 'test/unit'

class Fixnum
  def computed_mirror?
    !computed_mirror_expression.nil?
  end

  def computed_mirror_expression
    expressions.find do |e|
      next if e =~ /\A\d+\Z/
      compute(e) == reverse
    end
  end

  def reverse
    to_s.reverse.to_i
  end

  def digits
    to_s.split('')
  end

  def expressions
    head, *tail = digits
    tail.reduce([head]) do |a, e|
      a.flat_map { |i| ([''] + %w(+ - * /)).map { |o| "#{i}#{o}#{e}" } }
    end
  end

  def compute(expression)
    # 0으로 시작하는 숫자는 8진수로 인식되기 때문에 이런 짓을 함.
    eval(expression.gsub(/\d+/) { |i| i.to_i.to_s })
  rescue
    # 0으로 나누는 경우 에러!
    nil
  end
end

class ComputedMirrorTest < Test::Unit::TestCase
  test 'computed mirror number' do
    assert [10, 351, 621, 886].all?(&:computed_mirror?)
    assert_false [11, 12, 123, 1234].any?(&:computed_mirror?)
  end

  test 'reverse number' do
    assert_equal 321, 123.reverse
  end

  test 'number digits' do
    assert_equal %w(1 2 3), 123.digits
  end

  test 'generate expressions' do
    assert_equal ['12', '1+2', '1-2', '1*2', '1/2'], 12.expressions
    assert_equal %w(
      123 12+3 12-3 12*3 12/3 1+23 1-23 1*23 1/23
      1+2+3 1-2+3 1*2+3 1/2+3 1+2-3 1-2-3 1*2-3 1/2-3
      1+2*3 1-2*3 1*2*3 1/2*3 1+2/3 1-2/3 1*2/3 1/2/3
    ).sort, 123.expressions.sort
  end
end

def main
  puts '*' * 80
  (0_000..9_999).each do |i|
    next unless i.computed_mirror?
    # next if i % 10 == 0 # 자릿수까지 정확히 맞는 걸로 하면 5931 하나만 나옴.
    expression = i.computed_mirror_expression.gsub(/\d+/) { |i| " #{i} " }
    puts "#{i} -> #{expression.strip} = #{i.reverse}"
  end
  puts '*' * 80
end

main
