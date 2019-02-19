<?php

require_once('./test.php');

$items = array_fill(0, 1000 * 1000, 1);

$fs = [];

$fs[1] = function () use($items) {
    $acc = 0;
    foreach ($items as $key => $_) {
        $acc += $key;
    }
};

$fs[2] = function () use($items) {
    $acc = 0;
    foreach (array_keys($items) as $key) {
        $acc += $key;
    }
};

$fs[3] = function () use($items) {
    $acc = 0;
    $keys = array_keys($items);
    $size = count($keys);
    for ($i = 0; $i < $size; $i++) {
        $acc += $keys[$i];
    }
};

$times = range(1, 10);
array_walk($times, function() use($fs, $items) {
    $indices = range(1, count($fs));
    shuffle($indices);

    $result = [];

    foreach ($indices as $i) {
        echo "$i ";
        reset($items);
        $result[] = [test($fs[$i]), $i];
    }

    asort($result);

    foreach ($result as [$t, $i]) {
        printf("\t%d - %.5f", $i, $t);
    }

    echo "\n";
});

echo "\n";
