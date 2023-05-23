package test;

import java.util.Random;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddNewAccount {
	WebDriver driver;
	@Test
	public void userShouldBeAbleToAddNewAccount() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("http://techfios.com/test/billing/?ng=admin/");
		driver.findElement(By.cssSelector("input[id^='usern']")).sendKeys("techfiosdemo@gmail.com");
	    driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("abc123");
	    driver.findElement(By.xpath("//button[contains(text(),Sign)]")).click();	
	    
	    driver.findElement(By.xpath("//*[@id=\'side-menu\']/li[5]/a/span[1]")).click();
	    Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\'side-menu\']/li[5]/ul/li[1]/a")).click();
		Thread.sleep(3000);
		Random rnd = new Random();
		String accountTitle = "QA_John"+ rnd.nextInt(999);
		String description = "Month"+rnd.nextInt(99);
		driver.findElement(By.cssSelector("input[id^=account]")).sendKeys(accountTitle);
		driver.findElement(By.cssSelector("input.form-control[name='description']")).sendKeys(description);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("input[id$='ance']")).sendKeys(String.valueOf(rnd.nextInt(999)));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[3]/div[1]/div/div/div[2]/form/button")).click();
	}
	public void close() {
		driver.quit();
	}
}
