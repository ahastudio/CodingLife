console.log(2 == 1 + 1);

// 아래는 에러!
// Operator '==' cannot be applied to types 'string' and 'number'.
// console.log('2' == 1 + 1);

console.log('31' == '' + 3 + 1);

var add = (a, b): number => {
    return a + b;
}

console.log(5 == add(2, 3));

// 아래는 에러!
// Operator '==' cannot be applied to types 'string' and 'number'.
// console.log('5' == add(2, 3));
