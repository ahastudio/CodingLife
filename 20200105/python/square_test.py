from math import gcd, ceil


def solution1(w, h):
    d = gcd(w, h)
    [a, b] = sorted([w // d, h // d])
    return w * h - d * sum(ceil((i + 1) * b / a) - i * b // a for i in range(a))


def solution2(w, h):
    return w * h - w - h + gcd(w, h)


# test ----------------------------------

def test_sample():
    assert solution1(8, 12) == 80
    assert solution2(8, 12) == 80
    for i in range(8):
        assert solution1(10 ** i, 10 ** i) == 10 ** (i * 2) - 10 ** i
        assert solution2(10 ** i, 10 ** i) == 10 ** (i * 2) - 10 ** i
    assert solution2(10 ** i, 10 ** i - 1) == 99999970000002
