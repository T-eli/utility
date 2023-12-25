package teli.util;

public class ArrayUtil {

    /**
     * Concatenate byte arrays.
     * Null arrays are ignored.
     *
     * @param arrays the arrays to concatenate
     * @return new array with the concatenated data
     */
    static public byte[] concat(byte[]... arrays) {
        int length = 0;
        for (byte[] array : arrays) {
            if (array == null) continue;
            length += array.length;
        }
        byte[] b = new byte[length];
        int offset = 0;
        for (byte[] array : arrays) {
            if (array == null) continue;
            System.arraycopy(array, 0, b, offset, array.length);
            offset += array.length;
        }
        return b;
    }

    /**
     * Append bytes to byte array.
     *
     * @param array the array to append to
     * @param b  bytes to append
     * @return new array with the appended data
     */
    @SuppressWarnings("UnusedReturnValue")
    static public byte[] append(byte[] array, byte... b) {
        return ArrayUtil.concat(array, b);
    }




}
