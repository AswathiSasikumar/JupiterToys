import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase2 {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
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
		
	    //Clicking on item1-teddy bear
		driver.findElement(By.xpath(obj.getProperty("item1"))).click();
		
		String itemprice = driver.findElement(By.xpath(obj.getProperty("item1_price"))).getText();
		
		driver.findElement(By.xpath(obj.getProperty("cart"))).click();
		
		String cartprice = driver.findElement(By.xpath(obj.getProperty("cartprice"))).getText();
		
		if(itemprice.equalsIgnoreCase(cartprice))
		{
			System.out.print("Test case 2 passed");
		}
		else
		{
			System.out.print("Test case 2 failed");
		}
		
	}

}
