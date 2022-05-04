import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase11 {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
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
		
	    //Clicking on item1-Teddy bear
		driver.findElement(By.xpath(obj.getProperty("item1"))).click();
        
		//Clicking on cart button
		driver.findElement(By.xpath(obj.getProperty("cart"))).click();
		
        //Clicking on reomve item under actions
		driver.findElement(By.xpath(obj.getProperty("removeitem"))).click();
		 
		Thread.sleep(1000);
        //Clicking No button in remove item pop up message box
		driver.findElement(By.xpath(obj.getProperty("nobutton"))).click();

	}
}
