from datetime import datetime
from random import randint

# from pytest_benchmark.fixture import BenchmarkFixture


def calculate_max_area1(values: list[int]) -> int:
    max_area = 0
    area = 0

    for y in set(values):
        for i in values:
            if i < y:
                max_area = max(max_area, area)
                area = 0
                continue
            area += y
        max_area = max(max_area, area)
        area = 0

    return max_area


def calculate_max_area2(values: list[int]) -> int:
    max_area = 0
    pairs: list[tuple[int, int]] = []

    def area(index: int):
        y, x = pairs[-1]
        return y * (index - x)

    for index, value in enumerate(values):
        y = pairs[-1][0] if pairs else 0
        if value > y:
            pairs.append((value, index))
        if value < y:
            max_area = max(max_area, area(index))
            pairs.pop()

    while pairs:
        max_area = max(max_area, area(len(values)))
        pairs.pop()

    return max_area


def test_calculate_max_area():
    for calculate_max_area in [calculate_max_area1, calculate_max_area2]:
        assert calculate_max_area([]) == 0
        assert calculate_max_area([0]) == 0
        assert calculate_max_area([1]) == 1
        assert calculate_max_area([3]) == 3
        assert calculate_max_area([1, 2, 3]) == 4
        assert calculate_max_area([1, 2, 3, 4, 2, 2, 1]) == 2 * 5
        assert calculate_max_area([1, 4, 5, 4, 2, 4, 1]) == 4 * 3
        assert calculate_max_area([1, 2, 3, 2, 1, 2, 1]) == 7


def test_calculate_max_area1(benchmark):
    values = [randint(1, 100) for _ in range(10_000)]
    benchmark(lambda: calculate_max_area1(values))


def test_calculate_max_area2(benchmark):
    values = [randint(1, 100) for _ in range(10_000)]
    benchmark(lambda: calculate_max_area2(values))


def test_max_area_performance():
    print()
    values = [randint(1, 100) for _ in range(10_000)]
    for calculate_max_area in [calculate_max_area1, calculate_max_area2]:
        old_time = datetime.now()
        calculate_max_area(values)
        new_time = datetime.now()
        print("▶", calculate_max_area.__name__, "→", new_time - old_time)
