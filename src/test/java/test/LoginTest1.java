package test;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class LoginTest1 {
	WebDriver driver;
	@Test
	public void validUserShouldLogin() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://techfios.com/test/billing/?ng=admin/");
		driver.findElement(By.xpath("//*[@type='text']")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.xpath("//*[contains(@placeholder, 'assword') and contains(@class, 'ontrol')]")).sendKeys("abc123");
		driver.findElement(By.xpath("//*[contains(@type,'sub')and text()='Sign in']")).click();
		String dashboardTitleXpath = "//div[@id='page-wrapper']//descendant ::h2[contains(text(),'Dashboard')]";
		
		Thread.sleep(3000);
		
		String expectedTitle = "Dashboard- TechFios Test Application - Billing";
		String actualTitle = driver.getTitle();
		
//		if(expectedTitle.equalsIgnoreCase(actualTitle)) {
//			System.out.println("Test Pass!");
//			
//		}else {
//			System.out.println("Dashboard Page did not display");
//			throw new RuntimeException("Test Failed");
//		}
		
		Assert.assertEquals("Dashboard Page did not display!", expectedTitle, actualTitle);
		
		
// 		Explicit wait		
	//	WebDriverWait wait = new WebDriverWait(driver,1);
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dashboardTitleXpath)));
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(dashboardTitleXpath))));
	
		driver.close();
		driver.quit();
	}

	
	
}
