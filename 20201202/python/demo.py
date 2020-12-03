from itertools import combinations


def sums1(numbers):
    return sorted(set().union(*[
        { numbers[j] + numbers[i] for i in range(j + 1, len(numbers)) }
        for j in range(0, len(numbers) - 1)
    ]))


def sums2(numbers):
    return sorted({ x + y for x, y in combinations(numbers, 2) })
