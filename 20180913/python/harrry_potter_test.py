BOOK_PRICE = 8
DISCOUNT_RATES = [0, 1.00, 0.95, 0.90, 0.80, 0.75]

price_cache = {}

def price(books):
    key = tuple(books)
    if key not in price_cache:
        price_cache[key] = calc_price(books)
    return price_cache[key]

def calc_price(books):
    if all(i <= 1 for i in books):
        count = sum(books)
        return BOOK_PRICE * count * DISCOUNT_RATES[count]
    return min(price(i) + price(remain(books, i)) for i in selections(books))

def remain(origin, selection):
    return [a - b for a, b in zip(origin, selection)]

def selections(books):
    def step(xs, index):
        if index == len(books):
            return [xs]
        result = []
        for i in range(books[index] == 0 and 1 or 2):
            result += step(xs + [i], index + 1)
        return result
    return {tuple(xs) for xs in step([], 0) if sum(xs) != 0}

# -- test ----

def test_sample():
    assert 0.00 == price([0, 0, 0, 0, 0])
    assert 51.20 == price([2, 2, 2, 1, 1])
    assert 102.40 == price([4, 4, 4, 2, 2])
    assert 150.00 == price([5, 5, 5, 5, 5])
    assert 132.40 == price([5, 5, 5, 3, 3])
    assert 132.40 == price([5, 3, 3, 5, 5])

def test_simple():
    assert 8.00 == price([1, 0, 0, 0, 0])
    assert 15.20 == price([1, 1, 0, 0, 0])
    assert 16.00 == price([2, 0, 0, 0, 0])
    assert 23.20 == price([2, 1, 0, 0, 0])

def test_remain():
    assert [1, 1, 1, 0, 1] == remain([2, 2, 2, 1, 1], [1, 1, 1, 1, 0])
    assert [1, 0, 0, 0, 0] == remain([2, 0, 0, 0, 0], [1, 0, 0, 0, 0])

def test_selections():
    assert {(1, 0, 0, 0, 0)} == selections([2, 0, 0, 0, 0])
    assert {(1, 0, 0, 0, 0), (0, 1, 0, 0, 0), (1, 1, 0, 0, 0)} == \
        selections([2, 2, 0, 0, 0])
    assert {(1, 0, 0, 0, 0), (0, 0, 0, 1, 0), (0, 0, 0, 0, 1),
            (1, 0, 0, 1, 0), (1, 0, 0, 0, 1), (0, 0, 0, 1, 1),
            (1, 0, 0, 1, 1)} == \
        selections([2, 0, 0, 3, 1])
