package testcases;



import java.net.MalformedURLException;

import org.testng.annotations.Test;

import testbase.TestBase;

public class LinkTest extends TestBase{
	
	@Test
	public void logintest() throws InterruptedException, MalformedURLException {
//		System.out.println(ObjectReader.reader.getURL());
//		getApplicationURL(ObjectReader.reader.getURL());
//		TestBase.logExtentReport("Browser Launched");
//		
//		LandingPage landingpage = new LandingPage(driver);
//		
//		SignInPage signinpage = landingpage.clickOnAccountLink();
//		signinpage.typeEmail(ObjectReader.reader.getEmail());
//		signinpage.typePassword(ObjectReader.reader.getPassword());
//		
//		HomePage homepage = signinpage.clickOnSignInButton();
//		Assert.assertTrue(homepage.verifySuccessLogin());
//		homepage.mouseOverDepartmentLink();
//		DepartmentPage departmentPage = homepage.clickOnDepartment("Baby");
//		departmentPage.mouseOverToCategory("NURSERY");
//		CategoryPage categoryPage = departmentPage.clickOnCategory("NURSERY");
//		categoryPage.mouseOverToProduct(8);
//		categoryPage.clickOnProduct(8);
//		Thread.sleep(8000);
		
		OpenDesktopApp("Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
		Click_Desktop("One");
		Click_Desktop("Plus");
		Click_Desktop("Seven");
		Click_Desktop("Equals");
	}
	
}
