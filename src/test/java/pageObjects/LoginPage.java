package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//*[@id='input-email']")
	WebElement enterusername;
	
	@FindBy(xpath = "//*[@id='input-password']")
	WebElement enterpassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement clicklogin;
	
	public void clickusername(String email) {
		enterusername.sendKeys(email);
	}
	
	public void clickpassword(String password) {
		enterpassword.sendKeys(password);
	}
	
	public void clickloginbutton() {
		clicklogin.click();;
	}

}

