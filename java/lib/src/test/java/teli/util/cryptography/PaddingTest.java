package teli.util.cryptography;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import teli.util.ArrayUtil;
import teli.util.Convert;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PaddingTest {

    byte[] b0;
    byte[] b1;
    byte[] b2;

    @Before
    public void setUp() {
        Random random = new Random();

        b0 = new byte[0];
        b1 = new byte[8];
        b2 = new byte[random.nextInt(255)];

        random.nextBytes(b1);
        random.nextBytes(b2);
    }

    @Test
    public void testISO9797M1() {
        int BlockSize = 8;
        Arrays.asList(b0, b1, b2).forEach((byte[] b) -> {
            byte[] padded = PaddingMethods.ISO9797M1.pad(b, BlockSize);

            Assert.assertEquals(0, padded.length % BlockSize);

            if (b.length == 0)
                assertArrayEquals(new byte[BlockSize], padded);

            if (b.length % BlockSize == 0 && b.length > 0)
                assertArrayEquals(b, padded);

            if (b.length % BlockSize > 0) {
                byte[] rpad = new byte[BlockSize - b.length % BlockSize];
                assertArrayEquals(ArrayUtil.concat(b, rpad), padded);
            }

        });
    }

    @Test
    public void testISO9797M2() {
        int BlockSize = 8;
        Arrays.asList(b0, b1, b2).forEach((byte[] b) -> {
            byte[] padded = PaddingMethods.ISO9797M2.pad(b, BlockSize);

            Assert.assertEquals(0, padded.length % BlockSize);

            if (b.length == 0)
                assertArrayEquals(ArrayUtil.concat(new byte[]{((byte) 0x80)}, new byte[BlockSize - 1]), padded);

            if (b.length % BlockSize == 0 && b.length > 0)
                assertArrayEquals(ArrayUtil.concat(b, new byte[]{((byte) 0x80)}, new byte[BlockSize - 1]), padded);

            if (b.length % BlockSize > 0) {
                byte[] rpad = Arrays.copyOfRange(padded, b.length, padded.length);

                assertEquals(((byte) 0x80), rpad[0]);
                if (rpad.length > 1) {
                    assertArrayEquals(ArrayUtil.concat(new byte[]{(byte) 0x80}, new byte[BlockSize - b.length % BlockSize - 1]), rpad);
                }
                assertArrayEquals(ArrayUtil.concat(new byte[]{(byte) 0x80}, new byte[BlockSize - b.length % BlockSize - 1]), rpad );
            }

        });
    }


    @Test
    public void testISO9797M3() {
        int BlockSize = 8;
        Arrays.asList(b0, b1, b2).forEach((byte[] b) -> {
            byte[] padded = PaddingMethods.ISO9797M3.pad(b, BlockSize);

            Assert.assertEquals(0, padded.length % BlockSize);

            if (b.length == 0)
                assertArrayEquals(new byte[BlockSize * 2], padded);

            if (b.length % BlockSize == 0 && b.length > 0) {
                byte[] pad = Arrays.copyOfRange(padded, 0, padded.length - b.length);
                assertEquals(b.length, Convert.toInt(pad));
                assertArrayEquals(b, Arrays.copyOfRange(padded, padded.length - b.length, padded.length));
            }

            if (b.length % BlockSize > 0) {
                byte[] rpad = new byte[BlockSize - b.length % BlockSize];
                byte[] lpad = Arrays.copyOfRange(padded, 0, padded.length - b.length - rpad.length);
                assertEquals(b.length, Convert.toInt(lpad));
                assertArrayEquals(ArrayUtil.concat(lpad, b, rpad), padded);
            }

        });
    }
}
