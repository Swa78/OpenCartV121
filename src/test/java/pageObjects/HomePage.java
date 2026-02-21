package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	// WebDriver driver;                                             //This not needed because we have created constructor in BasePage Class
	
	public HomePage(WebDriver driver)
	{
		super(driver);	
	}
	
	@FindBy(xpath="//*[@title='My Account'][@class='dropdown-toggle']")
	WebElement linkmyaccount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement linkRegister;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement linklogin;
	
	public void clickmyaccount() throws InterruptedException {

		linkmyaccount.click();
		
	}
	
	public void clickregisterlink() {
		linkRegister.click();  
	}
	
	public void clickloginlink() {
		linklogin.click();
		
	}	
}
