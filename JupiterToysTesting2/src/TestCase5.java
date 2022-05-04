import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TestCase5 {

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
	    
	    //Clicking on the "Teddy-bear" item
		driver.findElement(By.xpath(obj.getProperty("item1"))).click();
        
		//Clicking on the link "cart"
		driver.findElement(By.xpath(obj.getProperty("cart"))).click();
	
		//Clicking on checkout button
		driver.findElement(By.xpath(obj.getProperty("checkout"))).click();
	    
		//Entering user details in checkout page
	    driver.findElement(By.id(obj.getProperty("forename"))).sendKeys("John");
	    driver.findElement(By.id(obj.getProperty("surname"))).sendKeys("Thomas");
	    driver.findElement(By.id(obj.getProperty("email"))).sendKeys("JohnThomas@gmail.com");
	    driver.findElement(By.id(obj.getProperty("telephone"))).sendKeys("122333333333");
	    driver.findElement(By.id(obj.getProperty("address"))).sendKeys("no 10,day street");
	    Select visatype = new Select(driver.findElement(By.id(obj.getProperty("cardtype"))));
	    visatype.selectByVisibleText("Visa");
	    driver.findElement(By.id(obj.getProperty("cardno"))).sendKeys("1234 9876 1234 9876");
	    
	    //Clicking on submit on checkout page
	    driver.findElement(By.id(obj.getProperty("checkout_submit"))).click();
	    
	    //Getting the order success message and checking if entered name is appearing in message
	    String ordersuccessmsg=driver.findElement(By.xpath(obj.getProperty("ordersuccess"))).getText();
	    if(ordersuccessmsg.contains("John"))
	    {
	    	System.out.print("Test case 5 passed");
	    }
	    else
	    {
	    	System.out.print("Test case 5 failed");
	    }
	 
	
	}
}


