package framework;

import org.testng.Assert;
import org.testng.annotations.Test;

import testbase.TestBase;

public class A extends TestBase{
	
	@Test
	public void testLogin() {
		Assert.assertTrue(true);
	}

}
