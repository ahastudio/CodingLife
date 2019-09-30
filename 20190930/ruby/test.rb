# frozen_string_literal: true

require 'numo/narray'

a = Numo::DFloat.new(3, 5).seq
puts a.inspect

puts

puts "Shape: #{a.shape}"
puts "Dimension: #{a.ndim}"
puts "Type: #{a.class}"
puts "Total Size: #{a.size}"

puts

b = a + [[1, 2, 3, 4, 5]]
puts b.inspect

puts

c = a + [[1], [2], [3]]
puts c.inspect

puts

d = a + 1
puts d.inspect
