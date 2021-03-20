import org.testng.annotations.Test;
import org.testng.Assert;

@Test
public class TestStringCalculator {

    public static void testStringCalculator(StringCalculator calci) {
        System.out.print("Test for Empty string");
        Assert.assertEquals(calci.Add(""), 0);
        System.out.println(": OK!");

        System.out.print("Test for Single number string");
        Assert.assertEquals(calci.Add("1"), 1);
        System.out.println(": OK!");

        System.out.print("Test for Two numbers string (comma-delimited)");
        Assert.assertEquals(calci.Add("1,2"), 3);
        System.out.println(": OK!");

        System.out.print("Test for Two numbers string (newline-delimited)");
        Assert.assertEquals(calci.Add("1\n2"), 3);
        System.out.println(": OK!");

        System.out.print("Test for Two numbers string (comma or newline delimited)");
        Assert.assertEquals(calci.Add("1\n2,3"), 6);
        System.out.println(": OK!");

        System.out.print("Test for Two numbers string (comma or newline delimited) beginning with a new delimiter");
        Assert.assertEquals(calci.Add("//;\n1;2"), 3);
        System.out.println(": OK!");

        System.out.print("Test for Negative numbers");
        try {
            calci.Add("//;\n1;-2;-9,2");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Negative numbers not allowed: -2,-9");
        }
        System.out.println(": OK!");

        System.out.print("Test for GetCalledCount");
        Assert.assertEquals(calci.GetCalledCount(), 7);
        System.out.println(": OK!");

        System.out.print("Test for numbers greater than 1000");
        Assert.assertEquals(calci.Add("1\n2000"), 1);
        System.out.println(": OK!");

        System.out.print("Test for delimiters of different size and multiple delimiters.");
        Assert.assertEquals(calci.Add("//[***]\n1***2***3"), 6);
        System.out.println(": OK!");

    }

    public static void main(String[] args) {
        StringCalculator calci = new StringCalculator();
        TestStringCalculator.testStringCalculator(calci);
    }
}
