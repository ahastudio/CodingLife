import unittest

def sum_digits(n):
    if n < 10:
        return n
    return n % 10 + sum_digits(n // 10)


def d(n):
    return sum_digits(n) + n


def self_numbers(end):
    ds = {d(i) for i in range(1, end)}
    return set(range(1, end)) - ds


class SelfNumberTest(unittest.TestCase):
    def test_function_d(self):
        self.assertEqual(101, d(91))
        self.assertEqual(101, d(100))
        self.assertEqual(1003, d(1001))

    def test_sum_digits(self):
        self.assertEqual(3, sum_digits(3))
        self.assertEqual(4, sum_digits(13))
        self.assertEqual(6, sum_digits(132))

    def test_self_numbers(self):
        self.assertEqual({1, 3, 5, 7, 9}, self_numbers(10))
        self.assertEqual({1, 3, 5, 7, 9, 20, 31}, self_numbers(40))
        self.assertEqual(1_227_365, sum(self_numbers(5_000)))
        self.assertEqual(48_890_591_235, sum(self_numbers(1_000_000)))


if __name__ == '__main__':
    unittest.main()
