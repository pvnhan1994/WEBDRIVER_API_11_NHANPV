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

public class Topic_11_12_Popup_IFrame_Frame {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_iFrame() throws Exception {
		driver.get("https://www.hdfcbank.com/");

		// Size >= 1 - Presence (Có trong DOM) - k hiển thị trên UI: Undispplayed
		// Size = 0 - Ko có trong DOM - ko hiển thị trong UI: Undisplayed
		// Size >= 1 - Visible (Có trong DOM + hiển thị UI: Displayed

		List<WebElement> popup = driver.findElements(By.xpath("//div[@id='parentdiv']/a/img[@class='popupbanner at-element-click-tracking']"));

		if (popup.size() > 0 && popup.get(0).isDisplayed()) {
			System.out.println("Size >= 1 - Visible (Có trong DOM + hiển thị UI: Displayed ");
		} else if (popup.size() > 0 && !popup.get(0).isDisplayed()) {
			System.out.println("Size >= 1 - Presence (Có trong DOM) - k hiển thị trên UI: Undispplayed ");
		} else if (popup.size() == 0) {
			System.out.println("Size = 0  - Ko có trong DOM - ko hiển thị trong UI: Undisplayed ");
		}
		System.out.println("Popup displayed = " + popup.size());
		if (popup.size() > 0 && popup.get(0).isDisplayed()) {
			driver.findElement(By.xpath("//img[@class='popupCloseButton']")).click();
			Assert.assertFalse(popup.get(0).isDisplayed());
		}
		// Note: isDisplayed Nó chỉ kiểm tra được element có hiển thị thôi
		// Element k hiển thị nó chỉ ktra được 1 case: Size > 0 (phải có ít nhất 1)
		// Element k hiển thị size = 0 ; nó k dùng hàm này được

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

	public void TC_03_Windows() {
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

	@Test
	public void TC_04_Compare() {
		driver.get("http://live.guru99.com/index.php/");

		String parentID = driver.getWindowHandle();
		System.out.println("Parent ID = " + parentID);

		driver.findElement(By.xpath("//a[text()='Mobile']")).click();

		clickProductAddToCompareButton("Sony Xperia");
		Assert.assertTrue(verifyProductAddToCompare("Sony Xperia"));

		clickProductAddToCompareButton("Samsung Galaxy");
		Assert.assertTrue(verifyProductAddToCompare("Samsung Galaxy"));

		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		switchToWindowByTitle("Products Comparison List - Magento Commerce");

		List<WebElement> checkSize = driver.findElements(By.xpath("//h2[@class='product-name']"));
		System.out.println("Size product compare:" + checkSize.size());
		Assert.assertEquals(checkSize.size(), 2);

		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");

		closeAllWindowsWithoutParent(parentID);

	}

	public void clickProductAddToCompareButton(String productName) {
		driver.findElement(By.xpath("//a[text()='" + productName + "']/parent::h2/following-sibling::div[@class='actions']/ul/li/a[text()='Add to Compare']")).click();

	}

	public boolean verifyProductAddToCompare(String productName) {
		WebElement element = driver.findElement(By.xpath("//span[text()='The product " + productName + " has been added to comparison list.']"));
		return element.isDisplayed();
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
