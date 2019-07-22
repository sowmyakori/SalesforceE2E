package com.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;





public class SaleForceTest extends ReusableMethods{
	//WebDriver driver;
	//static ExtentTest logger;
	//static ExtentReports reports;
	@BeforeClass
	public static void intializeDriver() {
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Dell-Latitude-E6430\\Selenium_training\\geckodriver.exe");
		
	}
	@AfterClass
	public static void flushReport() {
		reports.endTest(logger);
		reports.flush();
	}
	@BeforeMethod
	public void openBrowser() {	
	//	createTestReport("Launching URL");
		launchURL("https://login.salesforce.com");
		
	}
	@AfterMethod
	public void closeBrowser() {
		logger.log(LogStatus.PASS, "Testcase execution completed");
		driver.close();
	}
	
	@Test(enabled=true)
	public void TC01_loginWithoutPassword()
	{
		createTestReport("TC01_loginWithoutPassword");	
		WebElement userName = driver.findElement(By.id("username")); 
		enterText(userName,"sowmya.kori1987-suj6@force.com", "UserName");
		
		WebElement loginButton = driver.findElement(By.id("Login"));
		clickButton(loginButton, "login button");
		
		WebElement loginError = driver.findElement(By.id("error"));
		validateTextMessage(loginError, "Please enter your password.", "Error message");		
	}
	
	@Test(enabled=true)
	public void TC02_LoginToSFDC() {
		createTestReport("TC02_LoginToSFDC");
		WebElement userName = driver.findElement(By.id("username")); 
		enterText(userName,"sowmya.kori1987-suj6@force.com", "UserName");
		
		WebElement password = driver.findElement(By.id("password"));
		enterText(password, "welcome123", "Password");
		
		WebElement loginButton = driver.findElement(By.id("Login"));
		clickButton(loginButton, "login button");
	}

	@Test(enabled=true)
	public void TC03_RememberUserNameChecked() throws InterruptedException
	{
		createTestReport("TC03_RememberUserNameChecked");
		WebElement userName = driver.findElement(By.id("username"));
		enterText(userName, "sowmya.kori1987-suj6@force.com", "Username");
		
		WebElement password = driver.findElement(By.id("password"));
		enterText(password,"welcome123", "password");
		
		WebElement rememberMeCheck = driver.findElement(By.id("rememberUn"));
		selectCheckBox(rememberMeCheck, "Check box");
		WebElement loginButton = driver.findElement(By.id("Login"));
		clickButton(loginButton, "Login Button");
		Thread.sleep(5000);
		int size = driver.findElements(By.id("lexNoThanks")).size();
		if(size!=0)
		{			
			driver.findElement(By.id("tryLexDialogX")).click();
		}
		else {
			WebElement profileImg = driver.findElement(By.xpath("//div[@class='profileTrigger branding-user-profile bgimg slds-avatar slds-avatar_profile-image-small circular forceEntityIcon']//span[@class='uiImage']"));
			clickOnImage(profileImg, "Profile Image");
			
			WebElement classicSF = driver.findElement(By.xpath("//a[text()='Switch to Salesforce Classic']"));
			clickOnLink(classicSF, "Classic salesForce Link");
		}
		WebElement logoutImage = driver.findElement(By.id("userNav-arrow"));
		clickOnImage(logoutImage, "Logout Image");
		
		
		WebElement logoutLink=driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		clickOnLink(logoutLink, "Logout Link");
		Thread.sleep(3000);
		
		WebElement actualUserName = driver.findElement(By.id("idcard-identity"));
		validateTextMessage(actualUserName, "sowmya.kori1987-suj6@force.com", "UserName");
		
	}
	
	@Test(enabled=true)
	public void TC04A_ForgotPassword() {
		createTestReport("TC04A_ForgotPassword");
		WebElement forgotPwdLink = driver.findElement(By.id("forgot_password_link"));
		clickOnLink(forgotPwdLink, "Forgot password Link");
		WebElement userEmail = driver.findElement(By.id("un"));
		enterText(userEmail, "sowmya.kori1987-suj6@force.com", "UserName");
		WebElement continueButton = driver.findElement(By.id("continue"));
		clickButton(continueButton, "Continue button");
		WebElement resetMsg = driver.findElement(By.xpath("//div[@class='message']"));
		validateTextMessage(resetMsg, "We've sent you an email with a link to finish resetting your password.", "reset Message");
	}

	@Test(enabled= true)
	public void TC04B_WrongCredential()
	{
		createTestReport("TC04B_WrongCredential");
		WebElement userName = driver.findElement(By.id("username"));
		enterText(userName, "123", "Username");
		
		WebElement password = driver.findElement(By.id("password"));
		enterText(password, "22131", "Password");
		WebElement rememberMeCheck = driver.findElement(By.id("rememberUn"));
		selectCheckBox(rememberMeCheck, "Rememberme checkbox");
		WebElement loginButton = driver.findElement(By.id("Login"));
		clickButton(loginButton, "Loginbutton");
		WebElement actualErrorMsg = driver.findElement(By.id("error"));
		validateTextMessage(actualErrorMsg, "actualErrorMsg", "Error Message");		
	}
	
	@Test(enabled =true)
	public void TC05_SelectUserMenu() throws InterruptedException {
		createTestReport("TC05_SelectUserMenu");
		login();
		WebElement userNavArrow = driver.findElement(By.id("userNav-arrow"));
		clickOnImage(userNavArrow, "UserMenu");
		
		WebElement myProfile = driver.findElement(By.xpath("//a[contains(text(),'My Profile')]"));
		isElementDisplayed(myProfile, "My Profile");
		
		WebElement mySetting = driver.findElement(By.xpath("//a[contains(text(),'My Settings')]"));
		isElementDisplayed(mySetting, "My Settings");
		
		WebElement devConsole = driver.findElement(By.xpath("//a[contains(text(),'Developer Console')]"));
		isElementDisplayed(devConsole, "Developer Console");
		
		WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		isElementDisplayed(logout, "Logout");
	}
	

	public static void login() throws InterruptedException {
		WebElement userName = driver.findElement(By.id("username"));
		enterText(userName, "sowmya.kori1987-suj6@force.com", "Username");
		
		WebElement password = driver.findElement(By.id("password"));
		enterText(password, "welcome123", "Password");
		
		WebElement loginButton = driver.findElement(By.id("Login"));
		clickButton(loginButton, "Login Button");
		Thread.sleep(5000);
		int size = driver.findElements(By.id("lexNoThanks")).size();
		if(size!=0)
		{			
			driver.findElement(By.id("tryLexDialogX")).click();
		}
		else {
	
		WebElement profileImg = driver.findElement(By.xpath("//div[@class='profileTrigger branding-user-profile bgimg slds-avatar slds-avatar_profile-image-small circular forceEntityIcon']//span[@class='uiImage']"));
		if(profileImg.isEnabled()) {
			clickOnImage(profileImg, "Profile Image");
			
			WebElement classicSF = driver.findElement(By.xpath("//a[text()='Switch to Salesforce Classic']"));
			clickOnLink(classicSF, "Classic salesForce Link");
		}
		}	
		Thread.sleep(4000);
	}
}
