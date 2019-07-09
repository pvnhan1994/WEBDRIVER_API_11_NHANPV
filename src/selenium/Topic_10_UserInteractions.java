package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_UserInteractions {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_MoveToElement_HoverMouse() {
		driver.get("http://www.myntra.com/");
		WebElement profileText = driver.findElement(By.xpath("//span[text()='Profile']"));
		action.moveToElement(profileText).perform();

		WebElement loginBtn = driver.findElement(By.xpath("//a[text()='log in']"));
		Assert.assertTrue(loginBtn.isDisplayed());
		loginBtn.click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());

	}

	public void TC_02_ClickAndHoldElement() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> numberItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("Tong so items " + numberItems.size());

		action.clickAndHold(numberItems.get(0)).moveToElement(numberItems.get(3)).release().perform();

		List<WebElement> numberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(numberSelected.size(), 4);
	}

	public void TC_03_ClickAndHoldElement_Random() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> numberItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("Tong so items " + numberItems.size());

		action.keyDown(Keys.CONTROL).perform();
		action.click(numberItems.get(0));
		action.click(numberItems.get(3));
		action.click(numberItems.get(6));
		action.click(numberItems.get(11));
		action.keyUp(Keys.CONTROL).perform();

		List<WebElement> numberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(numberSelected.size(), 4);
	}

	public void TC_04_DoubleClick() {
		driver.get("http://www.seleniumlearn.com/double-click");

		WebElement doubleBtn = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		action.doubleClick(doubleBtn).perform();

		Assert.assertEquals(driver.switchTo().alert().getText(), "The Button was double-clicked.");
		driver.switchTo().alert().accept();

	}

	public void TC_05_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		WebElement rightClickBtn = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(rightClickBtn).perform();

		WebElement quitBeforeHover = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and not(contains(@class,'context-menu-hover'))]"));
		action.moveToElement(quitBeforeHover).perform();

		WebElement quitAfterHover = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and not(contains(@class,'context-menu-hover')) and contains(@class,'context-menu-visible')]"));
		Assert.assertTrue(quitAfterHover.isDisplayed());

	}

	@Test
	public void TC_06_DragandDrop() {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");

		WebElement sourceCricle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));

		action.dragAndDrop(sourceCricle, targetCircle).perform();
		Assert.assertEquals(targetCircle.getText(), "You did great!");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
