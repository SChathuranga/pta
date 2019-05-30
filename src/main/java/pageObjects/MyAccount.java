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

public class MyAccount {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(MyAccount.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath="//h2[contains(text(),'Your Orders')]")
	WebElement yourOrdersLinkLocator;
	
	@FindBy(xpath="//h2[contains(text(),'Login & security')]")
	WebElement loginAndSecurityLinkLocator;
	
	@FindBy(xpath="//h2[contains(text(),'Prime')]")
	WebElement primeLinkLocator;
	
	@FindBy(xpath="//h2[contains(text(),'Your Addresses')]")
	WebElement yourAddressLinkLocator;
	
	@FindBy(xpath="//h2[contains(text(),'Payment options')]")
	WebElement paymentOptionsLinkLocator;
	
	@FindBy(xpath="//h2[contains(text(),'Gift cards')]")
	WebElement giftCardsLinkLocator;
	
	public MyAccount(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(yourOrdersLinkLocator, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("My Account page object created");
		new TestBase().captureScreenShot(driver);
	}
	
	public void clickOnYourOrders() {
		yourOrdersLinkLocator.click();
		log.info("Clicked on "+ yourOrdersLinkLocator.getText());
		TestBase.logExtentReport("Clicked on - " + yourOrdersLinkLocator.getText());
	}
	
	

}
