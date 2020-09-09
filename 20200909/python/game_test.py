from copy import deepcopy


def solution(board, moves):
    count = 0
    queue = []
    for position in moves:
        board, queue = move(board, queue, position)
        queue, count = process_queue(queue, count)
    return count


def move(board, queue, position):
    index = position - 1
    new_board = deepcopy(board)
    x = 0
    for i, row in enumerate(new_board):
        x = row[index]
        if x:
            row[index] = 0
            break
    return new_board, queue + [x]


def process_queue(queue, count):
    if queue[-1] == 0:
        queue = queue[:-1]
    if len(queue) >= 2 and queue[-1] == queue[-2]:
        count += 2
        queue = queue[:-2]
    return queue, count


def test_simple():
    assert solution([
        [0, 0, 0, 0, 0],
        [0, 0, 1, 0, 3],
        [0, 2, 5, 0, 1],
        [0, 2, 4, 4, 2],
        [0, 5, 1, 3, 1]
    ], [1, 1, 1, 1]) == 0
    assert solution([
        [0, 0, 0, 0, 0],
        [0, 0, 1, 0, 3],
        [0, 2, 5, 0, 1],
        [0, 2, 4, 4, 2],
        [0, 5, 1, 3, 1]
    ], [2, 2, 2]) == 2


def test_sample():
    assert solution([
        [0, 0, 0, 0, 0],
        [0, 0, 1, 0, 3],
        [0, 2, 5, 0, 1],
        [4, 2, 4, 4, 2],
        [3, 5, 1, 3, 1]
    ], [1, 5, 3, 5, 1, 2, 1, 4]) == 4


def test_move():
    new_board = [
        [0, 0, 0, 0, 0],
        [0, 0, 1, 0, 3],
        [0, 2, 5, 0, 1],
        [0, 2, 4, 4, 2],
        [3, 5, 1, 3, 1]
    ]

    assert move([
        [0, 0, 0, 0, 0],
        [0, 0, 1, 0, 3],
        [0, 2, 5, 0, 1],
        [4, 2, 4, 4, 2],
        [3, 5, 1, 3, 1]
    ], [1], 1) == (new_board, [1, 4])


def test_process_queue():
    assert process_queue([1, 2, 0], 0) == ([1, 2], 0)
    assert process_queue([1, 2, 2], 0) == ([1], 2)
    assert process_queue([2, 2], 0) == ([], 2)
