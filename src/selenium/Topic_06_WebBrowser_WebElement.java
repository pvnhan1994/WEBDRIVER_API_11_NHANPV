package selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebBrowser_WebElement {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='mail']");
	By ageUnder18 = By.xpath("//input[@id='under_18']");
	By eduTextArea = By.xpath("//textarea[@id='edu']");
	By jobRole01 = By.xpath("//select[@id='job1']");
	By interestEnable = By.xpath("//input[@id='development']");
	By slide01Enable = By.xpath("//input[@id='slider-1']");
	By buttonEnable = By.xpath("//button[@id='button-enabled']");
	By passTextbox = By.xpath("//input[@id='password']");
	By ageDisable = By.xpath("//input[@id='radio-disabled']");
	By biography = By.xpath("//textarea[@id='bio']");
	By jobRole02 = By.xpath("//select[@id='job2']");
	By interestDisable = By.xpath("//input[@id='check-disbaled']");
	By slide02Disable = By.xpath("//input[@id='slider-2']");
	By buttonDisable = By.xpath("//button[@id='button-disabled']");
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://daominhdam.github.io/basic-form/index.html");
	}

	
	@Test
	public void TC_01_Displayed() {
		if(isElementDisplayed(emailTextbox)) {
			sendkeyToElement(emailTextbox,"Automation Testing");
		}
		if(isElementDisplayed(eduTextArea)) {
			sendkeyToElement(eduTextArea,"Automation Testing");
		}
		if(isElementDisplayed(ageUnder18)) {
			clickElement(ageUnder18);
		}
	}
	
	/*@Test
	public void TC_01_Displayed() {
		if(driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
		}
		if(driver.findElement(eduTextArea).isDisplayed()) {
			driver.findElement(eduTextArea).sendKeys("Automation Testing");
		}
		if(driver.findElement(ageUnder18).isDisplayed()) {
			driver.findElement(ageUnder18).click();
		}
	}
	*/
	@Test 
	public void TC_02_EnableAndDisable() {
		//Check Enable
		isElementEnable(emailTextbox);
		isElementEnable(ageUnder18);
		isElementEnable(eduTextArea);
		isElementEnable(jobRole01);
		isElementEnable(interestEnable);
		isElementEnable(slide01Enable);
		isElementEnable(buttonEnable);
		isElementEnable(passTextbox);
		isElementEnable(ageDisable);
		isElementEnable(biography);
		isElementEnable(jobRole02);
		isElementEnable(interestDisable);
		isElementEnable(slide02Disable);
		isElementEnable(buttonDisable);
		
		
	}
	@Test
	public void TC_03_Selected() {
		
		clickElement(ageUnder18);
		clickElement(interestEnable);
		Assert.assertTrue(isElementSelected(ageUnder18));
		Assert.assertTrue(isElementSelected(interestEnable));
		
		clickElement(interestEnable);
		Assert.assertFalse(isElementSelected(interestEnable));
	}
	
	
	public boolean isElementDisplayed(By by) {
		WebElement element= driver.findElement(by);
		return element.isDisplayed();
	}
	public void clickElement(By by) {
		WebElement element= driver.findElement(by);
		element.click();
	}
	public void sendkeyToElement(By by, String value) {
		WebElement element= driver.findElement(by);
		element.sendKeys(value);
	}
	
	public void isElementEnable(By by) {
		WebElement element = driver.findElement(by);
		if(element.isEnabled()) {
			System.out.println("Element "+element+  "is Enable");
		}else {
			System.out.println("Element "+element+  "is Disable");
		}
	}
	public boolean isElementSelected(By by) {
		WebElement element=driver.findElement(by);
		return element.isSelected();
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
