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

public class Topic_07_Textbox_TextArea {

	WebDriver driver;
	// mngr206330 /YgEpYsA
	// Input
	String customerID;

	String usernameID, passwordID, name, gender, dob, Address, City, State, PIN, MobileNumber, Email, Password;

	String editAddr, editCity, editState, editPIN, editMobileNumber, editMail;

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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");

		usernameID = "mngr206330";
		passwordID = "YgEpYsA";
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

		editAddr = "235 Cu Lao";
		editCity = "Ho Chi Minh";
		editState = "Lac Hong";
		editPIN = "656565";
		editMobileNumber = "0967345123";
		editMail = "phanvietnhan" + randomMail() + "@gmail.com";
	}

	@Test
	public void TC_01_CreateNewCustomer() {
		// Input ID, pass
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(usernameID);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(passwordID);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		// Click for Add New Customer 
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		// Input data
		driver.findElement(customerNameTextbox).sendKeys(name);
		driver.findElement(genderMaleRadio).click();
		driver.findElement(DoBTextbox).sendKeys(dob);
		driver.findElement(addressTextbox).sendKeys(Address);
		driver.findElement(cityTextbox).sendKeys(City);
		driver.findElement(stateTextbox).sendKeys(State);
		driver.findElement(pinTextbox).sendKeys(PIN);
		driver.findElement(mobileTextbox).sendKeys(MobileNumber);
		driver.findElement(emailTextbox).sendKeys(Email);
		driver.findElement(passwordTextbox).sendKeys(Password);
		driver.findElement(submitButton).click();
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
	public void TC_02_EditCustomer() {
		// Open Edit Customer page and Edit Customer form
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		// Verify Customer Name and DOB matching vs TC 01
		Assert.assertEquals(driver.findElement(customerNameTextbox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(AddressRow).getText(), Address);

		// Clear
		driver.findElement(addressTextbox).clear();
		driver.findElement(cityTextbox).clear();
		driver.findElement(stateTextbox).clear();
		driver.findElement(pinTextbox).clear();
		driver.findElement(mobileTextbox).clear();
		driver.findElement(emailTextbox).clear();

		// Edit
		driver.findElement(addressTextbox).sendKeys(editAddr);
		driver.findElement(cityTextbox).sendKeys(editCity);
		driver.findElement(stateTextbox).sendKeys(editState);
		driver.findElement(pinTextbox).sendKeys(editPIN);
		driver.findElement(mobileTextbox).sendKeys(editMobileNumber);
		driver.findElement(emailTextbox).sendKeys(editMail);

		driver.findElement(submitButton).click();

		// Verify Edit
		Assert.assertEquals(driver.findElement(AddressRow).getText(), editAddr);
		Assert.assertEquals(driver.findElement(CityRow).getText(), editCity);
		Assert.assertEquals(driver.findElement(StateRow).getText(), editState);
		Assert.assertEquals(driver.findElement(PINRow).getText(), editPIN);
		Assert.assertEquals(driver.findElement(MobileNumberRow).getText(), editMobileNumber);
		Assert.assertEquals(driver.findElement(EmailRow).getText(), editMail);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomMail() {
		Random random = new Random();
		return random.nextInt(99999);
	}

}
