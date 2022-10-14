package pomClasses;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilClasses.Util1;

public class HomePage extends Util1{
	
WebDriver driver;
	
	@FindBy(xpath="//div[text()='Akshay']")
	private WebElement profileName;
	
	@FindBy(xpath="//div[text()='My Profile']")
	private WebElement myProfileText;
	

	@FindBy(xpath="//input[@name='q']")
	private WebElement searchField;
	
	@FindBy(xpath="//div[@class='_1AtVbE']")
	private WebElement textOnProductList;
	
	@FindBy(xpath="//div[@class='_2kHMtA']")
	private WebElement productList;
	
	@FindBy(xpath="//div[@class='_30jeq3 _1_WHN1']")
	private List<WebElement> productPriceList;
	
	
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	
	public String getProfileName() throws InterruptedException {
		Thread.sleep(3000);
		
		waitTillElementAppear(driver, profileName);
		
		String pName = profileName.getText();
		
		return pName;
	}
	
	public void searchProduct() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement newElement = wait.until(ExpectedConditions.visibilityOf(searchField));
		
		newElement.sendKeys("realme");
		newElement.sendKeys(Keys.ENTER);
		
	}
	
	public String getValidText() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(productList));
		
		String actualWholeText = textOnProductList.getText();
		System.out.println(actualWholeText);
		return actualWholeText;
	}
	
	public String getLowestPriceOfProduct() {
		
		System.out.println("Entered to get lowest Price :" +productPriceList.size());
		List<Integer> priceList = new ArrayList<>();
		for(WebElement priceElement: productPriceList) {
			System.out.println(Integer.parseInt(priceElement.getText().replace("₹", "").replace(",", "")));
			priceList.add(Integer.parseInt(priceElement.getText().replace("₹", "").replace(",", "")));	
		}
		
		System.out.println(priceList);
		Collections.sort(priceList);
		return priceList.get(0).toString();
	}

	public void switchToPage(int a) {

		driver.findElement(By.xpath("//a[@class='ge-49M' and text()='"+a+"']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='ge-49M _2Kfbh8' and text()='"+a+"']")));
		
	}

	public void hoverProfileName() {
		
		Actions act = new Actions(driver);
		
		act.moveToElement(profileName).perform();
		
	}

	public void clickOnMyProfile() {
		JavascriptExecutor js = (JavascriptExecutor)driver;		
		js.executeScript("arguments[0].click();", myProfileText);
	//	myProfileText.click();
	}

}
