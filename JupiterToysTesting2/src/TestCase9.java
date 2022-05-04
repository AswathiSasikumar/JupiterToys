import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase9 {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver","/Users/kunjiv/Downloads/chromedriver" );
		WebDriver driver = new ChromeDriver();
		driver.get("https://jupiter.cloud.planittesting.com/#/");
		
		Properties obj = new Properties();
        FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"//src//objects.properties");		
	    obj.load(objfile);
	    
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
	    
	    //Clicking on contact link
	    driver.findElement(By.linkText(obj.getProperty("contact"))).click();		
	    
	    //Entering the details
	    driver.findElement(By.xpath(obj.getProperty("forename_contact"))).sendKeys("John");
	    driver.findElement(By.xpath(obj.getProperty("surname_contact"))).sendKeys("Thomas");
	    driver.findElement(By.xpath(obj.getProperty("email_contact"))).sendKeys("JohnThomas@gmail.com");
	    driver.findElement(By.xpath(obj.getProperty("telephone_contact"))).sendKeys("122333333333");
	    driver.findElement(By.xpath(obj.getProperty("message_contact"))).sendKeys("no 10,day street");
	    
	    driver.findElement(By.xpath(obj.getProperty("submit_contact"))).click();
	    String msgproccessed=driver.findElement(By.xpath(obj.getProperty("ordersuccess"))).getText();
	    try
	    {
	    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	    
	    //Checking if sending feedback message is displayed along with the name previously entered
	    if((driver.findElement(By.xpath(obj.getProperty("processingmsg"))).isEnabled())&&(msgproccessed.contains("John")))
	    {
	    	System.out.print("Processing done-Testcase 9 Passed");
	    }
	    }
	    catch(NoSuchElementException e)
	    {
	    	System.out.print("Processing not done"+"\n"+"Testcase 9 Failed");
	    }
	  

	}

}
