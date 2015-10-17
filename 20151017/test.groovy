assert 55 == (1..10).inject { a, b -> a + b }

/*
def fib(n) {
  if (n == 1 || n == 2)
    1
  else
    fib(n - 1) + fib(n - 2)
}
*/

def fib = { n -> (1..n).inject([0, 1]) { a, e -> [a[1], a[0] + a[1]] }[0] }

def n = 8
def expected = [1, 1, 2, 3, 5, 8, 13, 21]

assert expected == (1..n).collect { fib(it) }

// Destructuring using Meta-programming

def (first, second) = [1, 2, 3]
assert [1, 2] == [first, second]

java.util.List.metaClass.destructure = {-> [get(0), subList(1, size())] }

def (head, tail) = [1, 2, 3].destructure()
assert [1, [2, 3]] == [head, tail]

println "OK!"
