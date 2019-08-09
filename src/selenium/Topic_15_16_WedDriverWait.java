
package selenium;

import java.sql.Timestamp;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Function;

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

		Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
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

	public void TC_04_Mix_Explicit_Implicit() {
		waitExplicit = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		System.out.println("Start time Hello invisible =" + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(helloworldText));
		System.out.println("End time Hello invisible =" + getDateTimeSecond());

		System.out.println("Start time Loading invisible =" + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingImg));
		System.out.println("End time Loading invisible =" + getDateTimeSecond());

		System.out.println("Start time Start Button =" + getDateTimeSecond());
		driver.findElement(starButton).click();
		System.out.println("End time Start Button =" + getDateTimeSecond());

		System.out.println("Start time Loading invisible =" + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingImg));
		System.out.println("End time Loading invisible =" + getDateTimeSecond());

		System.out.println("Start time StartBtn invisible=" + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(starButton));
		System.out.println("End time StartBtn invisible=" + getDateTimeSecond());
	}

	public void TC_05_AjaxLoading_ImplicitWait() {

		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']/div")).isDisplayed());

		String noSelectText = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
		System.out.println(noSelectText);
		Assert.assertEquals(noSelectText, "No Selected Dates to display.");

		driver.findElement(By.xpath("//a[text()='21']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='21']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("	//span[@id='ctl00_ContentPlaceholder1_Label1' and contains(text(),'Wednesday, August 21, 2019')]")).isDisplayed());

		String SelectedText = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
		System.out.println(SelectedText);
		Assert.assertEquals(SelectedText, "Wednesday, August 21, 2019");

	}

	public void TC_05_AjaxLoading_ExplicitWait() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		waitExplicit = new WebDriverWait(driver, 10);

		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_Panel1']/div")));

		String noSelectText = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
		System.out.println(noSelectText);
		Assert.assertEquals(noSelectText, "No Selected Dates to display.");

		driver.findElement(By.xpath("//a[text()='21']")).click();

		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));

		WebElement dateSelected = waitExplicit.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='21']")));
		Assert.assertTrue(dateSelected.isDisplayed());

		String SelectedText = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
		System.out.println(SelectedText);
		Assert.assertEquals(SelectedText, "Wednesday, August 21, 2019");

	}

	@Test
	public void TC_06_FluentWait() {
		driver.get("https://daominhdam.github.io/fluent-wait/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		WebElement countdown = driver.findElement(By.id("javascript_countdown_time"));
		// wait.until(ExpectedConditions.visibilityOf(countdown));

		new FluentWait<WebElement>(countdown).withTimeout(15, TimeUnit.SECONDS)
		.pollingEvery(1, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class)
		.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						boolean flag = element.getText().endsWith("00");
						System.out.println("Time = " + element.getText());
						return flag;
					}
				});

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
