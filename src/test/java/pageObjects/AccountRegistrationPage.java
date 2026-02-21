package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {

		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement firstName;
	
	@FindBy(xpath="//*[@name='lastname']")
	WebElement lastName;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement email;
	
	@FindBy(xpath="//*[@name='telephone']")
	WebElement telephone;
	
	@FindBy(xpath="//*[@name='password']")
	WebElement password;
	
	@FindBy(id = "input-confirm")
	WebElement confirmedpassword;
	
	@FindBy(xpath="//input[@name='agree']")
     WebElement agreeterm;
	
	@FindBy(xpath="//*[@value='Continue']")
	WebElement continuebtn;
	
	@FindBy(xpath = "")
	WebElement confirmedmsg;
	
	public void setfirstname(String fname) {
		firstName.sendKeys(fname);
	}	
	
	public void setlastname(String lname) {
		lastName.sendKeys(lname);
	}
	
	public void setemail(String emails) {
		email.sendKeys(emails);
	}
	
	public void settelephone(String telephones) {
		
		telephone.sendKeys(telephones);
		
	}
	
	public void setpassword(String pass) {
		password.sendKeys(pass);
	}
	
	public void setconfpassword (String confpass) {
		confirmedpassword.sendKeys(confpass);
	}
	
	public void checkpolicy() {
		agreeterm.click();
	}
	
	public void clickcontinue() {
		continuebtn.click(); 
	}
	
	public String getconfirmationmsg() {
		try {
		return (confirmedmsg.getText());
		}
		catch (Exception e) {
			return (e.getMessage());
			
		}
		
		
	}

}
