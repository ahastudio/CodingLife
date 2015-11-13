// Quick Sort

function qsort(as: Array<Number>) {
    if (as.length <= 1) {
        return as;
    }
    var pivot = as[as.length - 1];
    var middle = [pivot];
    var left = [];
    var right = [];
    as.slice(0, as.length - 1).forEach((i) => {
        if (i == pivot) {
            middle.push(i);
        } else if (i < pivot) {
            left.push(i);
        } else {
            right.push(i);
        }
    });
    return qsort(left).concat(middle, qsort(right));
}

console.log('Quick Sort');
console.log(qsort([1, 2, 3, 4, 5, 6, 7, 8, 9]));
console.log(qsort([9, 8, 7, 6, 5, 4, 3, 2, 1]));

// Merge Sort

function msort(as: Array<Number>) {
    if (as.length <= 1) {
        return as;
    }
    var left = msort(as.slice(0, as.length / 2));
    var right = msort(as.slice(as.length / 2));
    var result = [];
    while (left.length && right.length) {
        if (left[0] < right[0]) {
            result.push(left[0]);
            left = left.slice(1);
        } else {
            result.push(right[0]);
            right = right.slice(1);
        }
    }
    return result.concat(left, right);
}

console.log('Merge Sort');
console.log(msort([1, 2, 3, 4, 5, 6, 7, 8, 9]));
console.log(msort([9, 8, 7, 6, 5, 4, 3, 2, 1]));
