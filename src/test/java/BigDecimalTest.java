import org.junit.Test;

import static org.junit.Assert.*;


public class BigDecimalTest {

    @Test(expected = NullPointerException.class)
    public void testNullPointer() {
        BigDecimal.parseExp(null, 0, 0);
    }

    public void testArrayException() {//
        assertTrue(assertArrayException(new char[] {}, 0, 0));
        assertTrue(assertArrayException("1.1E1".toCharArray(), 3, 3));
        assertTrue(assertArrayException("1E1".toCharArray(), 1, 3));
        assertTrue(assertArrayException("1E1".toCharArray(), 3, 1));
        assertTrue(assertArrayException("1E1".toCharArray(), 2, 2));
        assertTrue(assertArrayException("1E1".toCharArray(), 3, 0));
        assertTrue(assertArrayException("1E12345123451".toCharArray(), 1, 12));
        assertTrue(assertArrayException("1E1".toCharArray(), 3, 3));
        assertTrue(assertArrayException("1.23-02".toCharArray(), 4, 4));
        assertTrue(assertArrayException("1..23E02".toCharArray(), 5, 2));
    }

    @Test
    public void testNumberException() {
        assertTrue(assertNumberException("1E1".toCharArray(), 1, 1));
        assertTrue(assertNumberException("1E1".toCharArray(), 1, 0));
        assertTrue(assertNumberException("1E1".toCharArray(), 1, -1));
        assertTrue(assertNumberException("1E1".toCharArray(), 0, 2));
        assertTrue(assertNumberException("1E12345123451".toCharArray(), 1, 12));

        assertTrue(assertNumberException("1E12345123451".toCharArray(), 3, 1));
        assertTrue(assertNumberException("1E12345678912".toCharArray(), 1, 12));
        assertTrue(assertNumberException("1.23E-f02".toCharArray(), 4, 4));
        assertTrue(assertNumberException("1.23EE02".toCharArray(), 4, 4));
        assertTrue(assertNumberException("1.23-f02".toCharArray(), 4, 4));
        assertTrue(assertNumberException("1.23-f02".toCharArray(), 4, 2));


    }

    @Test
    public void testSimple() {
        assertEquals(0, BigDecimal.parseExp("1.23ыE02".toCharArray(), 5, 2));
        assertEquals(2, BigDecimal.parseExp("1.23ыE02".toCharArray(), 5, 3));

        assertEquals(1, BigDecimal.parseExp("1E1".toCharArray(), 1, 2));
        assertEquals(1, BigDecimal.parseExp("1.1E1".toCharArray(), 3, 2));

        assertEquals(-2, BigDecimal.parseExp("1.23E-2".toCharArray(), 4, 3)); // E-2
        assertEquals(2, BigDecimal.parseExp("1.23E02".toCharArray(), 4, 3)); // E-2
        assertEquals(-2, BigDecimal.parseExp("1.23E-02".toCharArray(), 4, 4)); // E-2


        assertEquals(1, BigDecimal.parseExp("1.1E1".toCharArray(), -1, 2));

        assertEquals(34, BigDecimal.parseExp("1E12345123451".toCharArray(), 3, 3));
        assertEquals(345, BigDecimal.parseExp("1E12345123451".toCharArray(), 3, 4));
        assertEquals(3, BigDecimal.parseExp("1E12345123451".toCharArray(), 3, 2));
        assertEquals(12345, BigDecimal.parseExp("1E12345".toCharArray(), 1, 6));
        assertEquals(123456, BigDecimal.parseExp("1E123456".toCharArray(), 1, 7));
        assertEquals(1234567, BigDecimal.parseExp("1E1234567".toCharArray(), 1, 8));
        assertEquals(12345678, BigDecimal.parseExp("1E12345678".toCharArray(), 1, 9));
        assertEquals(123456789, BigDecimal.parseExp("1E123456789".toCharArray(), 1, 10));
        assertEquals(1234567891, BigDecimal.parseExp("1E1234567891".toCharArray(), 1, 11));

    }

    @Test
    public void bigDecimalTest() {
        // char format : number in E-notation : 1.257e42
        // offset : number of char in the decimal part : 1.257 = 5
        // length : number of digit in the exponent + 1 : e42 = 3
        // length + offset = char[] length
        assertEquals(-2, BigDecimal.parseExp("1.23E-2".toCharArray(), 4, 3)); // E-2
        assertEquals(+2, BigDecimal.parseExp("1.23E+2".toCharArray(), 4, 3)); // E+2
        assertTrue(assertNumberException("1.23E+2".toCharArray(), 4, 0)); // len = 0
        assertEquals(42, BigDecimal.parseExp("1E000000000042".toCharArray(), 2, 12)); // len > 10 & 000...
        assertTrue(assertNumberException("1E12345123451".toCharArray(), 2, 12)); // lan > 10 with no 0 (too big exponent)
        assertTrue(assertNumberException("1.23E1a3".toCharArray(), 4, 4)); // E1a4 is not an exponent
    }

    // Do you have a better solution and want to share it ? Send me a PM or make a pull request
    private boolean assertNumberException(char[] in, int offset, int len) {
        Exception e = null;
        try {
            BigDecimal.parseExp(in, offset, len);
        } catch (NumberFormatException caughtException) {
            e = caughtException;
        }
        return e != null; // True if exception was thrown as expected
    }

    // Do you have a better solution and want to share it ? Send me a PM or make a pull request
    private boolean assertArrayException(char[] in, int offset, int len) {
        Exception e = null;
        try {
            BigDecimal.parseExp(in, offset, len);
        } catch (ArrayIndexOutOfBoundsException caughtException) {
            e = caughtException;
        }
        return e != null; // True if exception was thrown as expected
    }

}
