import string
import cycle_length

cyclelength = cycle_length.CycleLength()

while 1:
	try:
		data = raw_input()
		data = string.split(data, ' ')
		data = data[:2]
		for i in range(0, len(data)):
			data[i] = int(data[i])
		if data[0] > data[1]:
			data.reverse()
		maxlength = 0
		for n in range(data[0], data[1] + 1):
			length = cyclelength.getLength(n)
			if length > maxlength:
				maxlength = length
		print "%d %d %d" % (data[0], data[1], maxlength)
	except:
		break

print "bye~"
