package baseClasses;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilClasses.Util1;

public class Base1 {
	
	static WebDriver driver;
	static ExtentHtmlReporter htmlReporter;
	static ExtentReports reports;
	static ExtentTest extentTest;
	

	public static WebDriver getDriver(String browser) throws IOException {
		
		if(driver == null) {
			
			if(browser.equals("chrome")) {
				
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}else {
				
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
		
			driver.get("https://www.flipkart.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
		}	
		return driver;
	}
	
	public static void unloadDriver() {
		driver = null;
	}
	
	
	
	public static ExtentHtmlReporter getHtmlReporter() {
		
		if(htmlReporter == null) {
			htmlReporter = new ExtentHtmlReporter("reports.html");
		}
		return htmlReporter;
	}
	
	public static ExtentReports getExtentReports() {
		
		if(reports == null) {
			reports = new ExtentReports();
			reports.attachReporter(htmlReporter);
		}
		return reports;
	}
	
	
	public static ExtentTest getTest(String testName) {
		
		extentTest = reports.createTest(testName);
		
		return extentTest;
	}
	

}
