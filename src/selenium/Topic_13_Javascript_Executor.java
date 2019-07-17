package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Javascript_Executor {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
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
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
