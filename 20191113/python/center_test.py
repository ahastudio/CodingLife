def center(text):
    size = len(text)
    middle = size // 2 - (is_even(size) and 1 or 0)
    return text[middle:middle and -middle or None]


def is_even(n):
    return n % 2 == 0


def test_sample():
    assert center('abcde') == 'c'
    assert center('qwer') == 'we'
    assert center('') == ''
    assert center('1') == '1'
    assert center('12') == '12'
    assert center('123456789') == '5'
    assert center('1234567890') == '56'
