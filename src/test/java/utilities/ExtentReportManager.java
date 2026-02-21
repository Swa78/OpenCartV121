package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.activation.DataSource;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceFileResolver;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	  public ExtentSparkReporter sparkreporter;   //UI of the report  >> Theme
      public ExtentReports extent;    //Populate common info in the report >>Tester Name, Project Name, Version, Module number,OS version etc.
      public ExtentTest test;         // Creating test cases entries in the report and update status of the test method
	
	String repName;
	
	    public  void onStart(ITestContext testcontext) {
		
		/*SimpleDateFormat df =new SimpleDateFormat("YYYY.MM.DD.HH.MM.SS");                    /// For date and time
		Date dt= new Date();
		String currentdatetimestamp =df.format(dt); */
		
		String timestamp = new SimpleDateFormat("yyyy.MM.DD.HH.mm.ss").format(new Date());
		
		repName = "Test Report-" + timestamp +".html"; 
		
		sparkreporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "\\reports\\"+repName);        //Location of report

		sparkreporter.config().setDocumentTitle("Opencart Automation Report");
		sparkreporter.config().setReportName("Functional Testing");
		sparkreporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customer");
		extent.setSystemInfo("Computer Name", "Local Host");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Tester Name", "Swapnil");
		
		String os = testcontext.getCurrentXmlTest().getParameter("OS");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Broswer", browser);
		
		List <String> includegroups = testcontext.getCurrentXmlTest().getIncludedGroups();
        if (!includegroups.isEmpty()) {
        	extent.setSystemInfo("Groups", includegroups.toString());
        }	
	}
	 public void onTestSuccess(ITestResult result) {
		 test=extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());                                   //To display groups in report
		 test.log(Status.PASS, "Test Case passed is:"+ result.getName());
		 
	 }
	 
	 public void onTestFailure(ITestResult result) {

	   test=extent.createTest(result.getTestClass().getName());
	   test.assignCategory(result.getMethod().getGroups());
	   
	   test.log(Status.FAIL,"Test case failed is:" +result.getName() );
	   test.log(Status.INFO, "Test case failed cause is :" +result.getThrowable().getMessage());
	   
	  
	   try {
		   
	   String imgpath = new BaseClass().capturescreen(result.getName());
	   test.addScreenCaptureFromPath(imgpath);
	   
	   }
	   catch (Exception e1) {
		e1.printStackTrace();   
	   }
	 }
	
	 public void onTestSkipped(ITestResult result) {

		 test=extent.createTest(result.getName());
		 test.log(Status.SKIP, "Test cases skipped is :" +result.getName());
		 
	 }
	  
	  public void onFinish(ITestContext context) {
	  extent.flush();
		  
		  String pathofExtentReport = System.getProperty("user.dir") +"\\reports\\"+repName;
		  File extentreport = new File(pathofExtentReport);
		  
		  
		  try {
			Desktop.getDesktop().browse(extentreport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		/*  try {
			URL url = new URL ("file:///" +System.getProperty("user.dir")+"\\reports:\\repName");
			
			ImageHtmlEmail email=new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlegmsil.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("swapnilbagul78@gmail.com", "Password"));
			email.setSSLOnConnect(true);
			email.setFrom("swapnilbagul78@gmail.com");                                                                                         //Sendeer
			email.setSubject("Test Results");
			email.setMsg("Test Result");
			email.addTo("swapnilbagul78@gmail.com");
			email.attach(url,"extent report", "Please check report....");
			email.send();
				
		} catch (MalformedURLException | EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  */
		  
	  }
	
}
