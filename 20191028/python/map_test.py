def solve(n, *maps):
    return [
        merge(format(line, f'0{n}b') for line in lines)
        for lines in zip(*maps)
    ]


def merge(lines):
    return ''.join(
        sum(map(int, xs)) and '#' or ' '
        for xs in zip(*map(list, lines))
    )


def test_sample():
    assert solve(5, [9], [30]) == ['#####']
    assert solve(5, [9, 20, 28, 18, 11], [30, 1, 21, 17, 28]) \
           == ['#####', '# # #', '### #', '#  ##', '#####']
    assert solve(6, [46, 33, 33, 22, 31, 50], [27, 56, 19, 14, 14, 10]) \
           == ['######', '###  #', '##  ##', ' #### ', ' #####', '### # ']


def test_merge():
    assert merge(['00000', '00001']) == '    #'
    assert merge(['00010', '00001']) == '   ##'
    assert merge(['00011', '00001']) == '   ##'
    assert merge(['10000', '00010']) == '#  # '
