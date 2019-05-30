package pageObjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import helper.browserconfiguration.config.ObjectReader;
import helper.javascript.JavascriptHelper;
import helper.logger.LoggerHelper;
import helper.wait.WaitHelper;
import testbase.TestBase;

public class DepartmentPage {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(DepartmentPage.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath="//*[@id=\"merchandised-content\"]/div/div/h1")
	WebElement departmentHeaderLocator;
	
	@FindBy(xpath="//h3[contains(text(), 'Refine by')]")
	WebElement refineByTextLocator;
	
	@FindBy(xpath="//*[@id=\"merchandised-content\"]/div/div/div/div/a/div/span")
	List<WebElement> productCategoriesLocator;
	
	public DepartmentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(departmentHeaderLocator, ObjectReader.reader.getExplicitWait());
		log.info("DepartmentPage object created");
		TestBase.logExtentReport("DepartmentPage object created");
		new TestBase().captureScreenShot(driver);
	}
	
	public void mouseOverToCategory(String categoryName) {
		log.info("scrolling to category - " + categoryName);
		new JavascriptHelper(driver).scrollIntoView(driver.findElement(By.xpath("//div[@id='merchandised-content']//span[contains(text(),'"+categoryName+"')]/parent::div")));
	}
	
	public CategoryPage clickOnCategory(String categoryName) {
		log.info("Clicking on category - " + categoryName);
		TestBase.logExtentReport("Clicking on category - " + categoryName);
		driver.findElement(By.xpath("//div[@id='merchandised-content']//span[contains(text(),'"+categoryName+"')]/parent::div/parent::a")).click();
		return new CategoryPage(driver);
	}
	
	// user will be able to refine a category by providing the heading and option
	public void refineBy(String refineType, String value) {
		log.info("Selecting refine item - " + refineType + " : " +value);
		TestBase.logExtentReport("Selecting refine item - " + refineType + " : " +value);
		driver.findElement(By.xpath("//h4[contains(text(),'" +refineType+ "')]/following-sibling::ul[1]//span[contains(text(),'"+value+"')]")).click();
	}

}
