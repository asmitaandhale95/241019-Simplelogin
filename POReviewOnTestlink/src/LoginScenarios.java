 import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public  class LoginScenarios 
{
public static WebDriver driver;
public static String APIKey = "be10a8bcee1c212d1b072dc094bb9942";
public static String serverUrl = "http://127.0.0.1:8666/testlink-1.9.19/lib/api/xmlrpc/v1/xmlrpc.php";
public static  String testlinkprojectName = "POReview";
public static  String testPlanName = "NewPOReviewPlan";
public static  String testCaseName = "PO---1";
public static  String buildName = "POBuild1";
	
	@Test
	public void validLogin() throws Exception
	{
	System.setProperty("webdriver.chrome.driver","E:\\Asmita\\Selenium\\POReview20819\\POReviewOnTestlink\\Resources\\chromedriver.exe");
	String result = "";
	String exception = "";
		try
		{
			driver = new ChromeDriver();
			driver.get("http://192.168.1.12/Aras11_SP8_PCCS/Client/X-salt=std_11.0.0.6493-X/scripts/Innovator.aspx");
			result = TestLinkAPIResults.TEST_PASSED;
			updateResult("PO---1",null,result);
		}
		catch(Exception e)
		{
			result = TestLinkAPIResults.TEST_FAILED;
	        exception = e.getMessage();
	        updateResult("PO---1",exception,result);
		}
	
	try
	{
		driver.switchTo().frame("main");
		//Enter username
		driver.findElement(By.id("username")).sendKeys("Flx_Entity4_Author1");

		//Enter Password
		driver.findElement(By.id("password")).sendKeys("123");
	
		//Select the database
		Select selectdb = new Select(driver.findElement(By.id("database_select")));
		selectdb.selectByIndex(0);
		
		//Click on login button
		driver.findElement(By.id("login.login_btn_label")).click();
		System.out.println("Login in aras is successfuk");
		driver.manage().window().maximize();
		 result = TestLinkAPIResults.TEST_PASSED;
		//Enter your test case ID here
		 updateResult("PO---1",null,result);
	}
	catch(Exception e)
	{
		result = TestLinkAPIResults.TEST_FAILED;
        exception = e.getMessage();
        updateResult("PO---1",null,result);
	}
}

	public void updateResult(String testCaseName, String exception, String results) throws TestLinkAPIException 
	{
			TestLinkAPIClient testlink = new TestLinkAPIClient(APIKey,serverUrl);
			testlink.reportTestCaseResult(testlinkprojectName, testPlanName, testCaseName, buildName, exception, results);
	}
}
