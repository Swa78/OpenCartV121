package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"sanity","Master"})
	public void Verify_login() throws InterruptedException {
		
		logger.info("*****Starting of TC002_LoginTest******");
		
		try {
		//HomePage
		HomePage hp= new HomePage(driver);
		hp.clickmyaccount();
		hp.clickloginlink();
		
		//LoginPage
		LoginPage lp= new LoginPage(driver);
		lp.clickusername(p.getProperty("email"));
		lp.clickpassword(p.getProperty("password"));
		lp.clickloginbutton();
		
		//MyAccount
		MyAccountPage macc = new MyAccountPage(driver);
		
		boolean targetPage = macc.ismyaccountPageExist();
		
		Assert.assertTrue(targetPage); //Assert.assertEquals(targetPage, true,"Login Failed");  ;
		}
		
		catch(Exception e){
			Assert.fail();
		
		logger.info("****End of C002_LoginTest");
	}
	
	}
}
