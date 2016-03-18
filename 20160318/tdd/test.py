import unittest


def multiply(x, y):
    if y == 0:
        return 0
    if y < 0:
        x, y = -x, -y
    if y % 2 == 0:
        return multiply(x + x, y / 2)
    return x + multiply(x, y - 1)
    # return reduce(lambda a, e: a + x, range(y), 0)


class MultiplicationTest(unittest.TestCase):
    def test_simple(self):
        self.assertEqual(6, multiply(3, 2))
        self.assertEqual(-6, multiply(3, -2))
        self.assertEqual(-6, multiply(-3, 2))
        self.assertEqual(6, multiply(-3, -2))

    def test_large_case(self):
        self.assertEqual(37 * 99, multiply(37, 99))
        self.assertEqual(37 * 100, multiply(37, 100))


if __name__ == '__main__':
    unittest.main()
