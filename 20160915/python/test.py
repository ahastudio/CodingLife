import unittest


class Collatz:
    def __init__(self, number):
        self.number = number

    def next(self, n):
        if n % 2 is 0:
            return n / 2
        return n * 3 + 1

    def is_return(self):
        n = self.number * 3 + 1
        while True:
            n = self.next(n)
            if n == 1:
                return False
            if n == self.number:
                return True


class CollatzTest(unittest.TestCase):
    def test_sample(self):
        self.assertTrue(Collatz(2).is_return())
        self.assertTrue(Collatz(4).is_return())
        self.assertFalse(Collatz(6).is_return())
        self.assertTrue(Collatz(274).is_return())


def solve():
    result = [i for i in range(2, 10000 + 1, 2) if Collatz(i).is_return()]
    print len(result), result


if __name__ == '__main__':
    solve()
    unittest.main()
