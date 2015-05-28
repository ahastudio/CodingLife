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


class FiveProblems(unittest.TestCase):
    def test_sum(self):
        self.assertEqual(55, sum1([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]))
        self.assertEqual(55, sum2([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]))
        self.assertEqual(55, sum3([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]))


if __name__ == '__main__':
    unittest.main()
