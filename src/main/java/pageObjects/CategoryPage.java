package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helper.browserconfiguration.config.ObjectReader;
import helper.javascript.JavascriptHelper;
import helper.logger.LoggerHelper;
import helper.wait.WaitHelper;
import testbase.TestBase;

public class CategoryPage {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(CategoryPage.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath="//span[@id='s-result-count']")
	WebElement resultCount;
	
	
	public CategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(resultCount, ObjectReader.reader.getExplicitWait());
		log.info("CategoryPage object created");
		TestBase.logExtentReport("CategoryPage object created");
		new TestBase().captureScreenShot(driver);
	}
	
	public void mouseOverToProduct(int productNumber) {
		log.info("scrolling to product number[" + productNumber + "]");
		new JavascriptHelper(driver).scrollIntoView(driver.findElement(By.xpath("//li[@id='result_"+productNumber+"']")));
	}
	
	public void clickOnProduct(int productNumber) {
		driver.findElement(By.xpath("//li[@id='result_"+productNumber+"']//a/h2")).click();
		log.info("Clicked on product [" + productNumber + "]");
	}
	
}
