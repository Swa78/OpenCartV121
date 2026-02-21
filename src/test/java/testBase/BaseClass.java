package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;   //Log4j
import org.apache.logging.log4j.core.Logger;    //Log4j
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
	
public static WebDriver driver;

public org.apache.logging.log4j.Logger logger;

public Properties p;


@SuppressWarnings("deprecation")
@BeforeClass(groups={"sanity","Regression","Master"})
@Parameters({"OS","browser"})
public void setup(@Optional("Windows") String os, @Optional("chrome") String br)
        throws InterruptedException, IOException {
		
		//Loading confi.properties file
		FileReader file= new FileReader("./src//test//resources//Config.properties");
		p= new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(BaseClass.class);
		
		///////////////////Selenium Grid/////////////////////
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
       {
	   DesiredCapabilities cap =new DesiredCapabilities();
   
	   //os
	   
	   if(os.equalsIgnoreCase("windows")) 
	   {
		   cap.setPlatform(Platform.WIN11);   
	   }
	   else if (os.equalsIgnoreCase("mac"))
	   {
		   cap.setPlatform(Platform.MAC);
	   }
	   else {
		   System.out.println("No matching browser");
		   
		   return;
	   }
	   
	   //browser
	   
	   switch(br.toLowerCase()) {
	   case "chrome" : cap.setBrowserName("chrome");
	   break;
	   
	   case "edge" : cap.setBrowserName("MicrosoftEdge");
	   break;
	   
	   default : System.out.println("No macthing Browser");
	   return;
	   
	   }
	   
	 //  driver=new RemoteWebDriver(new URL("http://192.168.1.8:4444/wd/hub"),cap);
 }

		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
		
		switch(br.toLowerCase()) {
		
		case "chrome" : driver= new ChromeDriver();  
		break;
		
		case "firefox" : driver = new FirefoxDriver();
		break;
		
		case "edge" : driver= new EdgeDriver();
		break;
		
		
		default : System.out.println("invalid browser");   // if the browser not exist then No need to execute rest of code
		return;
		}
	}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("Url"));       //Reading URL from properties file
		Thread.sleep(3000);
		driver.manage().window().maximize();
	}
	
	 @AfterClass (groups= {"sanity","Regression","Master"})
	     void tearDown() {
		driver.quit();
		
	}
	
	/// Dynamically Data
		public String randomestring() {	
		String generatedstring =	RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
		}
		
		public String randomnumber() {
			
			String randomnumeric = RandomStringUtils.randomNumeric(10);
			return randomnumeric;
		}
		
		public String randomsStringNumber() {
			
			String generatedstring =	RandomStringUtils.randomAlphabetic(3);
			String randomnumeric = RandomStringUtils.randomNumeric(3);
			return(generatedstring+"@"+randomnumeric);
		}

           public String capturescreen(String tname) {
        	   
        	   String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        	   
        	   TakesScreenshot takescreenshot = (TakesScreenshot) driver;
        	   File sourcefile = takescreenshot.getScreenshotAs(OutputType.FILE);
        	   
        	   String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" +tname+ " " +timestamp+ " .png";
        	   File targetfile = new File(targetFilePath);
        	   
        	   sourcefile.renameTo(targetfile);
			   return targetFilePath;   
           }	
}
