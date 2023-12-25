package teli.util;

public class Convert {

    /**
     * @param b byte array to be converted
     * @return Lowercase Hex String representation of b
     */
    static public String toHexString(byte[] b) {
        return toHexString(b, 0, b.length);
    }

    /**
     * @param b      byte array to be converted
     * @param offset starting offset
     * @param length number of bytes to convert, must be lower than b.length - offset
     * @return Lowercase Hex String representation of the array slice
     */
    static public String toHexString(byte[] b, int offset, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = offset; i < offset + length; i++) {
            sb.append(String.format("%02x", b[i] & 0xff));
        }
        return sb.toString();
    }

    /**
     * @param s Hex String to be converted
     * @return byte array representation of s
     */
    static public byte[] fromHexString(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
        }
        return b;
    }


}
