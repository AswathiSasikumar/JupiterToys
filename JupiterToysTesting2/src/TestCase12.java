import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase12 {

	public static void main(String[] args) throws IOException, InterruptedException {
		{
			System.setProperty("webdriver.chrome.driver","/Users/kunjiv/Downloads/chromedriver" );
			WebDriver driver = new ChromeDriver();
			driver.get("https://jupiter.cloud.planittesting.com/#/");
			
			Properties obj = new Properties();
	        FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"//src//objects.properties");		
		    obj.load(objfile);
		    
		    driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		    
			//Clicking on the "Start Shopping" link
		    driver.findElement(By.xpath(obj.getProperty("startshopping"))).click();		
			
            //Clicking on teddy bear item
			driver.findElement(By.xpath(obj.getProperty("item1"))).click();
	        
			//Clicking on cart button
			driver.findElement(By.xpath(obj.getProperty("cart"))).click();
		
			
			//Clicking on Empty cart button
			driver.findElement(By.linkText(obj.getProperty("emptycart"))).click();
			
			Thread.sleep(1000);
			//Clicking on yes button in empty cart pop up message
			driver.findElement(By.xpath(obj.getProperty("yesbutton"))).click();
			
			String message =driver.findElement(By.xpath(obj.getProperty("emptymessage"))).getText();
			if (message.contains("empty"))
			{
				System.out.print("Testcase 12 passed");
			}
			else
			{
				System.out.print("Testcase 12 failed");
			}
		}

	}

}
