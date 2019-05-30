package testcases;



import org.testng.Assert;
import org.testng.annotations.Test;

import helper.browserconfiguration.config.ObjectReader;
import pageObjects.CategoryPage;
import pageObjects.DepartmentPage;
import pageObjects.HomePage;
import pageObjects.LandingPage;
import pageObjects.SignInPage;
import testbase.TestBase;

public class LinkTest extends TestBase{
	
	@Test
	public void logintest() throws InterruptedException {
		System.out.println(ObjectReader.reader.getURL());
		getApplicationURL(ObjectReader.reader.getURL());
		TestBase.logExtentReport("Browser Launched");
		
		LandingPage landingpage = new LandingPage(driver);
		
		SignInPage signinpage = landingpage.clickOnAccountLink();
		signinpage.typeEmail(ObjectReader.reader.getEmail());
		signinpage.typePassword(ObjectReader.reader.getPassword());
		
		HomePage homepage = signinpage.clickOnSignInButton();
		Assert.assertTrue(homepage.verifySuccessLogin());
		homepage.mouseOverDepartmentLink();
		DepartmentPage departmentPage = homepage.clickOnDepartment("Baby");
		departmentPage.mouseOverToCategory("NURSERY");
		CategoryPage categoryPage = departmentPage.clickOnCategory("NURSERY");
		categoryPage.mouseOverToProduct(8);
		categoryPage.clickOnProduct(8);
		Thread.sleep(8000);
	}
	
}
