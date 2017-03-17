import pytest


class Money:
    def __init__(self, amount, currency):
        self._amount = amount
        self._currency = currency

    def __eq__(self, other):
        return self._currency == other._currency and \
               self._amount == other._amount

    def currency(self):
        return self._currency

    def times(self, multiplier):
        return Money(self._amount * multiplier, self.currency())

    @staticmethod
    def dollar(amount):
        return Money(amount, 'USD')

    @staticmethod
    def franc(amount):
        return Money(amount, 'CHF')

    @staticmethod
    def won(amount):
        return Money(amount, 'KRW')


def test_multiplication():
    five = Money.dollar(5)
    assert Money.dollar(10) == five.times(2)
    assert Money.dollar(15) == five.times(3)


def test_equality():
    assert Money.dollar(5) == Money.dollar(5)
    assert Money.dollar(5) != Money.dollar(6)
    assert Money.franc(5) == Money.franc(5)
    assert Money.franc(5) != Money.franc(6)
    assert Money.franc(5) != Money.dollar(5)


def test_franc_multiplication():
    five = Money.franc(5)
    assert Money.franc(10) == five.times(2)
    assert Money.franc(15) == five.times(3)


def test_currency():
    assert 'USD' == Money.dollar(1).currency()
    assert 'CHF' == Money.franc(1).currency()
    # bonus...
    assert 'KRW' == Money.won(1).currency()
    assert 'KRW' == Money(1, 'KRW').currency()
