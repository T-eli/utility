package teli.util;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilTest {

    @Test
    public void testConcat() {
        byte[] b0 = new byte[0];
        byte[] b1 = new byte[]{0x01, 0x02, 0x03};

        Assert.assertArrayEquals(b0, ArrayUtil.concat(null, null));
        Assert.assertArrayEquals(b0, ArrayUtil.concat(b0, b0));
        Assert.assertArrayEquals(b1, ArrayUtil.concat(b0, null, b1));
        Assert.assertArrayEquals(b1, ArrayUtil.concat(b0, b1));
        Assert.assertArrayEquals(new byte[]{0x01, 0x02, 0x03, 0x01, 0x02, 0x03}, ArrayUtil.concat(b1, b1));

    }

    @Test
    public void testAppend() {
        byte[] b0 = new byte[0];

        b0 = ArrayUtil.append(b0, (byte) 1, (byte) 2, (byte) 3);
        Assert.assertArrayEquals(new byte[]{1, 2, 3}, b0);

        b0 = ArrayUtil.append(b0, ((byte) 4));
        Assert.assertArrayEquals(new byte[]{0x01, 0x02, 0x03, 0x04}, b0);


    }
}
