<?php

function test(callable $fn) {
    $before = microtime(true);
    $fn();
    $after = microtime(true);
    return $after - $before;
}
