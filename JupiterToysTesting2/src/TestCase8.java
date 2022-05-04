import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestCase8 {

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver","/Users/kunjiv/Downloads/chromedriver" );
		WebDriver driver = new ChromeDriver();
		driver.get("https://jupiter.cloud.planittesting.com/#/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		
		Properties obj = new Properties();
        FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"//src//objects.properties");		
	    obj.load(objfile);
	    driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
	    
		//Clicking on the "Start Shopping" link
	    driver.findElement(By.xpath(obj.getProperty("startshopping"))).click();		
		
	   //Clicking on item1-teddy bear
		driver.findElement(By.xpath(obj.getProperty("item1"))).click();
        
		//Clicking on cart button
		driver.findElement(By.xpath(obj.getProperty("cart"))).click();
		
		//Clicking on checkout button
		driver.findElement(By.xpath(obj.getProperty("checkout"))).click();
		
		//Verify if user is able to checkout without entering mandatory fields cardtype
		WebElement name =driver.findElement(By.id(obj.getProperty("forename")));
	    name.sendKeys("John");
	    
	    
	    driver.findElement(By.id(obj.getProperty("surname"))).sendKeys("Thomas");
	    
	    WebElement email = driver.findElement(By.id(obj.getProperty("email")));
	    email.sendKeys("JohnThomas@gmail.com");
	    
	    
	    driver.findElement(By.id(obj.getProperty("telephone"))).sendKeys("123333345");
	    
	    WebElement address = driver.findElement(By.id(obj.getProperty("address")));
	    address.sendKeys("no.15,elis street");
	   
	    
	    Select visatype = new Select(driver.findElement(By.id(obj.getProperty("cardtype"))));
	     visatype.selectByVisibleText("");
	   
	    WebElement visatypedrop = driver.findElement(By.id(obj.getProperty("cardtype")));
	    String visatypemsg=visatypedrop.getAttribute("validationMessage");
	    
	    if(!visatypemsg.isEmpty())
	    {
	    System.out.print("Visa type field validation message displayed:\t\n" +visatypemsg);
	    }
	    else
	    {
	    	System.out.print("Visa type field validation message not  displayed:\t\n" +visatypemsg);
	    }
	    WebElement cardno= driver.findElement(By.id(obj.getProperty("cardno")));
	    cardno.sendKeys("122 222 2222");

	    
	    driver.findElement(By.id(obj.getProperty("checkout_submit"))).click();
	    Thread.sleep(1000);
	    //Checking if order processing message is getting displayed.
	    try
	    {
	    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	    if(driver.findElement(By.xpath(obj.getProperty("processingmsg"))).isEnabled())
	    {
	    	System.out.print("Processing done-Testcase8 Failed");
	    }
	    }
	    catch(NoSuchElementException e)
	    {
	    	System.out.print("Processing not done-Testcase 8 passed"+"\n");
	    }
	  

	    	

	}

}
