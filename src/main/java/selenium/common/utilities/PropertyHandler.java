package selenium.common.utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.Assert;

import com.mysql.jdbc.AssertionFailedException;


public class PropertyHandler {

	
	private static Properties confProp;
	private static Properties appProp;
	
	final static String CONFIG_PROPERTIES_FILE="Config/config.properties";
	final static String APPLICATION_PROPERTIES_FILE="Config/application.properties";
	
	final static String APPLICATION_QA_URL = "application.qa.url";
	final static String APPLICATION_UAT_URL = "application.uat.url";
	final static String BROWSER = "browser";
	
	public PropertyHandler() {
		this.confProp = loadProp(CONFIG_PROPERTIES_FILE);
		this.appProp = loadProp(APPLICATION_PROPERTIES_FILE);
	}
	
	
	
	public Properties loadProp(String PropertiesFile)
	{
		
		Properties pro = new Properties();
		InputStream inpStream =getClass().getClassLoader().getResourceAsStream(PropertiesFile);
		try {
			pro.load(inpStream);
		} catch (IOException e) {
			Assert.assertTrue(false,"Given Properties file not found : "+PropertiesFile);
			
			e.printStackTrace();
			return null;
		}
		return pro;
		
	}
	
	public String getConfProperty(String Propertyname)
	{
		return this.confProp.getProperty(Propertyname);
		
	}
	
	public void setConfProperty(String propertyName,String propertyValue)
	{
		this.confProp.setProperty(propertyName, propertyValue);
		
	}
	
	public String getProjProperty(String Propertyname)
	{
		return this.appProp.getProperty(Propertyname);
	}
	
	public void setProjProperty(String propertyName,String propertyValue)
	{
		
		
	}

	public String getDriverPath(String driverName)
	{
		String driverPath = null;
		
		if(driverName.equalsIgnoreCase("ie"))
		{
			driverPath = new File(System.getProperty("user.dir")+"/src/common/resources/Drivers/IEDriverServer.exe").getAbsolutePath();
		}
		else if(driverName.equalsIgnoreCase("chrome"))
		{
			driverPath = new File(System.getProperty("user.dir")+"/src/common/resources/Drivers/chromedriver.exe").getAbsolutePath();
		}
		else
		{
			Assert.assertTrue(false, "invalid Browser Name");
		}
		return driverPath;
	}
	
	
	public String getDriverName(String browserName) {
		
		String driverName = null;
		
		if(browserName.equalsIgnoreCase("ie"))
		{
			driverName = "webdriver.ie.driver";
		}
		else if(browserName.equalsIgnoreCase("chrome"))
		{
			driverName = "webdriver.chrome.driver";
		}
		else
		{
			Assert.assertTrue(false, "invalid Browser Name");
		}
		
	
		return driverName;
	}
}
	
