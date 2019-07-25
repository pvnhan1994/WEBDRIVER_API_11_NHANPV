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

public class Topic_08_Drop{
	WebDriver driver;
	JavascriptExecutor javascriptExecutor;
	WebDriverWait waitExplicit;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		javascriptExecutor = (JavascriptExecutor) driver;
		waitExplicit = new WebDriverWait (driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	public void TC_01_DropdownList() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		WebElement jobrole = driver.findElement(By.xpath("//select[@id='job1']"));
		Select select = new Select(jobrole);
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
	public void selectMultiInDropdown(String parentXpath, String allItemXpath, String[] expectedValueItem) throws Exception {
		//1; Click vao cai dropdown cho no xo het tat ca ca gia tri 
		WebElement parentDropdown = driver.findElement(By.xpath(parentXpath));
		javascriptExecutor.executeScript("arguments[0].click();", parentDropdown);
		
		//2: Cho cho tat ca cac gia tri trong dropdown duoc load ra thanh cong
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		List <WebElement> allItems= driver.findElements(By.xpath(allItemXpath));
		System.out.println("Tat ca phan tu trong dropdown =" +allItems.size());
		//Duyệt qua hết tất cả các phần tử cho đến khi thỏa mãn điều kiện 
		for(WebElement childElement: allItems){
			//January, April, July
			for(String item:expectedValueItem) {
				//3: scroll den item can chon (neu nhu item can chon co the nhin thay thi k can scroll
				javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
				Thread.sleep(1000);
				//4: click vao item can chon 
				javascriptExecutor.executeScript("arguments[0].click();", parentDropdown);
				Thread.sleep(1000);
			
				List <WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected'//input"));
				System.out.println("Item selected= " + itemSelected.size());
				if(expectedValueItem.length == itemSelected.size()) {
					break;
				}
			}
		}
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
