import unittest

from itertools import permutations


def sum1(numbers):
    sum = 0
    for i in numbers:
        sum += i
    return sum

def sum2(numbers):
    sum = 0
    while len(numbers):
        sum += numbers[0]
        numbers = numbers[1:]
    return sum

def sum3(numbers):
    if len(numbers) is 0:
        return 0
    return numbers[0] + sum3(numbers[1:])

def combine(a, b):
    if len(a) is 0:
        if len(b) is 0:
            return []
        a, b = b, a
    return [a[0]] + combine(b, a[1:])

CACHE = { 1: 0, 2: 1 }

def fib(n):
    def f(n):
        if n not in CACHE:
            CACHE[n] = f(n - 1) + f(n - 2)
        return CACHE[n]
    if n <= 0:
        return []
    return fib(n - 1) + [f(n)]

def largest_possible_number(numbers):
    return max(int(''.join(str(i) for i in seq))
               for seq in permutations(numbers))


class FiveProblems(unittest.TestCase):
    def test_sum(self):
        self.assertEqual(55, sum1([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]))
        self.assertEqual(55, sum2([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]))
        self.assertEqual(55, sum3([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]))

    def test_combines_two_lists_by_alternatingly_taking_elements(self):
        self.assertEqual(['a', 1, 'b', 2, 'c', 3],
                         combine(['a', 'b', 'c'], [1, 2, 3]))
        self.assertEqual(['a', 1, 'b', 'c'],
                         combine(['a', 'b', 'c'], [1]))
        self.assertEqual(['a', 1, 2, 3],
                         combine(['a'], [1, 2, 3]))

    def test_fibonacci_numbers(self):
        self.assertEqual([0, 1, 1, 2, 3, 5, 8, 13, 21, 34], fib(10))
        self.assertEqual(218922995834555169026L, fib(100)[-1])

    def test_arranges_form_the_largest_possible_number(self):
        self.assertEqual(95021, largest_possible_number([50, 2, 1, 9]))
        self.assertEqual(922001, largest_possible_number([200, 2, 1, 9]))
        self.assertEqual(932321, largest_possible_number([23, 2, 3, 1, 9]))


if __name__ == '__main__':
    unittest.main()
