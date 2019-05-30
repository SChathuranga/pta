package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import helper.assertion.VerificationHelper;
import helper.browserconfiguration.config.ObjectReader;
import helper.logger.LoggerHelper;
import helper.wait.WaitHelper;
import testbase.TestBase;

public class HomePage {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(HomePage.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath="//span[contains(text(),'Hello')]")
	WebElement myAccountLink;
	
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	WebElement searchTextField;
	
	@FindBy(xpath="//*[@id='nav-shop']/a")
	WebElement departmentsLink;
	
	@FindBy(xpath="//div[@id=\"nav-logo\"]/a")
	WebElement amozonLogo;
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(departmentsLink, ObjectReader.reader.getExplicitWait());
		TestBase.logExtentReport("Home page object created");
	}
	
	public boolean verifySuccessLogin() {
		return new VerificationHelper(driver).isDisplayed(myAccountLink);
	}
	
	public MyAccount clickOnMyAccount() {
		myAccountLink.click();
		return new MyAccount(driver);
	}
	
	public void mouseOverDepartmentLink() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='nav-shop']/a"))).build().perform();
	}
	
	public DepartmentPage clickOnDepartment(String departmentname) {
		driver.findElement(By.xpath("//*[@id=\"nav-flyout-shopAll\"]/div[2]/a/span[contains(text(),'"+departmentname+"')]")).click();
		return new DepartmentPage(driver);
	}
	
	public void navigateToHomePage() {
		amozonLogo.click();
	}

}
