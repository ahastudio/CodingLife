package main

import (
	"fmt"
	"io"
	"io/ioutil"
	"log"
	"net/http"
)

func greeting(w http.ResponseWriter, r *http.Request) {
	fmt.Println("Hello, world!")
	io.WriteString(w, "Hello, world!\n")
}

func echo(w http.ResponseWriter, r *http.Request) {
	defer r.Body.Close()

	body, err := ioutil.ReadAll(r.Body)
	if err != nil {
		log.Fatalln(err)
	}

	b := make([]byte, len(body)+1)
	for i, c := range body {
		b[i] = c
	}
	b[len(body)] = '\n'

	fmt.Println(string(body))
	io.WriteString(w, string(b))
}

func main() {
	http.HandleFunc("/", greeting)
	http.HandleFunc("/echo", echo)

	err := http.ListenAndServe(":3000", nil)
	if err != nil {
		log.Fatalln(err)
	}
}
