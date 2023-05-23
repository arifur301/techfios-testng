package test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TransferMoney {
	WebDriver driver;

	@Test
	public void userShouldBeAbleToTransferMoney() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("http://techfios.com/test/billing/?ng=admin/");
		driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.name("password")).sendKeys("abc123");
		driver.findElement(By.name("login")).click();

// Click on Transactions in the site Navigation
		driver.findElement(By.xpath("//span[contains(text(),'Transac')]")).click();
		Thread.sleep(3000);
// Click on transfer
		driver.findElement(By.linkText("Transfer")).click();
		Thread.sleep(3000);
		// driver.findElement(By.linkText("Add Deposit")).click();
// Fill in the new transfer form
		
		selectIndex("//select[@id='faccount']",2);
		
//		WebElement elementFrom = driver.findElement(By.xpath("//select[@id='faccount']"));
//		Select select = new Select(elementFrom);
//		select.selectByIndex(2);

		Thread.sleep(3000);

		selectIndex("//select[@id='taccount']", 5);
	
		Random rnd = new Random();
		int randomNumber = rnd.nextInt(999);
		driver.findElement(By.id("description")).sendKeys("Bata" + randomNumber);
		driver.findElement(By.id("amount")).sendKeys("$" + randomNumber);
// Click on submit
		driver.findElement(By.xpath("//button[@id='submit']")).click();
		Thread.sleep(3000);

// Validate transfer posted on the table

	}

	public void selectIndex(String xpath, int index) {
		WebElement element = driver.findElement(By.xpath(xpath));
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	@After
	public void close() {
		driver.quit();
	}
}
