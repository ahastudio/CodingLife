// This is a HTTP server for the Docker class.
package main

import (
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"net/url"
	"strconv"
)

// Add returns the sum of numbers(x, y, z).
func Add(params url.Values) int {
	result := 0
	for _, i := range []string{"x", "y", "z"} {
		v, err := strconv.Atoi(params.Get(i))
		if err != nil {
			continue
		}
		result += v
	}
	return result
}

func renderJSON(w io.Writer, data interface{}) {
	err := json.NewEncoder(w).Encode(data)
	if err != nil {
		fmt.Println(err)
	}
}

func main() {
	fmt.Println("Hello, world!")

	fmt.Println("http://localhost:8080")

	http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		fmt.Fprintln(w, "Welcome!")
	})

	http.HandleFunc("/add", func(w http.ResponseWriter, r *http.Request) {
		params := r.URL.Query()
		result := Add(params)
		data := map[string]interface{}{"status": "ok", "result": result}
		renderJSON(w, data)
	})

	http.ListenAndServe(":8080", nil)
}
