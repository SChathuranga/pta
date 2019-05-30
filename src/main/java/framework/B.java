package framework;

import org.testng.Assert;
import org.testng.annotations.Test;

import testbase.TestBase;

public class B extends TestBase{
	
	int i =1;
	
	@Test
	public void testOpen() {
		if(i==3) {
			Assert.assertTrue(true);
		}else {
			i++;
			Assert.assertTrue(false);
		}
	}

}
