from itertools import chain

def spiral(n):
    m = [[None] * n for i in range(n)]
    x, y = 0, 0
    dx, dy = 1, 0
    for i in range(n * n):
        m[y][x] = i
        nx, ny = x + dx, y + dy
        if not (0 <= nx < n and 0 <= ny < n and m[ny][nx] is None):
            dx, dy = -dy, dx
        x += dx
        y += dy
    return list(chain(*m))

# test ------------------------------------------------------

def test_sample():
    assert spiral(1) == [
        0
    ]

    assert spiral(2) == [
        0, 1,
        3, 2
    ]

    assert spiral(3) == [
        0, 1, 2,
        7, 8, 3,
        6, 5, 4
    ]

    assert spiral(5) == [
        0,  1,  2,  3,  4,
        15, 16, 17, 18, 5,
        14, 23, 24, 19, 6,
        13, 22, 21, 20, 7,
        12, 11, 10, 9,  8
    ]
