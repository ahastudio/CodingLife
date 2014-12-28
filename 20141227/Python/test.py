import unittest


cache = {}


def next_number(number):
  if number % 2 == 0:
    return number / 2
  else:
    return (number * 3) + 1

def f(number):
  if number == 1:
    return 1
  if number not in cache:
    cache[number] = 1 + f(next_number(number))
  return cache[number]

def max_length(first, last):
  return max(f(i) for i in range(first, last + 1))


class TestThreeNplusOne(unittest.TestCase):
  def test_max_length(self):
    self.assertEqual(20, max_length(1, 10))
    self.assertEqual(125, max_length(100, 200))
    self.assertEqual(89, max_length(201, 210))
    self.assertEqual(174, max_length(900, 1000))
    self.assertEqual(351, max_length(1, 100000))
    self.assertEqual(383, max_length(1, 200000))
    self.assertEqual(525, max_length(1, 1000000))


if __name__ == '__main__':
  unittest.main()
