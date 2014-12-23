require("cycle_length")

while 1 do
	local line = io.read()
	local _, _, i, j = string.find(line, "(%d+) (%d+)")
	if i == nil or j == nil then
		error("ERROR.")
	end
	if i > j then
		local temp = i
		i = j
		j = i
	end
	local maxlength = 0
	for n = i, j do
		length = CycleLength:getLength(n)
		if length > maxlength then
			maxlength = length
		end
	end
	io.write(i, " ", j, " ", maxlength, "\n")
end
