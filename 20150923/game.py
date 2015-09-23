import unittest


BLANK = '.'
BLACK = 'X'
WHITE = 'O'
WALL = '#'
WIDTH = 8
HEIGHT = 8


class Board:
    def __init__(self):
        self.reset()

    def reset(self):
        self.cells = [BLANK] * WIDTH * HEIGHT
        self.put(BLACK, 3, 4)
        self.put(BLACK, 4, 3)
        self.put(WHITE, 3, 3)
        self.put(WHITE, 4, 4)

    def put(self, stone, x, y):
        if self.valid(x, y):
            self.cells[y * HEIGHT + x] = stone

    def get(self, x, y):
        if not self.valid(x, y):
            return WALL
        return self.cells[y * HEIGHT + x]

    def valid(self, x, y):
        return 0 <= x < WIDTH and 0 <= y < HEIGHT

    def __str__(self):
        return '\n'.join(''.join(self.cells[i:i + WIDTH])
                         for i in range(0, WIDTH * HEIGHT, WIDTH))


class TestBoard(unittest.TestCase):
    def setUp(self):
        self.board = Board()

    def test_put_and_get(self):
        self.board.put(BLACK, 2, 3)
        self.assertEqual(BLACK, self.board.get(2, 3))
        self.assertEqual(BLANK, self.board.get(0, 0))

    def test_get_initial_stone(self):
        self.assertEqual(WHITE, self.board.get(3, 3))

    def test_get_outside(self):
        self.assertEqual(WALL, self.board.get(100, 3))


if __name__ == '__main__':
    unittest.main()
