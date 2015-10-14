def notDuplicateNumbers(numbers) {
  numbers.split('').collect { it.toInteger() }.sort() == (0..9)
}

assert notDuplicateNumbers("0123456789")
assert notDuplicateNumbers("1234567890")

assert !notDuplicateNumbers("1234")
assert !notDuplicateNumbers("01234567890")

println "OK!"
