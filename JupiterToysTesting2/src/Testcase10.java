import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testcase10 {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver","/Users/kunjiv/Downloads/chromedriver" );
		WebDriver driver = new ChromeDriver();
		driver.get("https://jupiter.cloud.planittesting.com/#/");
		
		Properties obj = new Properties();
        FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"//src//objects.properties");		
	    obj.load(objfile);
	    
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
	    
	    //clicking on contact link and entering details
	    driver.findElement(By.linkText(obj.getProperty("contact"))).click();		
	    driver.findElement(By.xpath(obj.getProperty("forename_contact"))).sendKeys("John");
	    driver.findElement(By.xpath(obj.getProperty("surname_contact"))).sendKeys("Thomas");
	    
	    //Entering email in invalid format ie.,without @ and .com
	    WebElement email= driver.findElement(By.xpath(obj.getProperty("email_contact")));
	    email.sendKeys("x");
	    String emailValidMsg=email.getAttribute("validationMessage");
	    //Checking if message "Enter email in valid format is displayed
	    if(!emailValidMsg.isEmpty())
	    {
	    	System.out.print("Email validation message displayed:\t"+emailValidMsg);
	    }
	    else
	    {
	    	System.out.print("Email validation message not displayed\n");
	    }
	    driver.findElement(By.xpath(obj.getProperty("telephone_contact"))).sendKeys("122333333333");
	    driver.findElement(By.xpath(obj.getProperty("message_contact"))).sendKeys("no 10,day street");
	    
	    driver.findElement(By.xpath(obj.getProperty("submit_contact"))).click();
	    String msgproccessed=driver.findElement(By.xpath(obj.getProperty("ordersuccess"))).getText();
	    try
	    {
	    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	    if((driver.findElement(By.xpath(obj.getProperty("processingmsg"))).isEnabled())&&(msgproccessed.contains("John")))
	    {
	    	System.out.print("\nFeedback submitted with incorrect email format -Testcase Failed\n");
	    }
	    }
	    catch(NoSuchElementException e)
	    {
	    	System.out.print("Feedback not submitted"+"\n"+"Testcase passed");
	    }

	}

}
