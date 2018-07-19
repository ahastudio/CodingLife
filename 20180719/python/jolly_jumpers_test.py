import unittest

def is_jolly(sequence):
    return list(range(1, len(sequence))) == sorted(differences(sequence))


def differences(sequence):
    n = len(sequence)
    if n <= 1:
        return []
    difference = abs(sequence[n - 1] - sequence[n - 2])
    return differences(sequence[:n - 1]) + [difference]
    # return [abs(a - b) for a, b in zip(sequence[:-1], sequence[1:])]


class JollyJumpersTest(unittest.TestCase):
    def test_simple(self):
        self.assertEqual(True, is_jolly([1, 2, 4]))
        self.assertEqual(False, is_jolly([1, 2, 3]))
        self.assertEqual(True, is_jolly([1, 4, 2, 3]))
        self.assertEqual(False, is_jolly([1, 4, 2, -1, 6]))
        self.assertEqual(True, is_jolly([11, 7, 4, 2, 1, 6]))

    def test_differences(self):
        self.assertEqual([], differences([]))
        self.assertEqual([], differences([2]))
        self.assertEqual([2], differences([2, 4]))
        self.assertEqual([1, 2], differences([1, 2, 4]))
        self.assertEqual([1, 1], differences([1, 2, 3]))
        self.assertEqual([1, 1, 2], differences([1, 2, 3, 5]))
        self.assertEqual([1, 1, 2, 5], differences([1, 2, 3, 5, 10]))


if __name__ == '__main__':
    unittest.main()
