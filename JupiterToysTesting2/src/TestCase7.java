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

public class TestCase7 {

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
		
		//Clicking on submit button without entering value in any of the fields in checkout page and checking for validation message in mandatory fields
		WebElement name =driver.findElement(By.id(obj.getProperty("forename")));
	    name.sendKeys("");
	    String forenameValidmsg = name.getAttribute("validationMessage");
	    
	    driver.findElement(By.id(obj.getProperty("surname"))).sendKeys("");
	    
	    WebElement email = driver.findElement(By.id(obj.getProperty("email")));
	    email.sendKeys("");
	    String emailValidmsg = name.getAttribute("validationMessage");
	    
	    driver.findElement(By.id(obj.getProperty("telephone"))).sendKeys("");
	    
	    WebElement address = driver.findElement(By.id(obj.getProperty("address")));
	    address.sendKeys("");
	    String addressValidmsg = address.getAttribute("validationMessage");
	    
	    Select visatype = new Select(driver.findElement(By.id(obj.getProperty("cardtype"))));
	     visatype.selectByVisibleText("");
	   
	    WebElement visatypedrop = driver.findElement(By.id(obj.getProperty("cardtype")));
	    String visatypemsg=visatypedrop.getAttribute("validationMessage");
	    System.out.print(visatypemsg);
	    
	    WebElement cardno= driver.findElement(By.id(obj.getProperty("cardno")));
	    cardno.sendKeys("");
	    String cardnoValidMsg=cardno.getAttribute("validationMessage");
	    
	    driver.findElement(By.id(obj.getProperty("checkout_submit"))).click();
	    
	    //Checking if order processing message is geeting displayed
	    try
	    {
	    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	    driver.findElement(By.xpath(obj.getProperty("processingmsg")));
	    }
	    catch(NoSuchElementException e)
	    {
	    	System.out.print("Processing not done"+"\n");
	    }
	    
	    //Checking if validation messages are appearing for all mandatory fields
	    if((forenameValidmsg.isEmpty())|| (emailValidmsg.isEmpty())||(addressValidmsg.isEmpty())||(cardnoValidMsg.isEmpty())||(visatypemsg.isEmpty()))
	    {   
	    	System.out.print("Forename Validation Message:\t"+forenameValidmsg +"\n" +"Email Validation message:\t"+ emailValidmsg+"\n"+"Address validation message:\t" +addressValidmsg+"\n"+"Card no validation message:\t"+ cardnoValidMsg+"\n"+"Visa type Validation message:\t"+ visatypemsg+"\n");
	    	System.out.print("Test case 7 failed\n");
	    	
	    }
	    else
	    {
	    	System.out.print("Test case 7 passed\n");
	    }

	}

}
