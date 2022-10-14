package pomClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilClasses.Util1;

public class LoginPage extends Util1{
	
	
	// WebElements , constructor and public methods
	
		@FindBy(xpath="(//input[@type='text'])[2]")
		private WebElement emailID;
		
		@FindBy(xpath="//input[@type='password']")
		private WebElement password;
		
		@FindBy(xpath="(//button[@type='submit'])[2]")
		private WebElement loginBth;
		
		
		public LoginPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		
		public void enterEmailID() throws IOException {
			emailID.sendKeys(getConfigData("email"));
		}
		
		public void enterPassword() throws IOException {
			password.sendKeys(getConfigData("password"));
		}
		
		public void clickOnSubmitBtn() {
			loginBth.click();
		}

}
