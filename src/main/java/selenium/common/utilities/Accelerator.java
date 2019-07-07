package selenium.common.utilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import sun.nio.cs.HistoricallyNamedCharset;

public class Accelerator extends PropertyHandler {

	public static WebDriver driver=null;
	DesiredCapabilities BrowserCapabilities;
	private static WebDriverWait wait;
	
	
	public WebDriver getWebDriver()
	{
	if(this.driver==null)
	{
		
		if(getConfProperty(BROWSER).equalsIgnoreCase("IE"))
		{
			BrowserCapabilities = DesiredCapabilities.internetExplorer();
			BrowserCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			setBrowserDriver();
			this.driver = new InternetExplorerDriver();
		}
		else if(getConfProperty(BROWSER).equalsIgnoreCase("CHROME"))
		{
			setBrowserDriver();
			this.driver = new ChromeDriver();
		}
		else if(getConfProperty(BROWSER).equalsIgnoreCase("FIREFOX"))
		{
			this.driver = new FirefoxDriver();
		}
		else
		{
			Assert.assertTrue(true, "Invalid browser name : "+getConfProperty(BROWSER));
		}
		EventFiringWebDriver eventhHandler = new EventFiringWebDriver(this.driver);
		EventListner eventListner = new EventListner();
		eventhHandler.register(eventListner);
		this.driver = eventhHandler;
		
	}

	return this.driver;
}
	
	private void setBrowserDriver() {
	
	System.setProperty(getDriverName(getConfProperty(BROWSER)), getDriverPath(getConfProperty(BROWSER)));
	
	}
	

	

	private String getBrowserDriver(String BrowserDriver)
	{
		String DriverPath = new File(System.getProperty("user.dir")+"/src/common/resources/Drivers/"+BrowserDriver+".exe").getAbsolutePath();
		return DriverPath;
		
	}

	public void launchApplication(String Environment)
	{
		if(Environment.equalsIgnoreCase("QA"))
		{
			System.out.println("url : "+getProjProperty(APPLICATION_QA_URL));
			getWebDriver().get(getProjProperty(APPLICATION_QA_URL));
			
		}
		else if(Environment.equalsIgnoreCase("UAT"))
		{
			getWebDriver().get(getProjProperty(APPLICATION_UAT_URL));
		}
		
		
	}
	
	
	public static void takeScreenshot(String ScreenshotTitle, String ScreenshotDesc) throws Exception {
		
		String Sceenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64); 
		//String filePath = screenShotName.toString();
		String path = "<img src=\"data:image/png;base64,"+Sceenshot+"\" alt=\"sdsd\">";
		Reporter.log(ScreenshotTitle+":"+ScreenshotDesc+":"+path);

		}
	
	
	public void waitForElement(WebElement element)
	{
		seleniumwait().until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public WebDriverWait seleniumwait()
	{
		this.wait = new WebDriverWait(getWebDriver(), 60);
		return this.wait;
	}
	
	
	public void closeBrowser()
	{
		getWebDriver().close();
	}
	
	
	public void setMobileCapabilities() throws MalformedURLException
	{
		WebDriver mobileDriver;
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("BROWSER_NAME", "Android");
		cap.setCapability("VERSION", "7.1.1");
		cap.setCapability("deviceName", "d0e5e4c5");
		cap.setCapability("platformName", "Android");
		cap.setCapability("appPackage", "com.android.calculator2");
		mobileDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
	
		 WebElement two=driver.findElement(By.name("2"));
		   two.click();
		   WebElement plus=driver.findElement(By.name("+"));
		   plus.click();
		   WebElement four=driver.findElement(By.name("4"));
		   four.click();
		   WebElement equalTo=driver.findElement(By.name("="));
		   equalTo.click();
		   //locate the edit box of the calculator by using By.tagName()
		   WebElement results=driver.findElement(By.tagName("EditText"));
			//Check the calculated value on the edit box
		assert results.getText().equals("6"):"Actual value is : "+results.getText()+" did not match with expected value: 6";
	}
	
	
	
	
	
}
