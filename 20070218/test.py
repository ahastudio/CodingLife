import string

while 1:
	try:
		data = raw_input()
		data = string.split(data, ' ')
		data = data[:2]
		for i in range(0, len(data)):
			data[i] = int(data[i])
		if data[0] > data[1]:
			data.reverse()
		max_cycle_length = 0
		for n in range(data[0], data[1] + 1):
			cycle_length = 1
			while n > 1:
				if n % 2:
					n = n * 3 + 1
				else:
					n /= 2
				cycle_length += 1
			if cycle_length > max_cycle_length:
				max_cycle_length = cycle_length
		print "%d %d %d" % (data[0], data[1], max_cycle_length)
	except:
		break

print "bye~"
