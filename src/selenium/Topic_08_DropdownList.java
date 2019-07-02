package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_DropdownList {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://daominhdam.github.io/basic-form/index.html");
	}

	@Test
	public void TC_02_DropdownList() throws Exception {
		WebElement jobRoleDropdownList = driver.findElement(By.xpath("//select[@id='job1']"));
		Select select = new Select(jobRoleDropdownList);
		
		Assert.assertFalse(select.isMultiple());
		
		select.selectByVisibleText("Automation Tester");
		Thread.sleep(2000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");
		
		select.selectByValue("manual");
		Thread.sleep(2000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		
		select.selectByIndex(3);
		Thread.sleep(2000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
		
		System.out.println(select.getOptions().size());
		Assert.assertEquals(select.getOptions().size(), 5);
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
