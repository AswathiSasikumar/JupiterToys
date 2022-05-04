import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase4 {

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
		
		//Getting name of item in cart
		String itemname = driver.findElement(By.xpath(obj.getProperty("cartitemname"))).getText();
		driver.findElement(By.xpath(obj.getProperty("removeitem"))).click();
		
		//Getting item name displayed in message box
		String cartitemname=driver.findElement(By.xpath(obj.getProperty("itemnamedisplayed"))).getText();
	    driver.findElement(By.xpath(obj.getProperty("yesbutton"))).click();
	    
	    //Checking if itemname in cart and item name in remove item message box is the same
	    if(itemname.equals(cartitemname))
	    {
	    	System.out.print("Testcase 4 passed");
	    }
	    else
		{
			System.out.print("Test case 4 failed");
		}

	}
}
