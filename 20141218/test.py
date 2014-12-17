import unittest

from itertools import chain, combinations


unit_price = 8.00
prices = {}


def discount(books):
    return [0, 0, 0.05, 0.10, 0.20, 0.25][len(set(books))]

def all_combinations(x):
    return chain(*[combinations(x, n) for n in range(1, len(x) + 1)])

def subsets(books):
    subsets = []
    for subset in all_combinations(set(books)):
        remain = books[:]
        for x in subset:
            del remain[remain.index(x)]
        subsets.append((list(subset), remain))
    return subsets

def price(books):
    key = '/'.join(books)
    if key not in prices:
        if len(books) == len(set(books)):
            prices[key] = unit_price * len(books) * (1 - discount(books))
        else:
            prices[key] = min(price(a) + price(b) for a, b in subsets(books))
    return prices[key]

def books(numbers):
    books = list('abcde')
    return list(''.join(a * b for a, b in zip(books, numbers)))

def solve(numbers):
    return price(books(numbers))


class HarryPorterTest(unittest.TestCase):
    def test_sample(self):
        self.assertEqual(0.00, solve([0, 0, 0, 0, 0]))
        self.assertEqual(51.20, solve([2, 2, 2, 1, 1]))
        self.assertEqual(132.40, solve([5, 5, 5, 3, 3]))

    def test_books(self):
        self.assertEqual([], books([0, 0, 0, 0, 0]))
        self.assertEqual(['a', 'b', 'b'], books([1, 2, 0, 0, 0]))

    def test_subsets(self):
        self.assertEqual([
            (['a'], []),
        ], subsets(['a']))
        self.assertEqual([
            (['a'], ['a']),
        ], subsets(['a', 'a']))
        self.assertEqual([
            (['a'], ['b']), (['b'], ['a']), (['a', 'b'], [])
        ], subsets(['a', 'b']))
        self.assertEqual([
            (['a'], ['a', 'b']), (['b'], ['a', 'a']), (['a', 'b'], ['a'])
        ], subsets(['a', 'a', 'b']))


if __name__ == '__main__':
    unittest.main()
