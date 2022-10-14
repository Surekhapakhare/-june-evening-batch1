package testClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import baseClasses.Base1;
import pomClasses.HomePage;
import pomClasses.LoginPage;
import utilClasses.Util1;

public class VerifyUserCanLogin {
	
static WebDriver driver;

	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest extentTest;
	
	LoginPage lp;
	HomePage hp;
	
	
	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) throws IOException {
		
		htmlReporter = Base1.getHtmlReporter();
		reports = Base1.getExtentReports();
		extentTest = Base1.getTest("VerifyUserCanLogin");
		
		driver = Base1.getDriver(browser);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
	}
	
	
	@Test
	public void VerifyUserCanLogIn() throws InterruptedException, IOException {
		lp.enterEmailID();
		lp.enterPassword();
		lp.clickOnSubmitBtn();
		
		String profileName = hp.getProfileName();
		
		Assert.assertNotEquals(profileName, "Akshay","Profile name is not matching");
		
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException{
		
		if(result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "Test : "+result.getName());
		}else if(result.getStatus() == ITestResult.FAILURE) {
			
			String path = Util1.getScreenshot(driver, result.getName());
			
			extentTest.log(Status.FAIL, "Test : "+ result.getName() , MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			
		}else if(result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP, "Test : "+result.getName());
		}
		
	}
	
	
	@AfterClass
	public void afterClass() {
		reports.flush();
	}

}
