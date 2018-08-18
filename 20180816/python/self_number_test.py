def self_numbers(n):
    return set(range(1, n)) - {generate(i) for i in range(1, n)}


def generate(n):
    return n + sum(digits(n))


def digits(n):
    return [int(i) for i in str(n)]
    # if n < 10:
    #     return [n]
    # return digits(n // 10) + [n % 10]


# tests...


def test_simple():
    assert {1, 3, 5, 7, 9} == self_numbers(10)


def test_sum_of_self_numbers():
    assert 1_227_365 == sum(self_numbers(5_000))
    assert 122_295_392 == sum(self_numbers(50_000))
    assert 12_223_173_430 == sum(self_numbers(500_000))


def test_generate():
    assert 2 == generate(1)
    assert 18 == generate(9)
    assert 11 == generate(10)
    assert 15 == generate(12)
    assert 129 == generate(123)


def test_digits():
    assert [1] == digits(1)
    assert [1, 0] == digits(10)
    assert [3, 7] == digits(37)
    assert [1, 2, 3] == digits(123)
    assert [1, 2, 3, 4, 5, 6, 7] == digits(1234567)
