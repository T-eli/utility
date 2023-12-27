package teli.util.cryptography;

import teli.util.ArrayUtil;
import teli.util.Convert;

public class PaddingMethods {
    static public Padding<byte[]> ISO9797M1 = (byte[] data, int n) -> {
        int pad = data.length % n == 0 && data.length > 0 ? 0 : n - (data.length % n);
        byte[] padded = new byte[data.length + pad];
        System.arraycopy(data, 0, padded, 0, data.length);
        return padded;
    };

    static public Padding<byte[]> ISO9797M2 = (byte[] data, int n) -> {
        data = ArrayUtil.append(data, (byte) 0x80);
        return ISO9797M1.pad(data, n);
    };

    static public Padding<byte[]> ISO9797M3 = (byte[] data, int n) -> {
        byte[] padded = ISO9797M1.pad(data, n);
        byte[] l = Convert.toByteArray(data.length);
        byte[] Ld = ArrayUtil.concat(new byte[l.length % n == 0 ? 0 : n - (l.length % n)], l);
        return ArrayUtil.concat(Ld, padded);
    };

}
