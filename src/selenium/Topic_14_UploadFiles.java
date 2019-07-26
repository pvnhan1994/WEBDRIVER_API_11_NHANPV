package selenium;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_UploadFiles {
	WebDriver driver;
	String rootFolderPath, image01Path, image02Path, image03Path, image04Path;
	String image01Name = "image01.jpg";
	String image02Name = "image02.JPG";
	String image03Name = "image03.jpg";
	String image04Name = "image04.jpeg";
	String Name, Mail, FirstName, getTextEmail, Link;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		rootFolderPath = System.getProperty("user.dir");

		// System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
		// driver = new ChromeDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Root folder path = /Users/nhanpv/Documents/workspace/WEBDRIVER_API_11_NHANPV
		// rootFolderPath = System.getProperty("user.dir");
		System.out.println(rootFolderPath);
		image01Path = rootFolderPath + "//uploadFiles//" + image01Name;
		image02Path = rootFolderPath + "//uploadFiles//" + image02Name;
		image03Path = rootFolderPath + "//uploadFiles//" + image03Name;
		image04Path = rootFolderPath + "//uploadFiles//" + image04Name;

		System.out.println(image01Path);
		System.out.println(image02Path);
		System.out.println(image03Path);
		System.out.println(image04Path);

		Name = "anonymous" + randomName();
		Mail = "anonymous" + randomMail() + "@gmail.com";
		FirstName = "bin";
		// https://encodable.com/uploaddemo/?action=viewer&path=phanvietnhan29958/&file=image01.jpg

	}

	public void TC_01_SendKeyAPI() throws Exception {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		// Element nay phai la the input va co attribute type=file
		WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));

		/*
		 * uploadFile.sendKeys(
		 * "/Users/nhanpv/Documents/workspace/WEBDRIVER_API_11_NHANPV/uploadFiles/image01.jpg"
		 * + "\n" +
		 * "/Users/nhanpv/Documents/workspace/WEBDRIVER_API_11_NHANPV/uploadFiles/image02.JPG"
		 * + "\n" +
		 * "/Users/nhanpv/Documents/workspace/WEBDRIVER_API_11_NHANPV/uploadFiles/image03.jpg"
		 * );
		 */

		uploadFile.sendKeys(image01Path + "\n" + image02Path + "\n" + image03Path + "\n" + image04Path);

		// Wait for 3 file upload
		List<WebElement> fileLoaded = driver.findElements(By.xpath("//tbody[@class='files']//p[@class='name']"));

		for (WebElement file : fileLoaded) {
			System.out.println("File loaded name = " + file.getText());
			Assert.assertTrue(file.isDisplayed());
		}
		// Check start upload button
		List<WebElement> startBtns = driver.findElements(By.xpath("//tbody[@class='files']//button[@class='btn btn-primary start']"));
		for (WebElement start : startBtns) {
			start.click();
			Thread.sleep(2000);
		}

		// Check 3 file upload success
		List<WebElement> fileUploaded = driver.findElements(By.xpath("//tbody[@class='files']//p[@class='name']/a"));
		for (WebElement file : fileUploaded) {
			System.out.println("File uploaded name = " + file.getText());
			Assert.assertTrue(file.isDisplayed());
		}

		Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']//a[text()='" + image01Name + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']//a[text()='" + image02Name + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']//a[text()='" + image03Name + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']//a[text()='" + image04Name + "']")).isDisplayed());
	}

	@Test
	public void TC_02() throws Exception {
		driver.get("https://encodable.com/uploaddemo/");
		WebElement uploadFile = driver.findElement(By.xpath("//input[@id='uploadname1']"));
		uploadFile.sendKeys(image01Path);

		driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(Name);
		driver.findElement(By.xpath("//input[@id='formfield-email_address']")).sendKeys(Mail);
		driver.findElement(By.xpath("//input[@id='formfield-first_name']")).sendKeys(FirstName);

		driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
		Thread.sleep(10000);

		WebElement getEmail = driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dd[contains(text(),'Email ')]"));
		System.out.println("Text of " + getEmail.getText());
		Assert.assertEquals(getEmail.getText(), "Email Address: " + Mail);

		WebElement getFirstName = driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dd[contains(text(),'First ')]"));
		System.out.println("Text of " + getFirstName.getText());
		Assert.assertEquals(getFirstName.getText(), "First Name: " + FirstName);

		WebElement getNameFile = driver.findElement(By.xpath("//dl[@id='fcuploadsummary']/dt[contains(text(),'File')]/a"));
		System.out.println("Text of" + getNameFile.getText());
		Assert.assertEquals(getNameFile.getText(), image01Name);

		driver.findElement(By.xpath("//div[@id='fcfooter-text']/a[text()='View Uploaded Files']")).click();

		selectFolder("//table[@id='filelist']//following-sibling::tr/td[@class='dname diricon']", Name);

		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='fname thumb']/a[text()='" + image01Name + "']")).isDisplayed());
		driver.findElement(By.xpath("//td[@class='fname thumb']/a[@class='thumb']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='defaultpage']/div/div[@id='fc_content']/a[text()='" + image01Name + "']")).isDisplayed());
		System.out.println("dsadsads::::" + driver.findElement(By.xpath("//div[@id='defaultpage']/div/div[@id='fc_content']/a")).getText());

		Link = "https://encodable.com/uploaddemo/?action=viewer&path=" + Name + "/&file=" + image01Name;
		System.out.println("Link: " + Link);
		System.out.println(driver.getCurrentUrl());
		Assert.assertEquals(Link, driver.getCurrentUrl());

	}

	public int randomName() {
		Random random = new Random();
		return random.nextInt(99999);
	}

	public int randomMail() {
		Random random = new Random();
		return random.nextInt(99999);
	}

	public void selectFolder(String listFolder, String NameFolder) throws Exception {

		List<WebElement> allItems = driver.findElements(By.xpath(listFolder));
		System.out.println("Tat ca phan tu trong dropdown =" + allItems.size());
		
		for (WebElement childElement : allItems) {
			System.out.println("Text moi lan get = " + childElement.getText());

			if (childElement.getText().contains(NameFolder)) {
				
				if (childElement.isDisplayed()) {
					System.out.println("Click by Selenium = " + childElement.getText());
					childElement.click();
				}
				Thread.sleep(1000);
				break;

			}

		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
