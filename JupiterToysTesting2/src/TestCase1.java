import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class TestCase1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","/Users/kunjiv/Downloads/chromedriver" );
		WebDriver driver = new ChromeDriver();
		driver.get("https://jupiter.cloud.planittesting.com/#/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		
		Properties obj = new Properties();
        FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"//src//objects.properties");		
	    obj.load(objfile);
	    
	    
		//Clicking login and entering username and password
		driver.findElement(By.linkText(obj.getProperty("login"))).click();
        driver.findElement(By.id(obj.getProperty("username"))).sendKeys("user");
		driver.findElement(By.id(obj.getProperty("password"))).sendKeys("password");
        driver.findElement(By.xpath(obj.getProperty("LoginSubmit"))).click();
        
		String message = driver.findElement(By.xpath(obj.getProperty("LoginErrorMessage"))).getText();
		String expectedText = obj.getProperty("LoginErrorText");
	    if(message.equalsIgnoreCase(expectedText))
	    {
	    	System.out.print("Test case 1 passed");
	    }
	    else
	    {
	    	System.out.print("Test case 1 failed");
	    }

	}

}
