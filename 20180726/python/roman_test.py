import unittest

NUMBERS = [
    (1_000, 'M'), (900, 'CM'),
    (500, 'D'), (400, 'CD'),
    (100, 'C'), (90, 'XC'),
    (50, 'L'), (40, 'XL'),
    (10, 'X'), (9, 'IX'),
    (5, 'V'), (4, 'IV')
]


def roman(number):
    for boundary, symbol in NUMBERS:
        if number >= boundary:
            return symbol + roman(number - boundary)
    return 'I' * number


class RomanNumeralsTest(unittest.TestCase):
    def test_sample(self):
        self.assertEqual('MDCCLXXVI', roman(1_776))
        self.assertEqual('MCMLIV', roman(1_954))
        self.assertEqual('MCDXLIV', roman(1_444))
        self.assertEqual('MCMXCIX', roman(1_999))

    def test_simple(self):
        self.assertEqual('M', roman(1_000))
        self.assertEqual('DCC', roman(700))
        self.assertEqual('LXX', roman(70))
        self.assertEqual('VI', roman(6))
        self.assertEqual('VII', roman(7))
        self.assertEqual('XI', roman(11))
        self.assertEqual('CXXIII', roman(123))

    def test_below_boundary(self):
        self.assertEqual('IV', roman(4))
        self.assertEqual('IX', roman(9))


if __name__ == '__main__':
    unittest.main()
