package test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class loginTest {
	
	// JUnit - Test Automation Tool
	// It executes tests using TAGS
	// The tags starts with "@" followed by key words
	// These key words describe the function of the test
	// @Test - This will act  as the 'main' method 
	// Place these tags right above the method signature
	
	
	@Test
	public void validUsersShouldBeAbleToLogin() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		
//	    Step 1: Open Chrome browser
	    WebDriver pilot = new ChromeDriver();
	    
//		Step 2: Go to https://techfios.com/test/billing/?ng=admin/
	    pilot.get("https://techfios.com/test/billing/?ng=admin/");

// 		Slow down the test a specific time
	    Thread.sleep(3000);
	    
//		Step 3: Type username in the username field (username:techfiosdemo@gmail.com)
//         Two steps process: 1. Location - findElement()
//	    				2. Action(Type(sendText)/click)	    		
	    pilot.findElement(By.id("username")).sendKeys("techfiosdemo@gmail.com");
//		
	    Thread.sleep(3000);
//	    Step 4: Type password in the password field (password: abc123)
	    pilot.findElement(By.name("password")).sendKeys("abc123");
	     Thread.sleep(3000);
//		 Step 5: Click on the submit button
	    pilot.findElement(By.name("login")).click();
//		Step 6: Dashboard page should display (No Code Needed for now - 			  Visually Inspect)
  	    Thread.sleep(5000);
	    pilot.close(); // Close the browser
	    pilot.quit();  // Terminate the ChromeDriver
	    
	    
	    
	    
	
	
	}
	
	

}
