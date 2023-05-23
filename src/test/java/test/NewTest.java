package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewTest {
	public class SampleTest extends basePage {
		WebDriver driver;
		@BeforeTest
		public void startBrowser() {
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();	
		}
	@Test(priority=1)
	public void sampleTest() {
		driver.get("http://www.google.com");
	}
	@Test(priority=2)
	public void invalidUsersShouldNotBeAbleToLogin() {
		driver.get("http://techfios.com/test/billing/?ng=admin/");
		driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("abc123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		By ALLERT_MSG_LOCATOR = By.xpath("//div[@class = 'wrapper no-navigation preload']");
//		waitForElement(driver,10,ALLERT_MSG_LOCATOR);
		waitForElement(driver, 10, ALLERT_MSG_LOCATOR);
		
	}
	@AfterTest
//	@AfterMethod
	public void close() {
		driver.close();
		driver.quit();
	}
	public void waitForElement(WebDriver driver, int timeInSeconds, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	}
}

	
	


