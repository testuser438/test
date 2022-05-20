package Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Base {
	
	public static WebDriver driver;
	
	@BeforeMethod
	public void setUp() throws FileNotFoundException, IOException {
		Properties prop= new Properties();
		prop.load(new FileReader("/Flipkart/src/test/java/Config/config.properties"));
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(prop.getProperty("url"));
	}
	
	
	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}

}
