package teli.util;

import java.util.Arrays;

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

    /**
     * Converts an integer into a byte array
     * @return bytes representation of integer
     */
    public static byte[] toByteArray(int value) {
        if (value < 0) {
            return new byte[]{(byte) (value >>> 24 & 0xFF), (byte) (value >>> 16 & 0xFF),
                    (byte) (value >>> 8 & 0xFF), (byte) (value & 0xFF)};
        } else if (value <= 0xFF) {
            return new byte[]{(byte) (value & 0xFF)};
        } else if (value <= 0xFFFF) {
            return new byte[]{(byte) (value >>> 8 & 0xFF), (byte) (value & 0xFF)};
        } else if (value <= 0xFFFFFF) {
            return new byte[]{(byte) (value >>> 16 & 0xFF), (byte) (value >>> 8 & 0xFF),
                    (byte) (value & 0xFF)};
        } else {
            return new byte[]{(byte) (value >>> 24 & 0xFF), (byte) (value >>> 16 & 0xFF),
                    (byte) (value >>> 8 & 0xFF), (byte) (value & 0xFF)};
        }
    }

    /**
     * Converts a byte array into an  32-bit signed integer.
     * <p>
     * if b is longer than 4 bytes, only the first 4 bytes (Big Endian) are converted.
     * @param b byte array to be converted
     * @return integer representation of bytes
     */
    public static int toInt(byte[] b) {
        if (b.length > 4){
            b = Arrays.copyOfRange(b, b.length - 4, b.length);
        }
        int value = 0;
        for (int i = 0; i < b.length; i++) {
            value += (b[i] & 0xFF) << (8 * (b.length - i - 1));
        }
        return value;
    }


}
