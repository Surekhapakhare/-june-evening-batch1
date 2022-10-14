package pomClasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilClasses.Util1;

public class ProfilePage extends Util1{
	
	
WebDriver driver;
	
	@FindBy(xpath="//span[text()='Personal Information']")
	private WebElement personalInfoText;
	
	@FindBy(xpath="//div[text()='Manage Addresses']")
	private WebElement manageAddressText;
	
	@FindBy(xpath="//div[@class='_1QhEVk']")
	private WebElement addNewAddressButton;
	
	@FindBy(xpath="//textarea[@name='addressLine1']")
	private WebElement fullAddress;
	
	@FindBy(xpath="//button[text()='Save']")
	private WebElement saveButton;
	
	@FindBy(xpath="//div[@class='_1CeZIA']")
	private List<WebElement> addressElementList;
	
	
	private By element = By.xpath("//div[@class='_1QhEVk']");
	
	
	
	public ProfilePage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		
		this.driver = driver;
	}
	
	
	public boolean checkUserOnProfilePage() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(personalInfoText));
		}catch(Exception e) {
			return false;
		}
		return true;
		
	}
	
	public void clickOnManageAddress() {
		manageAddressText.click();
	}
	
	public void addNewAddress(List<String> addressDetails) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(addNewAddressButton));
		
		addNewAddressButton.click();
		
		for(int i=1; i<=4; i++) {
			driver.findElement(By.xpath("//input[@tabindex='"+i+"']")).sendKeys(addressDetails.get(i-1));
		}
		
		fullAddress.sendKeys(addressDetails.get(4));
		
		saveButton.click();
		
	}


	public int getAddressCount() {
		
		return addressElementList.size();
	}
	

}
