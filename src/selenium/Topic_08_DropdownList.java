package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_DropdownList {
	WebDriver driver;
	JavascriptExecutor javascriptExecutor;
	WebDriverWait waitExplicit;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		javascriptExecutor = (JavascriptExecutor) driver;
		waitExplicit = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_DropdownList() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");

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

	@Test
	public void TC_02_jQuery() throws Exception {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "19");
		Thread.sleep(1000);
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']"));

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "11");
		Thread.sleep(1000);
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='11']"));

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "1");
		Thread.sleep(1000);
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='1']"));

	}

	@Test
	public void TC_03_Angualar() throws Exception {
		driver.get("https://material.angular.io/components/select/examples");

		selectItemInCustomDropdown("//mat-label[text()='State']/ancestor::span/preceding-sibling::mat-select//div[@class='mat-select-arrow-wrapper']", "//mat-option/span", "Ohio");
		Thread.sleep(1000);
		Assert.assertTrue(isElementDisplayed("//mat-label[text()='State']/ancestor::span/preceding-sibling::mat-select//div[@class='mat-select-value']//span[text()='Ohio']"));

		selectItemInCustomDropdown("//mat-label[text()='State']/ancestor::span/preceding-sibling::mat-select//div[@class='mat-select-arrow-wrapper']", "//mat-option/span", "Michigan");
		Thread.sleep(1000);
		Assert.assertTrue(isElementDisplayed("//mat-label[text()='State']/ancestor::span/preceding-sibling::mat-select//div[@class='mat-select-value']//span[text()='Michigan']"));

	}
	@Test
	public void TC_04_KendoUI() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");

		selectItemInCustomDropdown("//span[@aria-owns='color_listbox']//span[@class='k-select']", "//ul[@id='color_listbox']//li", "Grey");
		Thread.sleep(1000);
		Assert.assertTrue(isElementDisplayed("//span[@aria-owns='color_listbox']//span[@class='k-input'and text()='Grey']"));

		// selectItemInCustomDropdown("//span[@aria-owns='color_listbox']//span[@class='k-select']",
		// "//ul[@id='color_listbox']//li", "Orange");
		// Thread.sleep(1000);
		// Assert.assertTrue(isElementDisplayed("//span[@aria-owns='color_listbox']//span[@class='k-input'and
		// text()='Orange']"));
	}

	public boolean isElementDisplayed(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedValueItem) throws Exception {
		// Click vào dropdown cho xổ hết tất cả các giá trị

		WebElement parentDropdown = driver.findElement(By.xpath(parentXpath));
		javascriptExecutor.executeScript("arguments[0].click();", parentDropdown);
		Thread.sleep(1000);
		// Chờ cho tất cả các giá trị trong dropdown được load ra thành công
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		System.out.println("Tat ca phan tu trong dropdown =" + allItems.size());
		// Duyệt qua hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
		for (WebElement childElement : allItems) {
			System.out.println("Text moi lan get = " + childElement.getText());

			if (childElement.getText().contains(expectedValueItem)) {
				// click vao item can chon
				if (childElement.isDisplayed()) {
					System.out.println("Click by Selenium = " + childElement.getText());
					childElement.click();
				} else {
					javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					Thread.sleep(1000);
					System.out.println("Click by JS = " + childElement.getText());
					javascriptExecutor.executeScript("arguments[0].click();", childElement);
				}
				Thread.sleep(1000);
				break;

			}

		}

	}

	@Test
	public void TC_05_CustomMultipleItems() throws Exception {
		driver.get("http://multiple-select.wenzhixin.net.cn/examples/#basic.html");

		String[] three = { "January", "April", "July" };
		String[] four = { "January", "April", "July", "November" };

		// Switch to Iframe
		WebElement contentIframe = driver.findElement(By.xpath("//div[@class='content']//iframe"));
		driver.switchTo().frame(contentIframe);

		// 3 items
		selectMultiItemInDropdown("//button[@class='ms-choice']", "//div[@class='ms-drop bottom']//span", three);
		Assert.assertTrue(checkItemSelected(three));

		driver.navigate().refresh();
		WebElement contentIframeRefresh = driver.findElement(By.xpath("//div[@class='content']//iframe"));
		driver.switchTo().frame(contentIframeRefresh);
		// 4 items
		selectMultiItemInDropdown("//button[@class='ms-choice']", "//div[@class='ms-drop bottom']//span", four);
		Assert.assertTrue(checkItemSelected(four));
	}

	public void selectMultiItemInDropdown(String parentXpath, String allItemXpath, String[] expectedValueItem) throws Exception {
		// click vao dropdown tat ca value
		WebElement parentDropdown = driver.findElement(By.xpath(parentXpath));
		javascriptExecutor.executeScript("arguments[0].click();", parentDropdown);
		// cho value load ra
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		System.out.println("Tat ca phan tu trong dropdown =" + allItems.size());

		// duyet qua het tat ca cac phan tu
		for (WebElement childElement : allItems) {
			// System.out.println("Text lan thu = "+childElement.getText());
			for (String item : expectedValueItem) {
				if (childElement.getText().equals(item)) {
					javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					Thread.sleep(1500);

					javascriptExecutor.executeScript("arguments[0].click();", childElement);
					Thread.sleep(1500);

					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected = " + itemSelected.size());
					if (expectedValueItem.length == itemSelected.size()) {
						break;
					}

				}
			}
		}
	}

	public boolean checkItemSelected(String[] itemSelectedText) {
		// get ra bao nhieu item da duoc chon
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();
		// get het text o tren dropdown
		String allItemSelectedText = driver.findElement(By.xpath("//button[@class='ms-choice']/span")).getText();
		System.out.println("Text da chon = " + allItemSelectedText);

		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			for (String item : itemSelectedText) {
				if (allItemSelectedText.contains(item)) {
					break;
				}
			}
			return true;

		} else {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
