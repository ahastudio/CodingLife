import unittest


BOOK_PRICE = 8.00
DISCOUNTS = [0, 0, 0.05, 0.10, 0.20, 0.25]


def memoize(f):
    cache = {}
    def wrapper(books):
        key = tuple(books)
        if key not in cache:
            cache[key] = f(books)
        return cache[key]
    return wrapper

@memoize
def price(books):
    if sum(books) == 0:
        return 0
    set_books_count = get_set_books_count(books)
    def get_price(count):
        set_price, remain_books = price_step(books, count)
        return set_price + price(remain_books)
    return min(get_price(count) for count in range(1, set_books_count + 1))

def price_step(books, count):
    set_books = get_set_books(count)
    discount = DISCOUNTS[count]
    set_price = count * BOOK_PRICE * (1 - discount)
    remain_books = [a - b for a, b in zip(books, set_books)]
    return set_price, sorted(remain_books)[::-1]


def get_set_books_count(books):
    return sum(1 for i in books if i > 0)

def get_set_books(count):
    return [1] * count + [0] * (5 - count)


class HarryPotterTest(unittest.TestCase):
    def test_simple(self):
        self.assertEqual(8.00, price([1, 0, 0, 0, 0]))
        self.assertEqual(16.00, price([2, 0, 0, 0, 0]))
        self.assertEqual(16.00 * 0.95, price([1, 1, 0, 0, 0]))
        self.assertEqual(24.00 * 0.90, price([1, 1, 1, 0, 0]))
        self.assertEqual(32.00 * 0.80, price([1, 1, 1, 1, 0]))
        self.assertEqual(40.00 * 0.75, price([1, 1, 1, 1, 1]))

    def test_sets(self):
        self.assertEqual(23.20, price([2, 1, 0, 0, 0]))

    def test_sample(self):
        self.assertEqual(51.20, price([2, 2, 2, 1, 1]))
        self.assertEqual(102.40, price([4, 4, 4, 2, 2]))

    def test_large(self):
        self.assertEqual(150.00, price([5, 5, 5, 5, 5]))
        self.assertEqual(132.40, price([5, 5, 5, 3, 3]))

    def test_price_step(self):
        self.assertEqual((24.00 * 0.90, [1, 1, 1, 1, 1]),
                         price_step([2, 2, 2, 1, 1], 3))
        self.assertEqual((32.00 * 0.80, [1, 1, 1, 1, 0]),
                         price_step([2, 2, 2, 1, 1], 4))

    def test_get_set_books_count(self):
        self.assertEqual(1, get_set_books_count([1, 0, 0, 0, 0]))
        self.assertEqual(1, get_set_books_count([2, 0, 0, 0, 0]))
        self.assertEqual(2, get_set_books_count([1, 1, 0, 0, 0]))
        self.assertEqual(3, get_set_books_count([1, 1, 1, 0, 0]))

    def test_get_set_books(self):
        self.assertEqual([1, 0, 0, 0, 0], get_set_books(1))
        self.assertEqual([1, 1, 0, 0, 0], get_set_books(2))
        self.assertEqual([1, 1, 1, 0, 0], get_set_books(3))
        self.assertEqual([1, 1, 1, 1, 0], get_set_books(4))
        self.assertEqual([1, 1, 1, 1, 1], get_set_books(5))


if __name__ == '__main__':
    unittest.main()
