# -*- coding: utf-8 -*-

import unittest


HOME = (50, 50)

cache = {}


class Xmas:
    def __init__(self):
        self.points = []

    def add(self, x, y):
        self.points.append((x, y))

    def shortest(self):
        def iter(length, way, points):
            if len(points) is 0:
                return (length + self.distance(way[-1], HOME), way + [HOME])
            else:
                key = '-'.join(str(x) for x in points)
                if key not in cache:
                    cache[key] = min(
                        [iter(length + self.distance(way[-1], point),
                              way + [point],
                              points[:index] + points[index + 1:])
                         for index, point in enumerate(points)],
                        key=lambda x: x[0])
                return cache[key]
        cache = {}
        return iter(0, [HOME], self.points)[1]

    def distance(self, a, b):
        return (a[0] - b[0]) ** 2 + (a[1] - b[1]) ** 2


class TestOilKing(unittest.TestCase):
    def test_shortest_pass(self):
        points = Xmas()
        points.add(50 - 1, 50)
        points.add(50 + 2, 50)
        points.add(50 + 3, 50)
        self.assertEqual([
            (50, 50), (50 - 1, 50), (50 + 2, 50), (50 + 3, 50), (50, 50)
        ], points.shortest())

    def test_sample(self):
        data = '26 23 50 95 85 66 67 40 46 12 6 2 51 12 44 30 4 56 61 14 '
        #       '73 6 74 72 51 52 7 40 31 66 8 16 62 70 87 19 32 77 56 67 '
        #       '86 50 0 74 65 42 19 97 49 14 4 11 0 19 17 37 46 33 12 90'
        a = [int(x) for x in data.split()]
        points = Xmas()
        for x, y in zip(a[::2], a[1::2]):
            points.add(x, y)
        way = points.shortest()
        self.assertEqual((50, 50), way[0])
        self.assertEqual((50, 50), way[-1])
        # 중간이 망함. 열라 오래 걸림 ㅠㅠ


if __name__ == '__main__':
    unittest.main()
