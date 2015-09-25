import unittest

from itertools import permutations


BLANK = '.'
BLACK = 'X'
WHITE = 'O'
WALL = '#'
WIDTH = 8
HEIGHT = 8
DIRECTIONS = [x for x in permutations([-1, 0, 1], 2) if x is not [0, 0]]


class Board:
    def __init__(self):
        self.reset()

    def reset(self):
        self.cells = [BLANK] * WIDTH * HEIGHT
        self.set(BLACK, 3, 4)
        self.set(BLACK, 4, 3)
        self.set(WHITE, 3, 3)
        self.set(WHITE, 4, 4)

    def set(self, stone, x, y):
        self.cells[y * HEIGHT + x] = stone

    def put(self, stone, x, y):
        if self.valid(x, y) and [x, y] in self.find(stone):
            self.set(stone, x, y)
            for pos in self.get_all_flips(stone, x, y):
                self.cells[pos[1] * HEIGHT + pos[0]] = stone

    def get(self, x, y):
        if not self.valid(x, y):
            return WALL
        return self.cells[y * HEIGHT + x]

    def valid(self, x, y):
        return 0 <= x < WIDTH and 0 <= y < HEIGHT

    def get_flips(self, stone, x, y, direction):
        other = (stone is BLACK) and WHITE or BLACK
        dx, dy = direction
        def iter(x, y, positions):
            cell = self.get(x, y)
            if cell is stone:
                return positions
            if cell is other:
                positions.append([x, y])
                return iter(x + dx, y + dy, positions)
            return []
        return iter(x + dx, y + dy, [])

    def get_all_flips(self, stone, x, y):
        positions = []
        for direction in DIRECTIONS:
            positions += self.get_flips(stone, x, y, direction)
        return positions

    def find(self, stone):
        return [[x, y] for x in range(WIDTH) for y in range(HEIGHT)
                if self.valid(x, y) and len(self.get_all_flips(stone, x, y))]

    def __str__(self):
        return '\n'.join(''.join(self.cells[i:i + WIDTH])
                         for i in range(0, WIDTH * HEIGHT, WIDTH))


class Room:
    def __init__(self):
        self.board = Board()
        self.players = []
        self.ready = []
        self.playing = False
        self.turn = 0
        self.history = []


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

    def test_get_flips(self):
        self.assertEqual([[3, 3]], self.board.get_flips(BLACK, 2, 3, [1, 0]))
        self.assertEqual([], self.board.get_flips(BLACK, 2, 3, [-1, 0]))
        self.assertEqual([], self.board.get_flips(BLACK, 1, 3, [1, 0]))
        self.assertEqual([], self.board.get_flips(BLACK, 4, 3, [-1, 0]))

    def test_put_and_flip(self):
        self.assertEqual(WHITE, self.board.get(3, 3))
        self.board.put(BLACK, 2, 3)
        self.assertEqual(BLACK, self.board.get(3, 3))

    def test_find(self):
        self.assertEqual(sorted([[3, 2], [2, 3], [5, 4], [4, 5]]),
                         sorted(self.board.find(BLACK)))


if __name__ == '__main__':
    unittest.main()
