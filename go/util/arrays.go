package util

import "fmt"

// AddPadding takes a byte array and adds padding to it so that the length of the array is a multiple of the block size.
func AddPadding(input *[]byte, size int) {
	len := len(*input)
	pad := size - (len % size)

	if pad == size {
		return
	}
	padded := make([]byte, len+pad)
	copy(padded, *input)

	*input = padded
}

// XorBytes takes two byte arrays and returns a new byte array with the XOR of the two arrays.
// The two arrays must be the same length.
func XorBytes(a, b []byte) []byte {
	if len(a) != len(b) {
		panic("byte arrays must have the same length")
	}

	out := make([]byte, len(a))
	for i := range a {
		out[i] = a[i] ^ b[i]
	}
	return out
}

// GetBlocks takes []T with T is either a byte or uint64, and a block size int
// returns a 2D array of T:  [][size]T
// input length must be a multiple of block size
func GetBlocks[K byte | uint64](input []K, size int) ([][]K, error) {
	s := size
	if len(input)%s != 0 {
		return nil, fmt.Errorf("input length must be a multiple of %d", size)
	}

	blocks := make([][]K, len(input)/s)

	for i, v := range input {
		if blocks[i/s] == nil {
			blocks[i/s] = make([]K, size)
		}
		blocks[i/s][i%s] = v
	}

	return blocks, nil
}
