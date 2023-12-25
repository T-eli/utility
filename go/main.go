package main

import "fmt"

func main() {
	var b uint
	var a int = -10

	b = uint(a)

	fmt.Println(a)
	fmt.Println(b)
}
