# import math

MAX_PROBLEMS = 10_000

PATTERNS = [
    [1, 2, 3, 4, 5],
    [2, 1, 2, 3, 2, 4, 2, 5],
    [3, 3, 1, 1, 2, 2, 4, 4, 5, 5],
]


def solution(answers):
    # 1. 첫 번째 풀이
    # scores = []
    # for pattern in PATTERNS:
    #     scores.append(score(answers, pattern))
    # return good_students(scores)

    # 2. 두 번째 풀이
    return good_students([score(answers, pattern) for pattern in PATTERNS])


def score(answers, pattern):
    # 1. 첫 번째 풀이
    # count = 0
    # for i, answer in enumerate(answers):
    #     if pattern[i % len(pattern)] == answer:
    #         count += 1
    # return count

    # 2. 두 번째 풀이
    # student_answers = pattern * math.ceil(len(answers) / len(pattern))
    # return sum(a == b for a, b in zip(answers, student_answers))

    # 3. 세 번째 풀이
    # return sum(pattern[i % len(pattern)] == x for i, x in enumerate(answers))

    # 4. 네 번째 풀이
    return sum(a == b for a, b in zip(answers, pattern * MAX_PROBLEMS))


def good_students(scores):
    # 1. 첫 번째 풀이
    # max_score = 0
    # students = []
    # for i, score in enumerate(scores):
    #     if score == max_score:
    #         students.append(i + 1)
    #     if score > max_score:
    #         max_score = score
    #         students = [i + 1]
    # return students

    # 2. 두 번째 풀이
    max_score = max(scores)
    return [i + 1 for i, score in enumerate(scores) if score == max_score]


def test_sample():
    assert solution([1, 2, 3, 4, 5]) == [1]
    assert solution([1, 3, 2, 4, 2]) == [1, 2, 3]


def test_score():
    assert score([1, 2], [1, 2, 3]) == 2
    assert score([1, 2, 3], [1, 2]) == 2
    assert score([1, 2, 1, 2, 1], [1, 2]) == 5


def test_good_students():
    assert good_students([2, 5, 0]) == [2]
    assert good_students([5, 0, 0]) == [1]
    assert good_students([5, 0, 5]) == [1, 3]
