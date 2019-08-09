package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

public class Topic_04_DataProvider {
WebDriver driver;
	
	@BeforeClass
	public void initData(String browserName) {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
			}

  @Test(dataProvider = "UserAndPassword")
  public void TC_01_LoginwManyUsers(String username, String password) {
	  driver.get("http://live.guru99.com/");
	  
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  
	  driver.findElement(By.id("email")).sendKeys("auto_test_01@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("Pass111");
	  driver.findElement(By.id("send2")).click();

	  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	  driver.findElement(By.xpath("//div[@class='page-header-container']//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
  }
  @DataProvider(name = "UserAndPassword")
  public Object[][] UserInfor(){
	  return new Object[][] {
		  {"auto_test_01@gmail.com","Pass111"},
		  {"auto_test_05@gmail.com","123123"},
		  {"auto_test_07@gmail.com","123123"},
		  
	  };
  }
  @AfterClass
	public void afterClass() {
		driver.quit();
	}

}
