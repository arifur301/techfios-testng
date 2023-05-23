package test;

import java.util.Random;

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
		// Set chrome as default browser
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
// Open chrome browser
		driver = new ChromeDriver();
// Go to desired website
		driver.get("http://techfios.com/test/billing/?ng=admin/");
// Type username in the username field
		driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("techfiosdemo@gmail.com");
// Type password in the password field
		driver.findElement(By.name("password")).sendKeys("abc123");
		// Click on Sign In button
		driver.findElement(By.name("login")).click();

		// Go to add deposit page
		driver.findElement(By.linkText("Add Deposit")).click();

		// Validate Add Deposit page displayed
		Assert.assertTrue(
				driver.findElement(By.xpath("//h5[text()='Add Deposit']")).getText().equalsIgnoreCase("Add Deposit"));
		// Select an account
		WebElement element = driver.findElement(By.xpath("//select[@id='account']"));
		Select select = new Select(element);
		select.selectByIndex(7);
		// select.selectByIndex(8);
		// Random Description
		Random rnd = new Random();
		String expectedDescription = "QA_Automation_" + rnd.nextInt(999);
		driver.findElement(By.name("description")).sendKeys(expectedDescription);
		// Add amount
		driver.findElement(By.name("amount")).sendKeys(String.valueOf(rnd.nextInt(999)));
		// Click submit
		driver.findElement(By.id("submit")).click();
		// Thread.sleep(3000) instead use explicit wait
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//table//tbody//*[contains(text(),'" + expectedDescription + "')]")));
		// Comparison
		String actualDescription = driver.findElement(By.xpath("//table//tbody//tr[1]//td[1]")).getText();
		System.out.println("Expected:" + expectedDescription);
		System.out.println("Actual:" + actualDescription);
		// Validation of the added deposit
		Assert.assertTrue("Deposit was not found:", actualDescription.equalsIgnoreCase(expectedDescription));
	}

	@After
	public void close() {
		driver.quit();
	}
}
