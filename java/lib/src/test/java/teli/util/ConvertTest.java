package teli.util;

import org.junit.Assert;
import org.junit.Test;

public class ConvertTest {

    @Test
    public void testToHexString() {
        byte[] b = new byte[]{0x01, 0x02, 0x03};
        byte[] b1 = new byte[0];
        byte[] b2 = new byte[]{(byte) 128, (byte) 256};

        Assert.assertEquals("010203",  Convert.toHexString(b));
        Assert.assertEquals("", Convert.toHexString(b1));
        Assert.assertEquals("8000", Convert.toHexString(b2));
    }

    @Test
    public void testfromHexString() {
        byte[] b = new byte[]{0x01, 0x02, 0x03};
        byte[] b1 = new byte[0];
        byte[] b2 = new byte[]{(byte) 128, (byte) 256};

        Assert.assertArrayEquals(b, Convert.fromHexString("010203"));
        Assert.assertArrayEquals(b1, Convert.fromHexString(""));
        Assert.assertArrayEquals(b2, Convert.fromHexString("8000"));
    }
}
