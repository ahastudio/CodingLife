package main

import (
	"fmt"
	"net/url"
)

func ExampleAdd() {
	fmt.Println(Add(url.Values{}))
	fmt.Println(Add(url.Values{"x": {"3"}}))
	fmt.Println(Add(url.Values{"x": {"3"}, "y": {"2", "3", "4"}}))
	fmt.Println(Add(url.Values{"x": {"3"}, "y": {"2"}, "z": {"-1"}}))

	// Output:
	// 0
	// 3
	// 5
	// 4
}
