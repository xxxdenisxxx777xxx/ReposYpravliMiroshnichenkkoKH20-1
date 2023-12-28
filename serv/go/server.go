package main

import (
	"fmt"
	"log"
	"net/http"
	"net/http/httputil"
	"os"
)

func handler(w http.ResponseWriter, req *http.Request) {
	dump, err := httputil.DumpRequest(req, true)
	if err != nil {
		http.Error(w, fmt.Sprint(err), http.StatusInternalServerError)
		return
	}
	fmt.Printf("%s\n\n", string(dump))
}

func startServer(host, port string) {
	listen := host + ":" + port
	fmt.Fprintf(os.Stderr, "Listen server on %s\n\n", listen)
	http.HandleFunc("/", handler)
	if err := http.ListenAndServe(listen, nil); err != nil {
		log.Fatal("Error! Server no start: ", err)
	}
}

func main() {
	host := "127.0.0.1"
	port := "8080"

	if len(os.Args) > 1 {
		host = os.Args[1]
	}

	if len(os.Args) > 2 {
		port = os.Args[2]
	}

	startServer(host, port)
}
