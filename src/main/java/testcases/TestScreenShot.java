package testcases;

import org.testng.annotations.Test;

import testbase.TestBase;

public class TestScreenShot extends TestBase{
	
	@Test
	public void testScreen() {
		driver.get("https://www.seleniumhq.org/download/");
		captureScreen("firstscreen", driver);
	}

}
