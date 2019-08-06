package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Drop {
    WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor javascriptExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);
		javascriptExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Jquery() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInDropDownList("//span[@id='number-button']","//ul[@id='number-menu']//div", "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		Thread.sleep(2000);
		
		selectItemInDropDownList("//span[@id='number-button']","//ul[@id='number-menu']//div", "12");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='12']")).isDisplayed());
		Thread.sleep(2000);
		
		selectItemInDropDownList("//span[@id='number-button']","//ul[@id='number-menu']//div", "15");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());
		Thread.sleep(2000);
		
		selectItemInDropDownList("//span[@id='number-button']","//ul[@id='number-menu']//div", "2");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='2']")).isDisplayed());
		Thread.sleep(2000);
	}
	@Test
	public void TC_02_Angular() throws Exception {
		driver.get("https://material.angular.io/components/select/examples");
		selectItemInDropDownList("//mat-label[text()='State']","//mat-option/span", "Georgia");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Georgia']")).isDisplayed());
		Thread.sleep(2000);
		
		selectItemInDropDownList("//mat-label[text()='State']","//mat-option/span", "Michigan");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Michigan']")).isDisplayed());
		Thread.sleep(2000);
		
		selectItemInDropDownList("//mat-label[text()='State']","//mat-option/span", "Arkansas");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Arkansas']")).isDisplayed());
		Thread.sleep(2000);
		
		selectItemInDropDownList("//mat-label[text()='State']","//mat-option/span", "Hawaii");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Hawaii']")).isDisplayed());
		Thread.sleep(2000);
	}
	
	@Test
	public void TC_03_KendoUI() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		selectItemInDropDownList("//span[@class='k-input']","//ul[@id='color_listbox']//li", "Black");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='k-widget k-dropdown']//span[text()='Black']")).isDisplayed());
		Thread.sleep(2000);
		
		selectItemInDropDownList("//span[@class='k-input']","//ul[@id='color_listbox']//li", "Orange");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='k-widget k-dropdown']//span[text()='Orange']")).isDisplayed());
		Thread.sleep(2000);
		
		selectItemInDropDownList("//span[@class='k-input']","//ul[@id='color_listbox']//li", "Grey");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='k-widget k-dropdown']//span[text()='Grey']")).isDisplayed());
		Thread.sleep(2000);
		
	}
	
	public void selectItemInDropDownList(String parentLocator, String allItem, String expected) throws Exception
	{
		
		WebElement openListofItem = driver.findElement(By.xpath(parentLocator));
		javascriptExecutor.executeScript("arguments[0].click();", openListofItem);
		//openListofItem.click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItem)));
		
		List <WebElement> listItemInDropDown = driver.findElements(By.xpath(allItem));
		System.out.println("All Items in the DropDown List:" + listItemInDropDown.size());
		
		for(WebElement Item: listItemInDropDown)
		{
			String actualText = Item.getText();
			System.out.println("Actual Text = "+ actualText);
			if(actualText.equals(expected))
			{
				
				// Lẹnh dùng chạy TC JQuery
				javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", Item);
				Item.click();
				
				// Lệnh dùng chạy TC KendoUI):
				javascriptExecutor.executeScript("arguments[0].click();", Item);
				
				Thread.sleep(4000);
				break;
			}
			
		}
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}