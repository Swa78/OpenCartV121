package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider = "LoginData", dataProviderClass = utilities.DataProviders.class, groups="Datadriven")
	public void verify_loginDDT(String username, String password, String result) {

	    logger.info("******** Test TC003_LoginDDT Started ********");

	    try {
	        HomePage hp = new HomePage(driver);
	        hp.clickmyaccount();
	        hp.clickloginlink();

	        LoginPage lp = new LoginPage(driver);
	        lp.clickusername(username);
	        lp.clickpassword(password);
	        lp.clickloginbutton();

	        MyAccountPage macc = new MyAccountPage(driver);
	        boolean targetPage = macc.ismyaccountPageExist();

	        if (result.equalsIgnoreCase("valid")) {

	            if (targetPage) {
	                macc.clicklogout();
	                Assert.assertTrue(true);
	            } else {
	                Assert.fail("Valid credentials but login failed");
	            }

	        } else if (result.equalsIgnoreCase("invalid")) {

	            if (targetPage) {
	                macc.clicklogout();
	                Assert.fail("Invalid credentials but login succeeded");
	            } else {
	                Assert.assertTrue(true);
	            }

	        } else {
	            Assert.fail("Incorrect result value in Excel: " + result);
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	        Assert.fail(e.getMessage());
	    }

	    logger.info("******** Test TC003_LoginDDT Finished ********");
	}}