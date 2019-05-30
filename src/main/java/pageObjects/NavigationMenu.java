package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helper.browserconfiguration.config.ObjectReader;
import helper.logger.LoggerHelper;
import helper.wait.WaitHelper;
import testbase.TestBase;

public class NavigationMenu {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(NavigationMenu.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath="//a[@id='nav-link-shopall']")
	WebElement departmentsLinkLocator;
	
	public NavigationMenu(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper =  new WaitHelper(driver);
		waitHelper.waitForElement(departmentsLinkLocator, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("NavigationMenu object created");
		log.info("NavigationMenu object created");
		new TestBase().captureScreenShot(driver);
	}
	
	public void mouseOver(String data) {
		log.info("performing mouse over on - " + data);
		TestBase.logExtentReport("performing mouse over on - " + data);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'" + data + "')]"))).build().perform();
	}
	
	public DepartmentPage clickOnItem(String data) {
		log.info("Clicking on Navigation item - " + data);
		TestBase.logExtentReport("Clicking on Navigation item - " + data);
		driver.findElement(By.xpath("//*[contains(text(),'" + data + "')]")).click();
		return new DepartmentPage(driver);
	}
	
	public DepartmentPage clickOnMenu(WebElement element) {
		log.info("Clicking on element - " + element.getText());
		TestBase.logExtentReport("Clicking on element - " + element.getText());
		element.click();
		return new DepartmentPage(driver);
	}
	 

}
