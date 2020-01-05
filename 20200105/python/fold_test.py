def fold(n):
    if n == 0:
        return []
    w = fold(n - 1)
    return w + [0] + [1 - i for i in w[::-1]]


# test ----------------------

def test_sample():
    assert fold(0) == []
    assert fold(1) == [0]
    assert fold(2) == [0, 0, 1]
    assert fold(3) == [0, 0, 1, 0, 0, 1, 1]
    assert fold(4) == [0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1]
