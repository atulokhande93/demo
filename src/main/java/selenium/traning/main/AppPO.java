package selenium.traning.main;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import selenium.common.utilities.Accelerator;

/**
 * Hello world!	
 *
 */
public class AppPO extends Accelerator
{
	
	
	public AppPO()
	{
		PageFactory.initElements(getWebDriver(), Accelerator.class);
	}
	
	
	@FindBy(name="q")
	private WebElement searchBox;
	

	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	@FindBy(name="btnG")
	private WebElement searchBtn;


	public void VerifyHeader() throws Exception
	{
		getWebDriver().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		String BrowserHeader = driver.getTitle();
		System.out.println("Verifyiing the Header");
		Assert.assertTrue(true, "Verifying the Header");
		Assert.assertEquals(false, false);
		if(BrowserHeader.equals("Google"))
		{
			Assert.assertTrue(true, "Header Verified sccessfully");
			takeScreenshot("Google page Header","Header verified Successfully");
			
		}
		else
		{
			Assert.assertTrue(false, "Header Verified sccessfully");
		}
		//search();
	}
	
	
	public void search() throws Exception {
	System.out.println("I am Test method and I am searching for cars");
	waitForElement(getWebDriver().findElement(By.name("q")));
	getWebDriver().findElement(By.name("q")).sendKeys("cars");
//	getSearchBox().sendKeys("Cars");
	getWebDriver().findElement(By.name("btnG")).click();
//	getSearchBtn().click();
	 
	//Wait for the results to appear
	Thread.sleep(2000);
	takeScreenshot("Car searching in Google","Cars Searched successfully");
	if(true){
	Assert.assertTrue(true); 
	}
	else{
	Assert.assertTrue(false);
	}
	

	
	}
	
}
