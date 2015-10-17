assert 55 == (1..10).inject { a, b -> a + b }

/*
def fib(n) {
  if (n == 1 || n == 2)
    1
  else
    fib(n - 1) + fib(n - 2)
}
*/

def fib(n) {
  (1..n).inject([0, 1]) { a, e -> [a[1], a[0] + a[1]] }[0]
}

assert [1, 1, 2, 3, 5, 8, 13, 21] == (1..8).collect { fib(it) }
