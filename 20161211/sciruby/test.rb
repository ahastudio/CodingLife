require 'nmatrix'
require 'benchmark'

def f1
  data = Array.new(100_000) { Array.new(10) { rand } }
  w = Array.new(3) { Array.new(10) { rand } }
  b = Array.new(3) { rand }
  data.map do |items|
    w.map { |weights|
      items.zip(weights).map { |i| i.reduce(&:*) }.reduce(&:+)
    }.zip(b).map { |i| i.reduce(&:+) }
  end
end

def f2
  data = N.random([100_000, 10])
  w = N.random([10, 3])
  b = N.random([1, 3])
  data.dot(w) + b.repeat(100_000, 0)
end

puts Benchmark.measure { result1 = f1 }
puts Benchmark.measure { result2 = f2 }
