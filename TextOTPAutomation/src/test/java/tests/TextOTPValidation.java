package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.BrowserManager;
import utils.GetOTP;

public class TextOTPValidation {
	
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		driver = BrowserManager.getDriver();
	}
	
	@Test
	public void tc_otpvalidation() throws Exception {
		
		//Launch MSO
		driver.get("https://login-qa-mssip.morganstanleyclientserv.com/ux/");
		Thread.sleep(1000);
		
		//Login Page
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("twv_user3");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Welcome1");
		driver.findElement(By.xpath("//button[@id='btnLogin']")).click();
		Thread.sleep(1000);
		
		//Verify Identity Page
		driver.findElement(By.xpath("//button[@track-id='verify-identity__continue']")).click();
		Thread.sleep(1000);
		
		//Select MFA options
		driver.findElement(By.xpath("//button[@track-id='verify-identity_deliver-otp-code__text-my-phone']")).click();
		Thread.sleep(1000);
		
		//Enter OTP
		String otpNo = GetOTP.getOTPNumber();
		driver.findElement(By.id("ms-secureCode")).sendKeys(otpNo);
		Thread.sleep(1000);
		System.out.println("OTP entered is: "+otpNo);
		driver.findElement(By.xpath("//button[@track-id='verify-identity_verify-otp-code__authorize']")).click();
		Thread.sleep(1000);
		
		//Save Device
		driver.findElement(By.xpath("//button[contains(normalize-space(text()),'Save this device')]")).click();
		Thread.sleep(1000);
		
		//Verify Device registration confirmation message
		WebElement eleRegConfPage = driver.findElement(By.xpath("//h1[contains(normalize-space(text()),'Congratulations! This device has been successfully registered')]"));
		if(eleRegConfPage.isDisplayed()) {
			System.out.println(eleRegConfPage.getText() + " is displayed");
		} else {
			System.out.println(eleRegConfPage.getText() + " is displayed");
		}
		
		//Continue to go to Home page
		driver.findElement(By.xpath("//button[@track-id='verify-identity_device-added__continue']")).click();
		Thread.sleep(3000);
	}
	
	@AfterTest
	public void postTestSteps() {
		driver.quit();
	}

}
