import unittest

class CycleLength:
	def getLength(self, n):
		if n == 1:					return 1
		if self.isEvenNumber(n):	return self.getLength(n / 2) + 1
		else:						return self.getLength(n * 3 + 1) + 1

	def isEvenNumber(self, n):
		if n % 2 == 0:	return True
		else:			return False

class CycleLengthTest(unittest.TestCase):
	def test(self):
		cyclelength = CycleLength()
		self.assertEquals(1, cyclelength.getLength(1))
		self.assertEquals(2, cyclelength.getLength(2))
		self.assertEquals(8, cyclelength.getLength(3))
		self.assertEquals(3, cyclelength.getLength(4))
		self.assertEquals(6, cyclelength.getLength(5))
		self.assertEquals(cyclelength.getLength(3) + 1, cyclelength.getLength(6))
		self.assertEquals(cyclelength.getLength(4) + 1, cyclelength.getLength(8))
		self.assertEquals(cyclelength.getLength(10) + 1, cyclelength.getLength(3))

if __name__ == "__main__":
	unittest.main()
