package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_XPath_Css_Part_I {
	WebDriver driver;
	String email;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//Step 1: Access link 
		driver.get("http://live.guru99.com");
		email = "nhanphanviet" + randomData() +"@gmail.com";
		}
	@Test
	public void TC_01_LoginEmpty() {
		//Step 2: Click My Account để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer'] //a[text()='My Account']")).click();

		//Step 3: Để trống email + Pass
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		
		//Step 4: Click Login button
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		//Step 5: Verify error msg ở 2 fields
		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErrorMsg, "This is a required field.");

		String passErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passErrorMsg, "This is a required field.");
		
	}
	@Test
	public void TC_02_LoginWithEmailInvalid() {
		//Step 2: Click My Account để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer'] //a[text()='My Account']")).click();
		
		//Step 3: Input invalid email
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		
		//Step 4: Click button Login 
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		//Step 5: Verify
		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailErrorMsg, "Please enter a valid email address. For example johndoe@domain.com.");

	}
	@Test
	public void TC_03_LoginWithPassLessThan6Characters() {
		//Step 2: Click My Account để tới trang đăng nhập

		driver.findElement(By.xpath("//div[@class='footer'] //a[text()='My Account']")).click();

		//Step 3: Nhập valid email, invalid pass
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		
		//Step 4: Click button Login 
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		//Step 5: Verify
		String passErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passErrorMsg, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void TC_04_LoginWithPassIncorect() {
		//Step 2: Click My Account để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer'] //a[text()='My Account']")).click();

		//Step 3: Nhập valid email, invalid pass
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		
		//Step 4: Click button Login 
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		//Step 5: Verify
		String passErrorMsg = driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
		Assert.assertEquals(passErrorMsg, "Invalid login or password.");
	
	}	
	@Test
	public void TC_05_CreateAnAccount() throws Exception {
		//Step 2: Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer'] //a[text()='My Account']")).click();
		
		//Step 3: Click CREATE AN ACCOUNT button đế tới trang đăng ký tài khoản
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		//Step 4: Input valid data all fields 
		driver.findElement(By.id("firstname")).sendKeys("Nhan");
		driver.findElement(By.id("middlename")).sendKeys("Viet");
		driver.findElement(By.id("lastname")).sendKeys("Phan");
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys("123123");
		driver.findElement(By.id("confirmation")).sendKeys("123123");
		driver.findElement(By.xpath("//label[@for='is_subscribed']")).click();
		
		//Step 5: Click REGISTER button 
		driver.findElement(By.xpath("//span[text()='Register']")).click();
		
		//Step 6: Verify message Thank you for registering with Main Website Store.
		String verifyMsg = driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).getText();
		Assert.assertEquals(verifyMsg, "Thank you for registering with Main Website Store.");
		
		//Step 7: Log out 
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[text()='Log Out']")).click();
		
		//Step 8: Navigate về Home page khi log-out thành công
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for ')]")).isDisplayed());
		Thread.sleep(10000);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public int randomData() {
		Random random = new Random();
		return random.nextInt(99999);
		
	}
}
