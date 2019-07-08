package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox_Alert {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Button_Selenium() {
		driver.get("http://live.guru99.com/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");

		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");

	}
	@Test
	public void TC_01_Button_JS() {
		driver.get("http://live.guru99.com/");

		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", myAccountLink);

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");

		WebElement createAccountLink = driver.findElement(By.xpath("//span[text()='Create an Account']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", createAccountLink);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	}
	@Test
	public void TC_02_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		WebElement dualZoneCheckBox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", dualZoneCheckBox);
		Assert.assertTrue(dualZoneCheckBox.isSelected());
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", dualZoneCheckBox);
		Assert.assertFalse(dualZoneCheckBox.isSelected());
		
		
		
	}
	@Test
	public void TC_03_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
		
		WebElement petrolRadio = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		//((JavascriptExecutor) driver).executeScript("arguments[0].click();", petrolRadio);
		
		if(petrolRadio.isSelected()==true) {
			System.out.println("Da chon");
		}else ((JavascriptExecutor) driver).executeScript("arguments[0].click();", petrolRadio);
		//Assert.assertTrue(petrolRadio.isSelected());
		
		Assert.assertTrue(petrolRadio.isSelected());
		
	}
	@Test
	public void TC_04_AcceptAlert() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		Assert.assertEquals(alertText, "I am a JS Alert");
		
		alert.accept();
		
		String getTextResult= driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(getTextResult, "You clicked an alert successfully");
		
		
	}
	@Test
	public void TC_05_ConfirmAlert() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		Assert.assertEquals(alertText, "I am a JS Confirm");
		
		alert.dismiss();
		
		String getTextResult= driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(getTextResult, "You clicked: Cancel");
	}
	@Test
	public void TC_06_PromptAlert() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		Assert.assertEquals(alertText, "I am a JS prompt");
		
		String textInput= "phanvietnhan";
		alert.sendKeys(textInput);
		alert.accept();
		String getTextResult= driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(getTextResult, "You entered: " + textInput);
	
	}
	@Test
	public void TC_07_AuthenticationAlert() {
		String username = "admin";
		String password = "admin";
		
		driver.get("http://the-internet.herokuapp.com/");
		WebElement basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']"));
		String url = basicAuthenLink.getAttribute("href");
		
		//String url = "http://the-internet.herokuapp.com/basic_auth";
		
		driver.get(byPassAuthentication(url, username, password));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	public String byPassAuthentication(String url, String username, String password) {
		System.out.println("Old url = " +url);
		String[] splitUrl = url.split("//");
		url = splitUrl[0] + "//" + username + ":" + password + "@" + splitUrl[1];
		System.out.println("New Url = " +url);
		return url;
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
