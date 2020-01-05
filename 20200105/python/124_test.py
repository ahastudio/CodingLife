def solution(n):
    if n <= 3:
        return '124'[n - 1]
    return solution((n - 1) // 3) + solution(n % 3)


# test ------------------------

def test_sample():
    assert solution(1) == '1'
    assert solution(2) == '2'
    assert solution(3) == '4'
    assert solution(4) == '11'
    assert solution(5) == '12'
    assert solution(6) == '14'
    assert solution(7) == '21'
    assert solution(8) == '22'
    assert solution(9) == '24'
    assert solution(10) == '41'
    assert solution(11) == '42'
    assert solution(12) == '44'
    assert solution(13) == '111'
