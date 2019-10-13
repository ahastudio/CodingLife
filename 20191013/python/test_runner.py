from collections import Counter


def solution1(participant, completion):
    # 사람 이름 Group By!
    runners = {}
    for runner in participant:
        runners[runner] = runners.get(runner, 0) + 1
    # 완주한 사람을 빼기
    for runner in completion:
        runners[runner] -= 1
    # 남아있는 사람 찾기
    for runner, count in runners.items():
        if count > 0:
            return runner
    # 혹시라도 못 찾으면 완주 못한 사람 없음
    return ''


def solution2(participant, completion):
    # 갯수를 모으는 부분을 최적화
    runners = Counter(participant)
    for runner in completion:
        runners[runner] -= 1
    for runner, count in runners.items():
        if count > 0:
            return runner
    return ''


def solution3(participant, completion):
    # 갯수를 모으는 부분을 최적화
    runners = Counter(participant)
    # 갯수를 빼는 부분을 최적화
    runners -= Counter(completion)
    for runner, count in runners.items():
        if count > 0:
            return runner
    return ''


def solution4(participant, completion):
    # 갯수를 모으는 부분을 최적화
    runners = Counter(participant)
    # 갯수를 빼는 부분을 최적화
    runners -= Counter(completion)
    # 남은 사람 얻는 부분을 최적화
    return next(runners.elements())


def solution(participant, completion):
    return next((Counter(participant) - Counter(completion)).elements())


def test_solution():
    assert solution(
        ['leo', 'kiki', 'eden'],
        ['eden', 'kiki']
    ) == 'leo'
    assert solution(
        ['marina', 'josipa', 'nikola', 'vinko', 'filipa'],
        ['josipa', 'filipa', 'marina', 'nikola']
    ) == 'vinko'
    assert solution(
        ['mislav', 'stanko', 'mislav', 'ana'],
        ['stanko', 'ana', 'mislav']
    ) == 'mislav'
