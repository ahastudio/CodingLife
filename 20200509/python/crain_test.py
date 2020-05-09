class Board():
    def __init__(self, board):
        self.stacks = [
            [row[x] for row in reversed(board) if row[x]]
            for x in range(len(board[0]))
        ]
        self.basket = [-1]  # trick! :D
        self.score = 0

    def pick(self, position):
        stack = self.stacks[position - 1]
        if not stack:
            return
        return stack.pop()

    def move(self, doll):
        if not doll:
            return
        if doll != self.basket[-1]:
            self.basket.append(doll)
            return
        self.score += 2
        self.basket.pop()

    def game(self, moves):
        for i in moves:
            self.move(self.pick(i))
        return self.score


def solution(board, moves):
    return Board(board).game(moves)


def test_game():
    assert solution([
        [0, 0, 0, 0, 0],
        [0, 0, 1, 0, 3],
        [0, 2, 5, 0, 1],
        [4, 2, 4, 4, 2],
        [3, 5, 1, 3, 1]
    ], [1, 5, 3, 5, 1, 2, 1, 4]) == 4


def test_pick():
    board = Board([
        [0, 2],
        [1, 3]
    ])
    assert board.pick(1) == 1
    assert board.pick(1) is None


def test_move():
    board = Board([[1]])
    board.move(1)
    assert board.score == 0
    board.move(2)
    assert board.score == 0
    board.move(None)
    assert board.score == 0
    board.move(2)
    assert board.score == 2
    board.move(1)
    assert board.score == 4
