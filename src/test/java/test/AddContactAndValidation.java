package test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddContactAndValidation {
	WebDriver driver;
	@Test
	public void userShouldAddAndValidateContact() {
	System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("http://techfios.com/test/billing/?ng=admin/");
		driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.name("password")).sendKeys("abc123");
		driver.findElement(By.name("login")).click();
		driver.findElement(By.linkText("Add Deposit")).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//h5[text()='Add Deposit']")).getText().equalsIgnoreCase("Add Deposit"));
		WebElement element = driver.findElement(By.xpath("//select[@id='account']"));
		Select select = new Select(element);
		select.selectByIndex(7);
		Random rnd = new Random();
		String expectedDescription = "QA_Automation_" + rnd.nextInt(999);
		driver.findElement(By.name("description")).sendKeys(expectedDescription);
		driver.findElement(By.name("amount")).sendKeys(String.valueOf(rnd.nextInt(999)));
		driver.findElement(By.id("submit")).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//table//tbody//*[contains(text(),'" + expectedDescription + "')]")));
		String actualDescription = driver.findElement(By.xpath("//table//tbody//tr[1]//td[1]")).getText();
		System.out.println("Expected:" + expectedDescription);
		System.out.println("Actual:" + actualDescription);
		Assert.assertTrue("Deposit was not found:", actualDescription.equalsIgnoreCase(expectedDescription));
	}
	@After
	public void close() {
		driver.quit();
	}
}
