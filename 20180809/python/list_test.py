import unittest


class Node:
    def __init__(self, value, next=None):
        self.value = value
        self.next = next

    def last(self):
        node = self
        while node.next:
            node = node.next
        return node

    def append(self, value):
        new_node = Node(value)
        self.last().next = new_node
        return new_node

    def values(self):
        if self.next is None:
            return [self.value]
        return [self.value] + self.next.values()


class List:
    def __init__(self, values=[]):
        self._head = Node(None)
        node = self._head
        for i in values:
            new_node = Node(i)
            node.next = new_node
            node = new_node

    def first(self):
        return self._head.next

    def last(self):
        return self._head.last()

    def append(self, value):
        return self._head.append(value)

    def remove(self, target):
        node = self._head
        while node.next != target:
            node = node.next
        node.next = target.next

    def split(self, x):
        node = self._head
        r = None
        while node.next:
            if node.next.value < x:
                node = node.next
            else:
                right_node = node.next
                node.next = right_node.next
                right_node.next = r
                r = right_node
        return self, node, r

    def splited(self, x):
        l, n, r = self.split(x)
        n.next = r
        return l

    def values(self):
        return self.first().values()

    def nodes(self):
        nodes = []
        node = self.first()
        while node:
            nodes.append(node)
            node = node.next
        return nodes


class ListTest(unittest.TestCase):
    def test_values(self):
        list = List([1, 2, 3])
        self.assertEqual([1, 2, 3], list.values())

    def test_append(self):
        list = List()
        list.append(1).append(2).append(3)
        self.assertEqual([1, 2, 3], list.values())

    def test_remove(self):
        xs = [1, 2, 3]
        for i in range(len(xs)):
            list = List(xs)
            nodes = list.nodes()
            list.remove(nodes[i])
            self.assertEqual(xs[:i] + xs[i + 1:], list.values())

    def test_split(self):
        list = List([3, 5, 8, 5, 10, 2, 1])
        l, n, r = list.split(5)
        self.assertEqual([1, 2, 3], sorted(l.values()))
        self.assertEqual([5, 5, 8, 10], sorted(r.values()))
        self.assertEqual(None, n.next)

    def test_splited(self):
        list = List([3, 5, 8, 5, 10, 2, 1])
        values = list.splited(5).values()
        self.assertEqual([1, 2, 3], sorted(values[:3]))
        self.assertEqual([5, 5, 8, 10], sorted(values[3:]))


if __name__ == '__main__':
    unittest.main()
