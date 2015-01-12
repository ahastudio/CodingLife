import unittest


def differences(sequence):
    return [abs(j - k) for j, k in zip(sequence[:-1], sequence[1:])]

def is_jolly(*sequence):
    return set(differences(sequence)) == set(range(1, len(sequence)))


class TestJollyJumpers(unittest.TestCase):
    def test_is_jolly(self):
        self.assertTrue(is_jolly(4, 1, 3, 2))
        self.assertFalse(is_jolly(4, 3, 2, 1))

    def test_differences(self):
        self.assertTrue([3, 2, 1], differences([4, 1, 3, 2]))


if __name__ == "__main__":
    unittest.main()