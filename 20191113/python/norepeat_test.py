from functools import reduce


def norepeat1(xs):
    return reduce(lambda a, x: a[-1] == x and a or a + [x], xs[1:], xs[:1])


def norepeat2(xs):
    return [x for x, l in zip(xs, [None] + xs) if x != l]


def test_sample():
    for norepeat in [norepeat1, norepeat2]:
        assert norepeat([1, 1, 3, 3, 0, 1, 1]) == [1, 3, 0, 1]
        assert norepeat([4, 4, 4, 3, 3]) == [4, 3]
        assert norepeat(list('abcd')) == list('abcd')
        assert norepeat(list('aabcddefghhaabbccc')) == list('abcdefghabc')
