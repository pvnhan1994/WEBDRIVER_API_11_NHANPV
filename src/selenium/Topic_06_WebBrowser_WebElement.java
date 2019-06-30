package selenium;

import java.util.concurrent.TimeUnit;

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
		if(isElementEnable(emailTextbox)) {
			System.out.println("Element: "+emailTextbox+" is Enable ");
		}
		if(isElementEnable(ageUnder18)) {
			System.out.println("Element: "+ageUnder18+" is Enable ");
		}
		if(isElementEnable(eduTextArea)) {
			System.out.println("Element: "+eduTextArea+" is Enable ");
		}
		if(isElementEnable(jobRole01)) {
			System.out.println("Element: "+jobRole01+" is Enable ");
		}
		if(isElementEnable(interestEnable)) {
			System.out.println("Element: "+interestEnable+" is Enable ");
		}
		if(isElementEnable(slide01Enable)) {
			System.out.println("Element: "+slide01Enable+" is Enable ");
		}
		if(isElementEnable(buttonEnable)) {
			System.out.println("Element: "+buttonEnable+" is Enable ");
		}
		//Check Disable
		if(isElementDisable(passTextbox)) {
			System.out.println("Element: "+passTextbox+" is Disable ");
		}
		if(isElementDisable(ageDisable)) {
			System.out.println("Element: "+ageDisable+" is Disable ");
		}
		if(isElementDisable(biography)) {
			System.out.println("Element: "+biography+" is Disable ");
		}
		if(isElementDisable(jobRole02)) {
			System.out.println("Element: "+jobRole02+" is Disable ");
		}
		if(isElementDisable(interestDisable)) {
			System.out.println("Element: "+interestDisable+" is Disable ");
		}
		if(isElementDisable(slide02Disable)) {
			System.out.println("Element: "+slide02Disable+" is Disable ");
		}
		if(isElementDisable(buttonDisable)) {
			System.out.println("Element: "+buttonDisable+" is Disable ");
		}
	}
	@Test
	public void TC_03_Selected() {
		clickElement(ageUnder18);
		clickElement(interestEnable);
		
		if(isElementSelected(ageUnder18) && isElementSelected(interestEnable)) {
			System.out.println("Age Under 18 + Interest Development: Selected");
		}
		
		clickElement(interestEnable);
		if(!isElementSelected(interestEnable)) {
			System.out.println("Interest Development: Un-Selected");
		}
			
	//	Assert.assertFalse(clickElement(interestEnable));
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
	public boolean isElementEnable(By by) {
		WebElement element=driver.findElement(by);
		return element.isEnabled();
	}
	public boolean isElementDisable(By by) {
		WebElement element = driver.findElement(by);
		return element.isDisplayed();
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
