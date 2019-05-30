package framework;

import org.testng.Assert;
import org.testng.annotations.Test;

import testbase.TestBase;

public class C extends TestBase{
	
	int i=1;
	
	@Test
	public void testNavigate() {
			i++;
			Assert.assertTrue(false);	
	}
}
