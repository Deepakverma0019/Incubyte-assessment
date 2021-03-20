import org.testing.Assert.assertEquals;
import org.testing.annotations.Test;


@Test
public class TestStringCalculator {

	StringCalculator calci = new StringCalculator();
	assertEquals(0, calci.Add(""));
	//Assert.assertEquals(1, calci.Add("1"));
	//Assert.assertEquals(3, calci.Add("1,2"));
}
