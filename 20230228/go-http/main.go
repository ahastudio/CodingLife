package main

import (
	"fmt"
	"io"
	"net/http"
)

const PORT = "8080"

func main() {
	http.HandleFunc("/", getRoot)
	http.HandleFunc("/hello", getHello)

	fmt.Println("Server is listening on http://localhost:" + PORT)

	err := http.ListenAndServe(":"+PORT, nil)
	if err != nil {
		panic(err)
	}
}

func getRoot(w http.ResponseWriter, r *http.Request) {
	fmt.Printf("GET /\n")
	io.WriteString(w, "Hello, world!\n")
}

func getHello(w http.ResponseWriter, r *http.Request) {
	fmt.Printf("GET /hello\n")
	io.WriteString(w, "Hello, HTTP!\n")
}
