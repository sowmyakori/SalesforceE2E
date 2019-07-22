package com.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReusableMethods {
	static WebDriver driver;
//	static ExtentReports reports;
	static ExtentTest logger;
	//static String filename = new SimpleDateFormat("'SampleReport_'MMMddyyHHmmss'.html'").format(new Date());
	static String path="ExtentReports/SFDC.html";
	static ExtentReports reports = new ExtentReports(path);		
	
	
	public static ExtentTest createTestReport(String testName) {

		logger = reports.startTest(testName);
		return logger;
	}
	
	public static void launchURL(String url) {
		driver = new FirefoxDriver();
		if(driver!=null) {
			driver.get(url);
			System.out.println("Launching...." +url);
		//	logger.log(LogStatus.PASS, url+" launched... ");
		}
		else {
			System.out.println("Unable to launch url" +url);
			//logger.log(LogStatus.FAIL, "Unable to launch URL");
		}
	}
	
	public static void enterText(WebElement obj, String text, String objName) {
		if(obj.isEnabled()) {
			obj.sendKeys(text);
			System.out.println("Pass :"+text+" is entered in "+objName);
			logger.log(LogStatus.PASS, text+" is entered in "+objName);
		}
		else {
			System.out.println("Faile :"+objName+" is not enabled please check the application ");
			logger.log(LogStatus.FAIL, objName+" is not enabled please check the application ");
		}
	}
	
	public static void clearText(WebElement obj,String objName) {
		if(obj.isEnabled()) {
			obj.clear();
			System.out.println("Pass :"+objName+" is cleared in ");
			logger.log(LogStatus.PASS, "Cleared Text in "+objName);
		}
		else {
			System.out.println("Faile :"+objName+" is not enabled please check the application ");
			logger.log(LogStatus.FAIL, objName+" is not enabled please check the application ");
		}
	}
	
	public static void clickButton(WebElement obj, String objName) {
		if(obj.isEnabled()) {
			obj.click();
			System.out.println("Pass:"+objName+" is clicked");
			logger.log(LogStatus.PASS, objName+" is clicked");
		}
		else {
			System.out.println("Fail: "+objName+ " is disabled please check application");
			logger.log(LogStatus.FAIL, objName+ " is disabled please check application");
		}
	}
	
	public static void validateTextMessage(WebElement obj, String expectedMsg, String objName) {
		String actualMsg;
		if(obj.isDisplayed()) {
			actualMsg = obj.getText();
			if(expectedMsg.equals(actualMsg) || expectedMsg.contains(actualMsg))
			{
				System.out.println("Pass: expected message "+expectedMsg+" matches acutual message "+actualMsg);
				logger.log(LogStatus.PASS, "expected message "+expectedMsg+" matches acutual message "+actualMsg);
			}
			else {
				System.out.println("Fail: expected message "+expectedMsg+" doesn't matches acutual message "+actualMsg);
				logger.log(LogStatus.FAIL, "expected message "+expectedMsg+" doesn't matches acutual message "+actualMsg);
			}	
				
		}
		else {
			System.out.println("Fail :"+obj+ " is not displayed please check the application");
			logger.log(LogStatus.FAIL, obj+ " is not displayed please check the application");
		}
	}
	
	public static void selectCheckBox(WebElement obj,String objName) {
		if(obj.isEnabled()) {
			if(!obj.isSelected()) {
			obj.click();
			System.out.println("Pass : "+obj+" is selected");
			logger.log(LogStatus.PASS, obj+" is selected");
			}
		}
		else {
			System.out.println("Fail : "+obj+" is not enabled please check the application");
			logger.log(LogStatus.FAIL, obj+" is not enabled please check the application");
		}
	}
	
	public static void deSelectCheckBox(WebElement obj,String objName) {
		if(obj.isEnabled()) {
			if(obj.isSelected()) {
			obj.click();
			System.out.println("Pass : "+obj+" is Deselected");
			logger.log(LogStatus.PASS, obj+" is Deselected");
			}
		}
		else {
			System.out.println("Fail : "+obj+" is not enabled please check the application");
			logger.log(LogStatus.FAIL, obj+ " Unable to deselect the checkbox");
		}
	}
	
	public static void clickOnImage(WebElement obj, String objName) {
		if(obj.isEnabled()) {
			obj.click();
			System.out.println("Pass : "+objName+" is clicked");
			logger.log(LogStatus.PASS, objName+" is clicked");
		}
		else {
			System.out.println("Fail : "+objName+" is not enabled please check the application");
			logger.log(LogStatus.FAIL, objName+" is unable to click on image please check the application");
		}
	}
	
	public static void clickOnLink(WebElement obj, String objName) {
		if(obj.isEnabled()) {
			obj.click();
			System.out.println("Pass : "+objName+" is clicked");
			logger.log(LogStatus.PASS, objName+" is clicked");
		}
		else {
			System.out.println("Fail : "+obj+" is not enabled please check the application");
			logger.log(LogStatus.FAIL, obj+" is not enabled please check the application");
			
		}
	}
	
	public static void isElementDisplayed(WebElement obj, String objName) {
		if(obj.isDisplayed()) {
			System.out.println("Pass :"+objName+" is Displayed");
			logger.log(LogStatus.PASS, objName+" is Displayed");
		}
		else {
			System.out.println("Fail :"+objName+" is not displayed please check application");
			logger.log(LogStatus.FAIL, objName+" is not Displayed");
		}
	}
	
	public static void selectFromList(WebElement obj, String visibleText, String objName) {
		if(obj.isEnabled()) {
			Select select = new Select(obj);
			select.selectByVisibleText(visibleText);

			System.out.println("Pass :"+visibleText+" is selected from list"+objName);
			logger.log(LogStatus.PASS, visibleText+ " is selected from list "+objName);
		}
		else {
			System.out.println("Fail : "+objName+" is not present please check the application");
			logger.log(LogStatus.FAIL, objName+" is not present please check the application");
		}
	}
	
	public static void selectRadioButton(WebElement obj, String objName) {
		if(obj.isEnabled()) {
			obj.click();
			System.out.println("Pass :"+obj+" is selected");
			logger.log(LogStatus.PASS, obj+" is selected");
		}
		else
			System.out.println("Pass :"+obj+" is unable to selected");		
			logger.log(LogStatus.FAIL, obj+" is unable to selected");
	}
	
	public static void deSelectRadioButton(WebElement obj, String objName) {
		if(obj.isEnabled()) {
			obj.click();
			System.out.println("Pass :"+obj+" is deselected");
			logger.log(LogStatus.PASS, obj+" is deselected");
		}
		else
			System.out.println("Pass :"+obj+" is unable to deselected");		
			logger.log(LogStatus.FAIL, obj+" is unable to deselected");
	}
	
	public static void switchToFrame(WebElement obj) {
		if(obj.isEnabled()) {
			driver.switchTo().frame(obj);
			System.out.println("Passed : Switched to frame");	
			logger.log(LogStatus.PASS, "Switched to frame");
		}else
			System.out.println("Failed : Unable to switch between frames");
			logger.log(LogStatus.FAIL, "Failed to switch between frames");
	}
	
	public static void mouseOver(WebElement obj, String objName) {
		if(obj.isEnabled()) {
			Actions action = new Actions(driver);
			action.moveToElement(obj).build().perform();
			System.out.println("Passed : Mouseover on"+objName);
			logger.log(LogStatus.PASS, "Mouseover on"+objName);
		}else
			System.out.println("Failed : Unable to perform Mouse action");
		logger.log(LogStatus.FAIL, "Unable to perform Mouse action");
	}
}
