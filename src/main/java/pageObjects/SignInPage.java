package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helper.browserconfiguration.config.ObjectReader;
import helper.logger.LoggerHelper;
import helper.wait.WaitHelper;
import testbase.TestBase;

public class SignInPage {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(SignInPage.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath="//input[@id='ap_email']")
	WebElement emailTextFieldLocator;
	
	@FindBy(xpath="//input[@id='ap_password']")
	WebElement passwordTextFieldLocator;
	
	@FindBy(xpath="//input[@id='signInSubmit']")
	WebElement signInButtonLocator;
	
	@FindBy(xpath="//a[@id='createAccountSubmit']")
	WebElement createYourAccountButtonLocator;
	
	@FindBy(xpath="//a[@id='auth-fpp-link-bottom']")
	WebElement forgotPasswordLinkLocator;
	
	@FindBy(xpath="//h1[contains(text(),'Sign in')]")
	WebElement signInHeaderLocator;
	
	
	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(signInHeaderLocator, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("Sign In page object created");
		/* this will automatically capture the screenshot once the page is loaded , because 
		 * the moment we make a object of the sign-in-page the screenshot will be captured we will 
		 * not need to do that separately in the test-cases
		 */
		new TestBase().captureScreenShot(driver);
	}

	public void typeEmail(String email) {
		log.info("Entering email - " + email);
		TestBase.logExtentReport("Entering email - " + email);
		emailTextFieldLocator.sendKeys(email);
	}
	
	public void typePassword(String password) {
		log.info("Entering password - " + password);
		TestBase.logExtentReport("Entering password - " + password);
		passwordTextFieldLocator.sendKeys(password);
	}
	
	public HomePage clickOnSignInButton() {
		log.info("Clicking on sign in button");
		TestBase.logExtentReport("Clicking on sign in button");
		signInButtonLocator.click();
		// once the sign-in button is clicked a object of the home-page should be returned because we will be navigated to the home-page
		return new HomePage(driver);
	}
	
	public void signInToAmazon(String email, String password) {
		typeEmail(email);
		typePassword(password);
		clickOnSignInButton();
	}
	

	
}
