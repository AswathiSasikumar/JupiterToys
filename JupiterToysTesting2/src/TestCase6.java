import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestCase6 {

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver","/Users/kunjiv/Downloads/chromedriver" );
		WebDriver driver = new ChromeDriver();
		driver.get("https://jupiter.cloud.planittesting.com/#/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		
		Properties obj = new Properties();
        FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"//src//objects.properties");		
	    obj.load(objfile);
	  
		//Clicking on the "Start Shopping" link
	    driver.findElement(By.xpath(obj.getProperty("startshopping"))).click();		
		
	    //Clicking on item1-Teddy bear
		driver.findElement(By.xpath(obj.getProperty("item1"))).click();
        
		//Clicking on cart button
		driver.findElement(By.xpath(obj.getProperty("cart"))).click();
		
		
		//Clicking on checkout button
		driver.findElement(By.xpath(obj.getProperty("checkout"))).click();
	    
		//Entering incomplete details in checkout page
	    driver.findElement(By.id(obj.getProperty("forename"))).sendKeys("x");
	    driver.findElement(By.id(obj.getProperty("surname"))).sendKeys("x");
	    driver.findElement(By.id(obj.getProperty("email"))).sendKeys("x");
	    driver.findElement(By.id(obj.getProperty("telephone"))).sendKeys("x");
	    driver.findElement(By.id(obj.getProperty("address"))).sendKeys("x");
	    Select visatype = new Select(driver.findElement(By.id(obj.getProperty("cardtype"))));
	    visatype.selectByVisibleText("Visa");
	    driver.findElement(By.id(obj.getProperty("cardno"))).sendKeys("x");
	    
	   //clicking submit button in checkout page
	    driver.findElement(By.id(obj.getProperty("checkout_submit"))).click();
	    
	    //Checking if processing order element is appearing in page.If appearing order got processed with incomplete details which means testcase failed.
	    try
	    {
	    driver.findElement(By.xpath(obj.getProperty("processingmsg")));
	    }
	    catch(NoSuchElementException e)
	    {
	    	System.out.print("Test Case 6 passed");
	    }
	    finally
	    {
	    	if(driver.findElement(By.xpath(obj.getProperty("processingmsg"))).isDisplayed())
	    	{
	    		System.out.print("Test Case 6 failed");
	    	}
	    }
	    	


	}

}
