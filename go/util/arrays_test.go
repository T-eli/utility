package util_test

import (
	"testing"

	"github.com/T-eli/utility/util"
	"github.com/stretchr/testify/require"
)

func Test_Arrays(t *testing.T) {

	t.Run("add padding", func(t *testing.T) {
		data := make([]byte, 27)
		blocksize := 8

		util.AddPadding(&data, blocksize)

		require.Truef(t, len(data)%blocksize == 0, "error: %v", "padding failed")

	})

	t.Run("xor arrays", func(t *testing.T) {
		arr1 := []byte{0x01, 0x02, 0x01}
		arr2 := []byte{0x01, 0x02, 0x02}

		result := util.XorBytes(arr1, arr2)

		require.Equal(t, []byte{0x00, 0x00, 0x03}, result)
		t.Log("xor success")
	})

	t.Run("Get blocks", func(t *testing.T) {
		data := make([]byte, 24)
		blocksize := 8

		blocks, err := util.GetBlocks(data, blocksize)

		if err != nil {
			t.Fatalf("error: %v", err)
		}

		require.Truef(t, len(blocks) == 3, "error: %v", "blocks failed")
		t.Log("Create blocks success")
	})
}
