import unittest


def multiply(x, y):
    if y < 0:
        x, y = -x, -y
    if y == 0:
        return 0
    if y % 2 == 0:
        return multiply(x + x, y / 2)
    return x + multiply(x, y - 1)
    # return reduce(lambda a, b: a + x, range(y), 0)


class MultiplicationTest(unittest.TestCase):
    def test_simple(self):
        self.assertEqual(6, multiply(3, 2))
        self.assertEqual(9, multiply(3, 3))
        self.assertEqual(-6, multiply(3, -2))
        self.assertEqual(0, multiply(3, 0))
        self.assertEqual(300, multiply(3, 100))


if __name__ == '__main__':
    unittest.main()
