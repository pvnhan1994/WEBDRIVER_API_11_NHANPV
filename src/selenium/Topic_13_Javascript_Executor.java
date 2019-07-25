package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Javascript_Executor {
	WebDriver driver;
	
	// mngr206330 /YgEpYsA
	// Input
	String customerID;

	String usernameID, passwordID, name, gender, dob, Address, City, State, PIN, MobileNumber, Email, Password;

	
	By customerNameTextbox = By.xpath("//input[@name='name']");
	By genderMaleRadio = By.xpath("//input[@value='m']");
	By DoBTextbox = By.xpath("//input[@name='dob']");
	By addressTextbox = By.xpath("//textarea[@name='addr']");
	By cityTextbox = By.xpath("//input[@name='city']");
	By stateTextbox = By.xpath("//input[@name='state']");
	By pinTextbox = By.xpath("//input[@name='pinno']");
	By mobileTextbox = By.xpath("//input[@name='telephoneno']");
	By emailTextbox = By.xpath("//input[@name='emailid']");
	By passwordTextbox = By.xpath("//input[@name='password']");
	By submitButton = By.xpath("//input[@name='sub']");

	// Verify
	By customerNameRow = By.xpath("//td[text()='Customer Name']//following-sibling::td");
	By genderMaleRow = By.xpath("//td[text()='Gender']//following-sibling::td");
	By DoBRow = By.xpath("//td[text()='Birthdate']//following-sibling::td");
	By AddressRow = By.xpath("//td[text()='Address']//following-sibling::td");
	By CityRow = By.xpath("//td[text()='City']//following-sibling::td");
	By StateRow = By.xpath("//td[text()='State']//following-sibling::td");
	By PINRow = By.xpath("//td[text()='Pin']//following-sibling::td");
	By MobileNumberRow = By.xpath("//td[text()='Mobile No.']//following-sibling::td");
	By EmailRow = By.xpath("//td[text()='Email']//following-sibling::td");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
		//driver = new ChromeDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		usernameID = "mngr47659";
		passwordID = "123456@";
		name = "Automation Test";
		gender = "male";
		dob = "2000-01-02";
		Address = "439 Ho Xuan Huong";
		City = "Da Nang";
		State = "QuanHai";
		PIN = "555554";
		MobileNumber = "09356024501";
		Email = "phanvietnhan" + randomMail() + "@gmail.com";
		Password = "1231123";

	}
	

	
	public void TC_01() {

		navigateToUrlByJS("http://live.guru99.com/");
		
		String homePageUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(homePageUrl, "http://live.guru99.com/");
		
		String homePageDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(homePageDomain, "live.guru99.com");
		
		WebElement mobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
		clickToElementByJS(mobileLink);
		
		WebElement addToCard = driver.findElement(By.xpath("//a[text()='Sony Xperia']//parent::h2//following-sibling::div/button"));
		clickToElementByJS(addToCard);
		
		String sonySuccessMsg = (String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(sonySuccessMsg.contains("Sony Xperia was added to your shopping cart."));
		Assert.assertTrue(verifyTextInInnerText("Sony Xperia was added to your shopping cart."));
		//WebElement getText = driver.findElement(By.xpath("//li[@class='success-msg']//span"));
		//verifyTextInInnerText("Sony Xperia was added to your shopping cart.");
		
		clickToElementByJS(driver.findElement(By.xpath("//a[text()='Privacy Policy']")));
		
		String privacyTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(privacyTitle,"Privacy Policy");
		
		scrollToBottomPage();
		
		WebElement wishlistRow = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()=' The number of items in your Wishlist."));
		Assert.assertTrue(wishlistRow.isDisplayed());
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		
		String demoGuruDomain = (String) executeForBrowser("return document.domain");
		System.out.println("Domain = " + demoGuruDomain);
		Assert.assertEquals(homePageDomain, "live.guru99.com");
		
		
	}
	
	public void TC_02() throws Exception {
		driver.get("http://demo.guru99.com/v4");
		// Input ID, pass
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(usernameID);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(passwordID);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		// Click for Add New Customer 
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		// Input data
		driver.findElement(customerNameTextbox).sendKeys(name);
		driver.findElement(genderMaleRadio).click();
		
		// Handle Date Picker Time  - remove attribute in DOM 
		
		removeAttributeInDOM(driver.findElement(DoBTextbox),"type");
		Thread.sleep(3000);
		driver.findElement(DoBTextbox).sendKeys(dob);
		
		driver.findElement(addressTextbox).sendKeys(Address);
		driver.findElement(cityTextbox).sendKeys(City);
		driver.findElement(stateTextbox).sendKeys(State);
		driver.findElement(pinTextbox).sendKeys(PIN);
		driver.findElement(mobileTextbox).sendKeys(MobileNumber);
		driver.findElement(emailTextbox).sendKeys(Email);
		driver.findElement(passwordTextbox).sendKeys(Password);
		driver.findElement(submitButton).click();
		Thread.sleep(10000);
		// Get customer ID 
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText();
		System.out.println("Customer ID at TC_01: " + customerID);
		// Verify output 
		Assert.assertEquals(driver.findElement(customerNameRow).getText(), name);
		Assert.assertEquals(driver.findElement(genderMaleRow).getText(), gender);
		Assert.assertEquals(driver.findElement(DoBRow).getText(), dob);
		Assert.assertEquals(driver.findElement(AddressRow).getText(), Address);
		Assert.assertEquals(driver.findElement(CityRow).getText(), City);
		Assert.assertEquals(driver.findElement(StateRow).getText(), State);
		Assert.assertEquals(driver.findElement(PINRow).getText(), PIN);
		Assert.assertEquals(driver.findElement(MobileNumberRow).getText(), MobileNumber);
		Assert.assertEquals(driver.findElement(EmailRow).getText(), Email);

	}
	@Test
	public void TC_03() {
		navigateToUrlByJS("http://live.guru99.com/");
		
		//div[@class='footer']//li/a[text()='My Account']
		
		WebElement MyAccount = driver.findElement(By.xpath("//div[@class='footer']//li/a[text()='My Account']"));
		clickToElementByJS(MyAccount);
		
		//span[text()='Create an Account']
		WebElement createAccount = driver.findElement(By.xpath("//span[text()='Create an Account']"));
		clickToElementByJS(createAccount);
		
		WebElement firstname = driver.findElement(By.xpath("//input[@id='firstname']"));
		sendkeyToElementByJS(firstname,"phan");
		
		WebElement middlename = driver.findElement(By.xpath("//input[@id='middlename']"));
		sendkeyToElementByJS(middlename,"viet");
		
		WebElement lastname = driver.findElement(By.xpath("//input[@id='lastname']"));
		sendkeyToElementByJS(lastname,"nhan");
		
		WebElement email_address = driver.findElement(By.xpath("//input[@id='email_address']"));
		sendkeyToElementByJS(email_address,Email);
		
		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
		sendkeyToElementByJS(password,"123123");
		
		WebElement repassword = driver.findElement(By.xpath("//input[@id='confirmation']"));
		sendkeyToElementByJS(repassword,"123123");
		
		WebElement clickConfirm = driver.findElement(By.xpath("//input[@id='is_subscribed']"));
		clickToElementByJS(clickConfirm);
		
		WebElement btnRegister = driver.findElement(By.xpath("//button[@title='Register']"));
		clickToElementByJS(btnRegister);
		
		String registerSuccess = (String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(registerSuccess.contains("Thank you for registering with Main Website Store."));
		Assert.assertTrue(verifyTextInInnerText("Thank you for registering with Main Website Store."));
		//WebElement getText = driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']"));
		//verifyTextInInnerText("Thank you for registering with Main Website Store.");
		

		WebElement clickAccount = driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']"));
		clickToElementByJS(clickAccount);
		
		
		WebElement logOut = driver.findElement(By.xpath("//a[@title='Log Out']"));
		clickToElementByJS(logOut);
		
		//navigateToUrlByJS("http://live.guru99.com/index.php/");
		
		String homePageUrl = (String) executeForBrowser("return document.URL");
		System.out.println("Url = " + homePageUrl);
		Assert.assertEquals(homePageUrl, "http://live.guru99.com/index.php/customer/account/logoutSuccess/");
	}
	public void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public Object executeForBrowser(String javaSript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(String textExpected) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String textActual = (String) js.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}
	
	public Object clickToElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}

	public Object scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public Object sendkeyToElementByJS(WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public Object scrollToBottomPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public Object removeAttributeInDOM(WebElement element, String attributeRemove) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public Object navigateToUrlByJS(String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '" + url + "'");
	}
	public int randomMail() {
		Random random = new Random();
		return random.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
