package testClasses;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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
import pomClasses.ProfilePage;

public class VerifyUserCanAddNewAddress {
	
static WebDriver driver;

	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest extentTest;
	
	LoginPage lp;
	HomePage hp;
	ProfilePage pp;
	
	String oldAddressCount;
	String newAddressCount;
	
	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) throws IOException {
		
		htmlReporter = Base1.getHtmlReporter();
		reports = Base1.getExtentReports();
		extentTest = Base1.getTest("VerifyUserCanAddNewAddress");
		
		driver = Base1.getDriver(browser);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		pp = new ProfilePage(driver);
	}
	
	
	@Test(priority = 4)
	public void verifyUserCanOpenProfilePage() {
		//hover on profileName
		hp.hoverProfileName();
		//click on my profile
		hp.clickOnMyProfile();
		//check page is opened
		boolean onPage = pp.checkUserOnProfilePage();
		Assert.assertTrue(onPage);	
		oldAddressCount = String.valueOf(pp.getAddressCount());
	}
	
	@DataProvider(name="addressData")
	public Object[][] getData() {
		Object[][] k = {{"Akshay", "8956235623","413512","Nanded Road", "B-22, A pune"}, {"Sneha","7845124512", "411023", "Warje", "A-32, B shivaji nagar, near english school"}};
		return k;
	}
	
	@Test(priority = 5, dataProvider="addressData")
	public void addNewAddress(String name, String phone, String pincode, String locality, String fullAddress) {
		pp.clickOnManageAddress();
		List<String> addressDetails = Arrays.asList(name, phone, pincode, locality, fullAddress);
		pp.addNewAddress(addressDetails);
		newAddressCount = String.valueOf(pp.getAddressCount());
		boolean isCountMatching = newAddressCount.equals(oldAddressCount);
		Assert.assertFalse(isCountMatching);	
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "Test : "+result.getName());
		}else if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, "Test : "+result.getName());
			String path = lp.getScreenshot(driver, result.getName());
			
			extentTest.log(Status.FAIL, "Test : "+ result.getName() , MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}else if(result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP, "Test : "+result.getName());
		}
	}
	
	@AfterClass
	public void afterClass() {
		reports.flush();
		
		Base1.unloadDriver();
		
	}

}
