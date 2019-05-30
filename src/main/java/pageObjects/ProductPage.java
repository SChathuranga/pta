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

public class ProductPage {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(ProductPage.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath="//span[@id=\"s-result-count\"]")
	WebElement resultCount;
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(resultCount, ObjectReader.reader.getExplicitWait());
		log.info("ProductPage object created");
		TestBase.logExtentReport("ProductPage object created");
		new TestBase().captureScreenShot(driver);
	}

}
