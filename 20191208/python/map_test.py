def solution(n, *maps):
    return [line(n, a | b) for a, b in zip(*maps)]


def line(n, x):
    return ''.join(' #'[int(i)] for i in f'{x:016b}'[-n:])


def test_sample():
    assert solution(5, [9, 20, 28, 18, 11], [30, 1, 21, 17, 28]) == [
        '#####',
        '# # #',
        '### #',
        '#  ##',
        '#####',
    ]
    assert solution(6, [46, 33, 33, 22, 31, 50], [27, 56, 19, 14, 14, 10]) == [
        '######',
        '###  #',
        '##  ##',
        ' #### ',
        ' #####',
        '### # ',
    ]


def test_line():
    assert line(5, 9) == ' #  #'
    assert line(5, 30) == '#### '
    assert line(5, 9 | 30) == '#####'
