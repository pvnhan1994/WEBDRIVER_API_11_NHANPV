
package selenium;




import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_15_16_WedDriverWait {
	WebDriver driver;
	WebDriverWait waitExplicit;
	By starButton = By.xpath("//div[@id='start']/button");
	By loadingImg = By.xpath("//div[@id='loading']/img");
	By helloworldText = By.xpath("//div[@id='finish']/h4");

	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();

	}

	
	public void TC_01_Implicit_5s() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(starButton).click();

		Assert.assertEquals(driver.findElement(helloworldText).getText(), "dsdssdHello World!");
	}
	
	public void TC_02_Explicit_5s_Visible() {
		waitExplicit = new WebDriverWait(driver, 5);

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(starButton).click();

		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloworldText));

		Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
	}
	
	public void TC_03_Explicit_5s_Invisible() {
		waitExplicit = new WebDriverWait(driver, 5);

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(starButton).click();

		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingImg));
		Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
	}
	@Test
	public void TC_04() {
		waitExplicit = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		System.out.println("Start time Hello invisible ="+ getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(helloworldText));
		System.out.println("End time Hello invisible ="+ getDateTimeSecond());
		
		System.out.println("Start time Loading invisible ="+ getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingImg));
		System.out.println("End time Loading invisible ="+ getDateTimeSecond());
		
		System.out.println("Start time Start Button ="+ getDateTimeSecond());
		driver.findElement(starButton).click();
		System.out.println("End time Start Button ="+ getDateTimeSecond());
		
		System.out.println("Start time Loading invisible ="+ getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingImg));
		System.out.println("End time Loading invisible ="+ getDateTimeSecond());
		
		System.out.println("Start time StartBtn invisible="+ getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(starButton));
		System.out.println("End time StartBtn invisible="+ getDateTimeSecond());
	}

	public Date getDateTimeSecond() {
		Date date = new Date();
		date = new Timestamp(date.getTime());
		return date;
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
