import org.junit.Assert;
import org.junit.Test;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MainTEST {
    @Test
    public void unitTest1() {
        ArrayList<BigInteger> expectedDivisors = new ArrayList<>(
                List.of(new BigInteger("1"),
                        new BigInteger("2"),
                        new BigInteger("11"),
                        new BigInteger("22")));
        ArrayList<BigInteger> actualDivisors = Main.getDivisors(new BigInteger("22"));
        Assert.assertEquals(actualDivisors, expectedDivisors);
    }

    @Test
    public void unitTest2() {
        boolean actualResult = Main.isPrimitive(new BigInteger("5"), new BigInteger("23"), Main.getDivisors(new BigInteger("22")));
        Assert.assertTrue(actualResult);
        actualResult = Main.isPrimitive(new BigInteger("6"), new BigInteger("23"), Main.getDivisors(new BigInteger("22")));
        Assert.assertFalse(actualResult);
    }

    @Test
    public void unitTest3() {
        BigInteger primitiveRoot = Main.generatePrimitiveRoot(new BigInteger("23"));
        System.out.println("Primitive root: " + primitiveRoot);
        Assert.assertTrue(Main.isPrimitive(primitiveRoot, new BigInteger("23"), Main.getDivisors(new BigInteger("22"))));
    }
}