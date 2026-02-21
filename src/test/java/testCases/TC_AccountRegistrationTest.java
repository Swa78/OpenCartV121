package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_AccountRegistrationTest extends BaseClass {
	
	@Test (groups={"Regression","Master"})
	public void verify_account_registration() throws InterruptedException {
		
		
		logger.info("*** Starting TC_AccountRegistrationTest");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickmyaccount();
		logger.info("**Clicked on Myaccount link**");
		
		hp.clickregisterlink();
		logger.info("**Clicked on Registration**");
		
		AccountRegistrationPage reqpage=new AccountRegistrationPage(driver);
		reqpage.setfirstname(randomestring().toUpperCase());
		reqpage.setlastname(randomestring().toUpperCase());
		reqpage.setemail(randomestring()+"@gmail.com");
		reqpage.settelephone(randomnumber());
		
		String password =randomsStringNumber();  // need to store in variable bcz 
		
		reqpage.setpassword(password);
		reqpage.setconfpassword(password);
		
		reqpage.checkpolicy();
		reqpage.clickcontinue();
		
		/*String confirmationmessage = reqpage.getconfirmationmsg();
		
		if(confirmationmessage.equals("Your account has been created")) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
			logger.error("Test Failed..");
			logger.debug("Debug logs...");
		} */
		
	//	Assert.assertEquals(confirmationmessage, "Your account has been created");
		
	}
	catch (Exception e) {
	
		Assert.fail();  
	}	
	}
	
	
}
