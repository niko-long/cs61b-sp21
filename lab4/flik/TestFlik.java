package flik;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
public class TestFlik {

    @Test
    public void testRandom() {
        int a = 1000;
        int b = 1000;
        assertTrue(Flik.isSameNumber(a, b));
    }
}
