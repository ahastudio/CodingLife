<?php

function test(callable $callback) {
    $before = microtime(true);
    $callback();
    $after = microtime(true);
    return $after - $before;
}
