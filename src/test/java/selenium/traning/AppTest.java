package selenium.traning;



import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import selenium.common.utilities.Accelerator;
import selenium.common.utilities.EventListner;
import selenium.traning.main.AppPO;

/**
 * Unit test for simple App.
 */
//@Listeners(EventListner.class)
public class AppTest extends Accelerator
{
	AppPO po = new AppPO();
	
	@BeforeTest
	public void launchApplicationandLogin() throws MalformedURLException
	{
		
		launchApplication("QA");
		
		
	}
	
	@Test
	public void verifyHeaderofApp1() throws Exception
	{
		System.out.println("demo");
		po.VerifyHeader();		
		
	}
	

	
	
	
	@AfterTest
	public void CloseDriver()
	{
		closeBrowser();
	}
	
	
}
