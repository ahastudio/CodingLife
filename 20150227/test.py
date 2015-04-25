import unittest
from itertools import combinations
import copy


EMPTY = '.'
FILL = '#'
BLOCKS = list(combinations([(0, 0), (1, 0), (0, 1), (1, 1)], 3))

cache = {}


class Board:
    def __init__(self, data):
        if type(data) is str:
            self.matrix = [list(line) for line in data.split('\n')]
        else:
            self.matrix = copy.deepcopy(data.matrix)
        self.width = len(self.matrix[0])
        self.height = len(self.matrix)

    def set(self, x, y, data):
        if 0 <= x < self.width and 0 <= y < self.height:
            self.matrix[y][x] = data

    def get(self, x, y):
        if 0 <= x < self.width and 0 <= y < self.height:
            return self.matrix[y][x]

    def is_empty(self, x, y):
        return self.get(x, y) is EMPTY

    def block_on(self, block, x, y):
        return all(self.is_empty(x + dx, y + dy) for dx, dy in block)

    def put_block(self, block, x, y):
        for dx, dy in block:
            self.set(x + dx, y + dy, FILL)

    def __str__(self):
        return '\n'.join(''.join(row) for row in self.matrix)


def count_with_block(board, block, x, y):
    if not board.block_on(block, x, y):
        return 0
    board = Board(board)
    board.put_block(block, x, y)
    return recursive(board, x + 1, y)

def real_recursive(board, x, y):
    if y is board.height:
        return 1
    if board.is_empty(x - 1, y):
        return 0
    if x is board.width:
        return recursive(board, 0, y + 1)
    return sum(count_with_block(board, block, x, y) for block in BLOCKS) + \
           recursive(board, x + 1, y)

def recursive(board, x, y):
    key = str(board)
    if key not in cache:
        cache[key] = real_recursive(board, x, y)
    return cache[key]

def board_cover_case_count(shape):
    if shape.count(EMPTY) % 3 is not 0:
        return 0
    return recursive(Board(shape), 0, 0)


class TestSample(unittest.TestCase):
    def test_sample(self):
        self.assertEqual(0, board_cover_case_count(
            '#.....#\n' +
            '#.....#\n' +
            '##...##'
        ))
        self.assertEqual(2, board_cover_case_count(
            '#.....#\n' +
            '#.....#\n' +
            '##..###'
        ))
        self.assertEqual(1514, board_cover_case_count(
            '##########\n' +
            '#........#\n' +
            '#........#\n' +
            '#........#\n' +
            '#........#\n' +
            '#........#\n' +
            '#........#\n' +
            '##########'
        ))

    def test_block_on_board(self):
        board = Board(
            '###...#\n' +
            '###...#\n' +
            '#######'
        )
        self.assertTrue(board.block_on(BLOCKS[0], 4, 0))
        self.assertFalse(board.block_on(BLOCKS[0], 2, 0))
        self.assertFalse(board.block_on(BLOCKS[0], 0, 0))
        self.assertFalse(board.block_on(BLOCKS[0], 0, 2))

    def test_put_block_on_board(self):
        board = Board(
            '###...#\n' +
            '###...#\n' +
            '#######'
        )
        self.assertTrue(board.block_on(BLOCKS[0], 4, 0))
        board.put_block(BLOCKS[0], 4, 0)
        self.assertFalse(board.block_on(BLOCKS[0], 4, 0))


if __name__ == '__main__':
    unittest.main()
