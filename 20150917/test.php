<?php
echo array_sum(range(1, 10));
echo "\n";

echo array_sum(array_map(function($i) { return $i * 2; }, range(1, 10)));
echo "\n";

function even($num) {
  return $num % 2 == 0;
}

echo array_sum(array_filter(range(1, 20), even));
echo "\n";

function fib($n) {
  return array_reduce(range(1, $n), function($a, $e) {
    return array($a[1], $a[0] + $a[1]);
  }, array(0, 1))[0];
}

for ($i = 1; $i <= 10; $i++) {
  echo "fib($i) = " . fib($i) . "\n";
}
