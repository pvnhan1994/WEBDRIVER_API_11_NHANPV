package selenium;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup_IFrame_Frame {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	@Test
	public void TC_01_iFrame() throws Exception {
		driver.get("https://www.hdfcbank.com/");
		driver.findElement(By.xpath("//div[@id='parentdiv']/img")).click();

		// Switch TO iFrame
		WebElement lookingForIframe = driver.findElement(By.xpath("//iframe[starts-with(@id,'viz_iframe')]"));
		driver.switchTo().frame(lookingForIframe);
		Thread.sleep(3000);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='messageText']")).getText(), "What are you looking for?");

		// Switch parent node
		driver.switchTo().defaultContent();

		// Verify right banner has 9 images
		List<WebElement> rightBannerImage = driver.findElements(By.xpath("//div[@id='rightbanner']//div[@class='owl-item']//img"));
		Assert.assertEquals(rightBannerImage.size(), 9);

		// Verify flip banner has 8 images
		List<WebElement> flipBannerImage = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		Assert.assertEquals(flipBannerImage.size(), 8);

		for (WebElement image : flipBannerImage) {
			Assert.assertTrue(image.isDisplayed());
		}

	}

	@Test
	public void TC_02_Windows() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");

		// Lay ID cua parent Tab
		String parentID = driver.getWindowHandle();
		System.out.println("Parent ID = " + parentID);

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

		switchToWindowByTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchToWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");

		closeAllWindowsWithoutParent(parentID);
	}
	@Test
	public void TC_03() {
		driver.get("https://www.hdfcbank.com/");

		String parentID = driver.getWindowHandle();
		System.out.println("Parent ID = " + parentID);

		driver.findElement(By.xpath("//div[@id='parentdiv']/img")).click();
		driver.findElement(By.xpath("//a[text()='Agri']")).click();

		switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");

		driver.findElement(By.xpath("//p[text()='Account Details']")).click();
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");

		
		WebElement framePolicy = driver.findElement(By.xpath("//frame[@name='footer']"));
		driver.switchTo().frame(framePolicy);
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		
		switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");

		driver.findElement(By.xpath("//a[@title='Corporate Social Responsibility']")).click();

		closeAllWindowsWithoutParent(parentID);
		switchToWindowByTitle("HDFC Bank: Personal Banking Services");

	}
	
	public void switchToChildWindowByID(String parent) {
		// Get tat ca cac cua so + tab dang co
		Set<String> allWindows = driver.getWindowHandles();
		// Dung vong lap duyet qua tung ID
		for (String runWindow : allWindows) {
			// Kiem tra dieu kien: neu nhu ID nao khac voi ID cua parent thi no se switch
			// qua va thoat vong lap
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;

			}
		}
	}

	public boolean closeAllWindowsWithoutParent(String parentID) {
		// Get tat ca cac ID cua cua so + tab
		Set<String> allWindows = driver.getWindowHandles();
		// Dung vong lap duyet qua tung ID
		for (String runWindows : allWindows) {
			// Kiem tra dieu kien: neu ID khac voi ID Parent thi no se switch cua so do >>
			// roi close
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();

			}
		}
		// Switch vao parent
		driver.switchTo().window(parentID);
		// Kiem tra duy nhat con 1 cua so
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
