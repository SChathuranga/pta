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

public class LandingPage {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(LandingPage.class);
	WaitHelper waitHelper;
	
	
	@FindBy(xpath="//span[contains(text(),'Hello')]")
	WebElement accountLinkLocator;
	
	@FindBy(xpath="//*[@id=\"nav-al-wishlist\"]/a[5]/span")
	WebElement wish;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(accountLinkLocator, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("Landing page object created");
		new TestBase().captureScreenShot(driver);
	}
	
	public SignInPage clickOnAccountLink() {
		accountLinkLocator.click();
		
		return new SignInPage(driver);
	}

}
