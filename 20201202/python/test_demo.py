from demo import sums1, sums2


def test_sample():
    for sums in [sums1, sums2]:
        assert sums([2, 1, 3, 4, 1]) == [2, 3, 4, 5, 6, 7]
        assert sums([5, 0, 2, 7]) == [2, 5, 7, 9, 12]


def test_simple():
    for sums in [sums1, sums2]:
        assert sums([1, 1]) == [2]
        assert sums([1, 2]) == [3]
        assert sums([1, 2, 3]) == [3, 4, 5]
        assert sums([3, 2, 1]) == [3, 4, 5]
