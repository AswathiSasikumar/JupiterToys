import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase3 {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
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
        
	    //Clicking on item 1-Teddy bear
		driver.findElement(By.xpath(obj.getProperty("item1"))).click();
		
		//Getting price of item in cart
		String itemprice = driver.findElement(By.xpath(obj.getProperty("item1_price"))).getText();
		itemprice=itemprice.replace("$","");
		
		//click on cart button
		driver.findElement(By.xpath(obj.getProperty("cart"))).click();
		
		//Editing quantity of item in cart
		driver.findElement(By.name(obj.getProperty("quantity"))).clear();
	    driver.findElement(By.name(obj.getProperty("quantity"))).sendKeys("2");
        
	    //Getting the total price of items in cart
	    String subtotal = driver.findElement(By.xpath(obj.getProperty("subtotal"))).getText();
	    
	    float price=Float.parseFloat(itemprice);
	    float totalprice = price * 2;
	    
	    String noofitemsdisplayed = driver.findElement(By.xpath(obj.getProperty("noofitemsdisplayed"))).getText();
	    
	    //Checking if total price has changed based on quantity of items and checking if no of quantity displayed on top has changed to 2.
	    if(subtotal.contains(String.valueOf(totalprice))&& (noofitemsdisplayed.contains("2")))
	    {
	    	System.out.print("Test case 3 passed");
	    }
	    else
		{
			System.out.print("Test case 3 failed");
		}

	}
}
