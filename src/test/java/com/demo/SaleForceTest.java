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
	@Test(enabled=true)
	public void TC06_MyProfileOption() throws InterruptedException {
		login();
		WebElement userNavArrow = driver.findElement(By.id("userNav-arrow"));
		clickOnImage(userNavArrow, "UserMenu");
		
		WebElement myProfile = driver.findElement(By.xpath("//a[contains(text(),'My Profile')]"));
		clickOnLink(myProfile, "My Profile");
		
		WebElement editProfile = driver.findElement(By.xpath("//a[@class='contactInfoLaunch editLink']//img"));
		clickOnImage(editProfile, "Edit Profile");	
		Thread.sleep(5000);
		
		int totalFrames = driver.findElements(By.tagName("iframe")).size();		
		System.out.println("total frames***********"+totalFrames);
		driver.switchTo().frame("contactInfoContentId");
		WebElement aboutTab = driver.findElement(By.xpath("//a[contains(text(),'About')]"));
		clickOnLink(aboutTab, "About Tab");
		
		WebElement lastName = driver.findElement(By.name("lastName"));
		clearText(lastName,  "LastName");
		enterText(lastName, "kori", "LastName");
			
		WebElement saveAllButton = driver.findElement(By.xpath("//input[@class='zen-btn zen-primaryBtn zen-pas']"));
		clickButton(saveAllButton, "SaveAll Button");
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		
		WebElement displayedName= driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']"));
		validateTextMessage(displayedName, "kori", "Displayed Name");
		
		WebElement postLink = driver.findElement(By.xpath("//a[@id='publisherAttachTextPost']"));
		clickOnLink(postLink, "Post Link");
		Thread.sleep(3000);
		WebElement frame = driver.findElement(By.xpath("//iframe[@title='Rich Text Editor, publisherRichTextEditor']"));
		 switchToFrame(frame);
		
		 WebElement postTextArea = driver.findElement(By.xpath("//html[1]//body[1]"));
		enterText(postTextArea, "Welcome", "PostText");

		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		
		WebElement shareButton = driver.findElement(By.xpath("//input[@id='publishersharebutton']"));
		clickButton(shareButton, "Share");				
		
		driver.switchTo().defaultContent();		
		
		List<WebElement> postMsg = driver.findElements(By.xpath("//span[@class='feeditemtext cxfeeditemtext']//p"));
		Iterator<WebElement> itr = postMsg.iterator();
		boolean isPostPresent=false;
		while(itr.hasNext())
		{
			String text = itr.next().getText();
			System.out.println(text);
			if(text.contentEquals("welcome"))
			{
				isPostPresent=true;
				break;
			}			
		}			
	
		Thread.sleep(3000);
		WebElement fileLink = driver.findElement(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'File')]"));
		clickOnLink(fileLink, "File");
		Thread.sleep(5000);
		WebElement uploadButton = driver.findElement(By.id("chatterUploadFileAction"));
		clickButton(uploadButton, "Upload Button");	
		WebElement browse = driver.findElement(By.id("chatterFile"));
		//clickButton(browse, "Browse Button");		
		Thread.sleep(3000);
		enterText(browse, "C:\\Users\\Dell-Latitude-E6430\\Desktop\\Sampledox\\SampleText.odt", "Filename");
		WebElement shareFile = driver.findElement(By.id("publishersharebutton"));
		clickButton(shareFile, "Share Button");
		
		Thread.sleep(5000);
		WebElement image = driver.findElement(By.xpath("//span[@id='displayBadge']"));
		mouseOver(image, "Display Image");

		WebElement addPhoto = driver.findElement(By.id("uploadLink"));
		clickOnLink(addPhoto, "Add Photo");
		Thread.sleep(3000);		
		driver.switchTo().frame("uploadPhotoContentId");
		WebElement choosePhoto = driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadInputFile']"));
		enterText(choosePhoto, "C:\\Users\\Dell-Latitude-E6430\\Desktop\\Sampledox\\SampleImg.jpg", "ChoosePhoto");
		Thread.sleep(7000);
		WebElement saveButton = driver.findElement(By.xpath("//input[@id='j_id0:j_id7:save']"));
		clickButton(saveButton, "Save");		
	}
	@Test(enabled=true)
	public void TC07_MySettings() throws InterruptedException {
		createTestReport("TC07_MySettings");
		login();
		WebElement userNavArrow = driver.findElement(By.id("userNav-arrow"));
		clickOnImage(userNavArrow, "UserMenu");
		
		WebElement mySettings = driver.findElement(By.xpath("//a[contains(text(),'My Settings')]"));
		clickOnLink(mySettings, "My Settings");
				
		WebElement personal = driver.findElement(By.xpath("//span[@id='PersonalInfo_font']"));
		clickOnLink(personal, "personal");
		
		WebElement  loginHistory = driver.findElement(By.id("LoginHistory_font"));
		clickOnLink(loginHistory, "login History");
		
		WebElement downloadLoginHistory = driver.findElement(By.xpath("//a[contains(text(),'Download login history for last six months, includ')]"));
		clickOnLink(downloadLoginHistory, "Download login History");
		WebElement displayLayout = driver.findElement(By.id("DisplayAndLayout_font"));
		clickOnLink(displayLayout, "Display And Layout");
		
		WebElement customizeTab = driver.findElement(By.id("CustomizeTabs_font"));
		clickOnLink(customizeTab, "Customize My Tab");
		WebElement customApps = driver.findElement(By.id("p4"));
		selectFromList(customApps, "Salesforce Chatter", "Custom Apps List");
		WebElement availableTabs = driver.findElement(By.id("duel_select_0"));
		selectFromList(availableTabs, "Reports", "Available Tabs");
		
		WebElement rightArrow = driver.findElement(By.xpath("//img[@class='rightArrowIcon']"));
		clickOnImage(rightArrow, "Right Arrow");
		
		WebElement saveButton = driver.findElement(By.xpath("//input[@name='save']"));
		clickButton(saveButton, "Save");
		
		WebElement emailLink = driver.findElement(By.xpath("//div[@id='EmailSetup']//a[@class='header setupFolder']"));
		clickOnLink(emailLink, "Email");
		WebElement emailSetting = driver.findElement(By.xpath("//a[@id='EmailSettings_font']"));
		clickOnLink(emailSetting, "Email Setting");
		
		WebElement emailName = driver.findElement(By.id("sender_name"));
		clearText(emailName, " Name");
		enterText(emailName, "abc", "Name");
		
		WebElement emailId = driver.findElement(By.id("sender_email"));
		clearText(emailId, "EmailId");
		enterText(emailId, "abc123@mail.com", "Email Id");
		
		WebElement bcc = driver.findElement(By.xpath("//input[@id='auto_bcc1']"));
		selectRadioButton(bcc, "Bcc");		
		Thread.sleep(3000);
		
		WebElement emailSave = driver.findElement(By.xpath("//input[@name='save']"));
		clickButton(emailSave, "Save");
		driver.switchTo().alert().accept();
	}
	
	@Test(enabled=true)
	public void TC08_DevelopersConsole() throws InterruptedException {
		createTestReport("TC08_DevelopersConsole");
		login();
		WebElement userNavArrow = driver.findElement(By.id("userNav-arrow"));
		clickOnImage(userNavArrow, "UserMenu");
		
		WebElement devConsole = driver.findElement(By.xpath("//a[contains(text(),'Developer Console')]"));
		clickOnLink(devConsole, "Developer Console");
		
		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for(String childWindow : handles)
		{
			if(!childWindow.equals(parentWindow))
			{
				driver.switchTo().window(childWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
	}
	
	@Test(enabled=true)
	public void TC09_Logout() throws InterruptedException {
		createTestReport("TC09_Logout");
		login();
		WebElement userNavArrow = driver.findElement(By.id("userNav-arrow"));
		clickOnImage(userNavArrow, "UserMenu");
		
		WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		clickOnLink(logout, "Logout");
	}
	
	@Test(enabled=true)
	public void TC10_CreateAnAccount() throws InterruptedException {
		createTestReport("TC10_CreateAnAccount");
		login();
		WebElement accountTab = driver.findElement(By.id("Account_Tab"));
		clickOnLink(accountTab, "Account Tab");
		
		WebElement newButton = driver.findElement(By.xpath("//input[@name='new']"));
		clickButton(newButton, "New");
		
		String name ="Account456";
		WebElement accName = driver.findElement(By.xpath("//input[@id='acc2']"));
		enterText(accName, name, "Account Name");
		
		WebElement saveButton = driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@name='save']"));
		clickButton(saveButton, "Save");
		
		WebElement displayedName = driver.findElement(By.xpath("//h2[@class='topName']"));
		validateTextMessage(displayedName, name, "Name");
	}
	
	@Test(enabled=true)
	public void TC11_CreateNewView() throws InterruptedException {
		createTestReport("TC11_CreateNewView");
		login();
		WebElement accountTab = driver.findElement(By.id("Account_Tab"));
		clickOnLink(accountTab, "Account Tab");
		
		WebElement newViewLink = driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		clickOnLink(newViewLink, "New View");
		
		String name = "view100";
		WebElement viewName = driver.findElement(By.id("fname"));
		enterText(viewName, name, "View Name");
		
		WebElement uniqueName = driver.findElement(By.id("devname"));
		enterText(uniqueName, "view100", "Unique Name");
		
		WebElement save = driver.findElement(By.xpath("//div[@class='pbHeader']//input[@name='save']"));
		clickButton(save, "Save");
		
		Select select = new Select(driver.findElement(By.xpath("//select[@class='title']")));
		String selectedName = select.getFirstSelectedOption().getText();
		if(selectedName.equals(name))
			System.out.println("Test Case Passed*********New View Created");
		else
			System.out.println("Test Case Failed**********New View Not created");
	}

	@Test(enabled=true)
	public void TC12_EditView() throws InterruptedException {
		createTestReport("TC12_EditView");
		login();
		WebElement accountTab = driver.findElement(By.id("Account_Tab"));
		clickOnLink(accountTab, "Account Tab");	
				
		WebElement editButton = driver.findElement(By.xpath("//span[@class='fFooter']//a[contains(text(),'Edit')]"));
		clickButton(editButton, "Edit");
		
		String accName = "acc9999";
		WebElement viewName = driver.findElement(By.xpath("//input[@id='fname']"));
		enterText(viewName, accName, "Name");
		
		WebElement fields= driver.findElement(By.xpath("//select[@id='fcol1']"));
		selectFromList(fields, "Account Name", "Fields");
		
		WebElement operator = driver.findElement(By.xpath("//select[@id='fop1']"));
		selectFromList(operator,"contains","Operators");
		
		WebElement value = driver.findElement(By.xpath("//input[@id='fval1']"));
		enterText(value, "a", "Value");
		
		WebElement availableFields = driver.findElement(By.xpath("//select[@id='colselector_select_0']"));
		selectFromList(availableFields,"Last Activity","Available fields");
		
		WebElement addArrow = driver.findElement(By.xpath("//img[@class='rightArrowIcon']"));
		clickOnImage(addArrow, "Arrow Image");
		
		WebElement saveButton = driver.findElement(By.xpath("//div[@class='pbHeader']//input[@value=' Save ']"));
		clickButton(saveButton, "Save");
		
		Select viewList = new Select(driver.findElement(By.name("fcf")));
		String selectedView = viewList.getFirstSelectedOption().getText();
		Assert.assertEquals(accName, selectedView);
	}
	
	@Test(enabled=true)
	public void TC13_MergeAccounts() throws InterruptedException {
		createTestReport("TC13_MergeAccounts");
		login();
		WebElement accountTab = driver.findElement(By.id("Account_Tab"));
		clickOnLink(accountTab, "Account Tab");	
				
		WebElement mergeAccLink = driver.findElement(By.xpath("//a[contains(text(),'Merge Accounts')]"));
		clickOnLink(mergeAccLink, "Merge Link");
		
		WebElement searchField = driver.findElement(By.xpath("//input[@id='srch']"));
		enterText(searchField, "acc", "Search");
		
		WebElement findAccounts = driver.findElement(By.xpath("//input[@name='srchbutton']"));
		clickButton(findAccounts, "Find");
		
		WebElement chk1 = driver.findElement(By.xpath("//input[@id='cid0']"));
		selectCheckBox(chk1, "checkbox");
		WebElement chk2 = driver.findElement(By.xpath("//input[@id='cid1']"));
		selectCheckBox(chk2, "Checkbox");
		WebElement nextButton = driver.findElement(By.xpath("//div[@class='pbTopButtons']//input[@name='goNext']"));
		clickButton(nextButton, "Next Button");
		
		WebElement mergeButton = driver.findElement(By.xpath("//div[@class='pbTopButtons']//input[@name='save']"));
		clickButton(mergeButton, "Merge");
		
		driver.switchTo().alert().accept();
	}
	@Test(enabled=true)
	public void TC14_CreateAccountReport() throws InterruptedException {
		createTestReport("TC14_CreateAccountReport");
		login();
		WebElement accountTab = driver.findElement(By.id("Account_Tab"));
		clickOnLink(accountTab, "Account Tab");	
		
		WebElement lastActivityReport = driver.findElement(By.xpath("//a[contains(text(),'Accounts with last activity > 30 days')]"));
		clickOnLink(lastActivityReport, "Last activity Link");
		
		WebElement dateField = driver.findElement(By.xpath("//input[@id='ext-gen20']"));
		clickOnLink(dateField, "Date Field");
		
		WebElement createDate = driver.findElement(By.xpath("//div[contains(text(),'Created Date')]"));
		clickOnLink(createDate, "Create date");
		
		WebElement fromDate = driver.findElement(By.xpath("//img[@id='ext-gen152']"));
		clickOnImage(fromDate, "Calendar");
		
		WebElement fromToday = driver.findElement(By.xpath("(//button[text()='Today'])"));
		clickButton(fromToday, "Today");
		
		WebElement toDate = driver.findElement(By.xpath("//img[@id='ext-gen154']"));
		clickOnImage(toDate, "Calendar");
		
		WebElement toToday = driver.findElement(By.xpath("(//button[text()='Today'])[2]"));
		clickButton(toToday, "Today");
		
		WebElement saveBtn = driver.findElement(By.xpath("//button[(text()='Save')]"));
		clickButton(saveBtn, "Save");
		Thread.sleep(3000);
		
		WebElement reportName = driver.findElement(By.xpath("//input[@id='saveReportDlg_reportNameField']"));
		enterText(reportName, "Report1", "Report Name");
		
		WebElement uniqueName = driver.findElement(By.xpath("//input[@id='saveReportDlg_DeveloperName']"));
		enterText(uniqueName, "report6125", "Report UniqueName");
		Thread.sleep(3000);
		WebElement saveReport = driver.findElement(By.xpath("//table[@id='dlgSaveReport']//td[@class='x-btn-mc']"));
		clickButton(saveReport, "Save");
		Thread.sleep(3000);
		WebElement runReport = driver.findElement(By.xpath("//button[(text()='Run Report')]"));
		clickButton(runReport, "Run Report");
		Thread.sleep(3000);		
		WebElement reportStatus = driver.findElement(By.xpath("//div[@class='progressIndicator']"));
		validateTextMessage(reportStatus, "Complete", "Status");
		
	}
	@Test(enabled=true)
	public void TC15_VerifyOpportunityDropDown() throws InterruptedException {
		createTestReport("TC15_VerifyOpportunityDropDown");
		login();
		WebElement opportunitiesLink = driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
		clickOnLink(opportunitiesLink, "Oppertunities");
		
		WebElement oppList  = driver.findElement(By.xpath("//select[@id='fcf']"));
		isElementDisplayed(oppList, "Opportunity Dropdown");
	}
	
	@Test(enabled=true)
	public void TC16_CreateNewOpportunity() throws InterruptedException, ParseException {
		createTestReport("TC16_CreateNewOpportunity");
		login();
		
		WebElement opportunitiesLink = driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
		clickOnLink(opportunitiesLink, "Oppertunities");		
		
		WebElement newButton = driver.findElement(By.xpath("//input[@name='new']"));
		clickButton(newButton, "New");
		
		WebElement oppName = driver.findElement(By.xpath("//input[@id='opp3']"));
		enterText(oppName, "newOpportunity", "Opportunity Name");
		
		WebElement searchIcon = driver.findElement(By.xpath("//img[@class='lookupIcon']"));
		clickOnImage(searchIcon, "Search");
		Thread.sleep(4000);
		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for(String childWindow:handles)
		{
			if(!childWindow.equals(parentWindow))
			{
				driver.switchTo().window(childWindow);
				System.out.println(driver.getTitle());
				WebElement searchFrame = driver.findElement(By.id("searchFrame"));
				switchToFrame(searchFrame);			
				WebElement goButton = driver.findElement(By.name("go"));
				clickButton(goButton, "Go Button");
				driver.switchTo().defaultContent();
				WebElement resultFrame = driver.findElement(By.id("resultsFrame"));
				switchToFrame(resultFrame);			
				WebElement firstLink = driver.findElement(By.xpath("//table[@class='list']//tr[2]//th//a"));
				clickOnLink(firstLink, "Link");
				
			}
		}
		driver.switchTo().window(parentWindow);
		
		String date = "09/08/2020";
		
		SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yyyy");
		Date d = formattedDate.parse(date);
		
		String day = new SimpleDateFormat("dd").format(d);
		String month = new SimpleDateFormat("MMMM").format(d);
		String year = new SimpleDateFormat("yyyy").format(d);
		
		WebElement closeDate = driver.findElement(By.xpath("//input[@id='opp9']"));
		clickOnLink(closeDate, "Datte");	
		
		WebElement monthPicker = driver.findElement(By.xpath("//select[@id='calMonthPicker']"));
		selectFromList(monthPicker, month, "Month");	
		
		WebElement yearPicker = driver.findElement(By.xpath("//select[@id='calYearPicker']"));
		selectFromList(yearPicker, year, "Year");
		
		WebElement daypicker = driver.findElement(By.xpath("//td[contains(text(),"+day+")]"));
		clickButton(daypicker, "Day");
		
		WebElement stage = driver.findElement(By.xpath("//select[@id='opp11']"));
		selectFromList(stage, "Closed Won", "Stage");
		
		WebElement probability = driver.findElement(By.xpath("//input[@id='opp12']"));
		clearText(probability, "Probability");
		enterText(probability, "70", "Probability");
		
		WebElement leadSource = driver.findElement(By.xpath("//select[@id='opp6']"));
		selectFromList(leadSource, "Web", "Lead Source");
		
		WebElement saveButton = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@name='save']"));
		clickButton(saveButton, "Save");
	}
	
	@Test(enabled=true)
	public void TC17_OpportunityPipelineReport() throws InterruptedException, ParseException {
		createTestReport("TC17_OpportunityPipelineReport");
		login();
		
		WebElement opportunitiesLink = driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
		clickOnLink(opportunitiesLink, "Oppertunities");
		
		WebElement pipelineLink = driver.findElement(By.xpath("//a[contains(text(),'Opportunity Pipeline')]"));
		clickOnLink(pipelineLink, "Pipeline");
		
		WebElement displayText = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']"));
		validateTextMessage(displayText, "Opportunity Pipeline", "Display Pipeline");
	}
	
	@Test(enabled=true)
	public void TC18_StuckOpportunityReport() throws InterruptedException, ParseException {
		createTestReport("TC18_StuckOpportunityReport");
		login();
		
		WebElement opportunitiesLink = driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
		clickOnLink(opportunitiesLink, "Oppertunities");
		
		WebElement stuckLink = driver.findElement(By.xpath("//a[contains(text(),'Stuck Opportunities')]"));
		clickOnLink(stuckLink, "Stuck Opportunity");
		
		WebElement displayText = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']"));
		validateTextMessage(displayText, "Stuck Opportunities", "DisplayText");		
	}
	
	@Test(enabled=true)
	public static void TC19_QuarterlySummaryReport() throws InterruptedException, ParseException {
		createTestReport("TC19_QuarterlySummaryReport");
			login();		
		WebElement opportunitiesLink = driver.findElement(By.xpath("//a[contains(text(),'Opportunities')]"));
		clickOnLink(opportunitiesLink, "Oppertunities");
		WebElement intervalList = driver.findElement(By.id("quarter_q"));
		selectFromList(intervalList, "Next FQ", "Interval");
		
		WebElement includeList = driver.findElement(By.id("open"));
		selectFromList(includeList, "Open Opportunities", "Include List");
		
		WebElement runReportButton = driver.findElement(By.xpath("//table[@class='opportunitySummary']//input[@name='go']"));
		clickButton(runReportButton, "Report");
		
		WebElement displayText = driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']"));
		 validateTextMessage(displayText, "Opportunity Report", "Display Message");		
	}
	
	@Test(enabled=true)
	public static void TC20_LeadsTab() throws InterruptedException {
		createTestReport("TC20_LeadsTab");
			login();
			WebElement leadLink = driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
			clickOnLink(leadLink, "Lead");
			WebElement displayText = driver.findElement(By.xpath("//h2[@class='pageDescription']"));
			validateTextMessage(displayText, "Home", "Display Text");
	}
	@Test(enabled=true)
	public static void TC21_LeadsSelectView() throws InterruptedException, ParseException {
		createTestReport("TC21_LeadsSelectView");
			login();
			WebElement leadLink = driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
			clickOnLink(leadLink, "Lead");			
			
			WebElement viewlist = driver.findElement(By.xpath("//select[@id='fcf']"));
			clickOnLink(viewlist, "View");
			
			Select select = new Select(viewlist);
			List<WebElement> optList = select.getOptions();
			
			List<String> list1 = new ArrayList<String>();
			for(int i=0;i<optList.size();i++)		
				list1.add(optList.get(i).getText());
						
			List<String> list2 = new ArrayList<String>();
			list2.add("All Open Leads");
			list2.add("My Unread Leads");
			list2.add("Recently Viewed Leads");
			list2.add("Today's Leads");
			Assert.assertEquals(list1, list2);
	}
	@Test(enabled=true)
	public static void TC22_DefaultView() throws InterruptedException, ParseException {
		createTestReport("TC22_DefaultView");
			login();
			WebElement leadLink = driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
			clickOnLink(leadLink, "Lead");	
			Thread.sleep(3000);
			WebElement select = driver.findElement(By.id("fcf"));
			selectFromList(select, "Today's Leads","ViewDropDown");
			
			WebElement userNavArrow = driver.findElement(By.id("userNav-arrow"));
			clickOnImage(userNavArrow, "image");
			WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
			clickOnLink(logout, "Logout Link");			
			Thread.sleep(3000);
			WebElement userName = driver.findElement(By.id("username"));
			enterText(userName, "sowmya.kori1987-suj6@force.com", "Username");
			
			WebElement password = driver.findElement(By.id("password"));
			enterText(password, "welcome123", "Password");
			
			WebElement loginButton = driver.findElement(By.id("Login"));
			clickButton(loginButton, "Login Button");
			Thread.sleep(5000);
			WebElement leadLinkNew = driver.findElement(By.xpath("//a[contains(text(),'Leads')]"));
			clickOnLink(leadLinkNew, "Lead Link");
			
			WebElement goButton = driver.findElement(By.xpath("//span[@class='fBody']//input[@name='go']"));
			clickButton(goButton, "Go button");	

			Select newList = new Select(driver.findElement(By.xpath("//select[@name='fcf']")));
			WebElement selectedOption = newList.getFirstSelectedOption();
			validateTextMessage(selectedOption, "Today's Leads", "Dropdown selected option");
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
