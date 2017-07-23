import pytest


class BubbleSorter:
    @staticmethod
    def sort(array):
        operations = 0
        if type(array) is not list:
            return None, 0
        if len(array) <= 1:
            return array, 0
        for next_to_last in range(len(array) - 2, -1, -1):
            for index in range(next_to_last + 1):
                BubbleSorter.compareAndSwap(array, index)
                operations += 1
        return array, operations

    @staticmethod
    def compareAndSwap(array, index):
        if array[index] > array[index + 1]:
            array[index], array[index + 1] = array[index + 1], array[index]


def test_sort():
    assert BubbleSorter.sort(None) == (None, 0)
    assert BubbleSorter.sort([]) == ([], 0)
    assert BubbleSorter.sort([1]) == ([1], 0)
    assert BubbleSorter.sort([3, 1, 2]) == ([1, 2, 3], 2 + 1)
    assert BubbleSorter.sort([3, 2, 1]) == ([1, 2, 3], 2 + 1)
    assert BubbleSorter.sort([5, 1, 3, 2, 4]) == ([1, 2, 3, 4, 5],
                                                  4 + 3 + 2 + 1)
