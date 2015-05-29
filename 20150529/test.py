import unittest


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


if __name__ == '__main__':
    unittest.main()
