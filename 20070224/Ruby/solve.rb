require 'CycleLength'

cyclelength = CycleLength.new

while 1
	i, j = gets.split(" ")
	i, j = i.to_i, j.to_i
	i, j = j, i if i > j
	maxlength = 0
	for n in (i..j)
		length = cyclelength.getLength(n)
		maxlength = length if length > maxlength
	end
	puts i.to_s + " " + j.to_s + " " + maxlength.to_s
end
