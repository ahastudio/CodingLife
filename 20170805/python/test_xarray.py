import pytest
import random


class Array:
    def __init__(self):
        self._numbers = []

    def seek(self, n):
        return n in self._numbers

    def add(self, n):
        if self.seek(n):
            return
        for i in range(len(self._numbers)):
            if n < self._numbers[i]:
                self._numbers.insert(i, n)
                break
        else:
            self._numbers.append(n)

    def add_fast(self, n):
        if self.seek(n):
            return
        if not self._numbers:
            self._numbers.append(n)
            return
        def add(start, end):
            if start < end:
                middle = (start + end) // 2
                if n < self._numbers[middle]:
                    add(start, middle - 1)
                else:
                    add(middle + 1, end)
            else:
                if n < self._numbers[start]:
                    self._numbers.insert(start, n)
                else:
                    self._numbers.insert(start + 1, n)
        add(0, len(self._numbers) - 1)

    def add_fake(self, n):
        if self.seek(n):
            return
        self._numbers.append(n)
        self._numbers.sort()

    def delete(self, n):
        if self.seek(n):
            self._numbers.remove(n)

    def numbers(self):
        return self._numbers


def test_simple():
    a = Array()
    assert not a.seek(1)
    a.add(1)
    assert a.seek(1)
    assert not a.seek(2)
    a.delete(1)
    assert not a.seek(1)
    a.delete(2)


def test_duplication():
    a = Array()
    for i in range(2):
        a.add(1)
    a.delete(1)
    assert not a.seek(1)


def test_sorted():
    a = Array()
    for i in [10, 2, 3, 1, 5]:
        a.add(i)
    assert [1, 2, 3, 5, 10] == a.numbers()


def test_add_fast():
    a = Array()
    for i in [10, 2, 3, 1, 5, 9]:
        a.add_fast(i)
    assert [1, 2, 3, 5, 9, 10] == a.numbers()


def test_add_fake():
    a = Array()
    for i in [10, 2, 3, 1, 5, 9]:
        a.add_fake(i)
    assert [1, 2, 3, 5, 9, 10] == a.numbers()


def test_large_case():
    data = list(range(10000))
    random.shuffle(data)
    a = Array()
    for i in data:
        a.add_fast(i)
    assert sorted(data) == a.numbers()
