<?php

require_once('./test.php');

$items = array_fill(0, 1000 * 1000, 1);

$fs = [];

$fs[1] = function () use($items) {
    $acc = 0;
    foreach ($items as $_ => $value) {
        $acc += $value;
    }
};

$fs[2] = function () use($items) {
    $acc = 0;
    foreach ($items as $value) {
        $acc += $value;
    }
};

$fs[3] = function () use($items) {
    $acc = 0;
    foreach (array_values($items) as $value) {
        $acc += $value;
    }
};

$fs[4] = function () use($items) {
    $acc = 0;
    $size = count($items);
    for ($i = 0; $i < $size; $i++) {
        $acc += $items[$i];
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
