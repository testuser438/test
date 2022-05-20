package Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Test.Base;

public class LandingPage extends Base{
	public WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super();
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//button)[last()-2]")
	WebElement cancelLogin;
	
	@FindBy(xpath="//input[contains(@title,'Search')]")
	WebElement searchItems;
	
	@FindBy(xpath="//a[contains(@href,'apple')]")
	WebElement iPhoneLink;
	
	public void searchPhone(String item) throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait= new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button)[last()-2]"))).click();
		//cancelLogin.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@title,'Search')]"))).click();
		Thread.sleep(2000);
		searchItems.sendKeys(item);
		Thread.sleep(2000);
		//Actions action= new Actions(driver);
		//action.keyDown(searchItems, Keys.ENTER).build().perform();
		searchItems.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
	}
	
	public void storePriceOfItems() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> phones= driver.findElements(By.xpath("//a[contains(@href,'apple')]"));
		List<Integer> prices= new ArrayList<Integer>();
		for (int i=0; i<phones.size(); i++) {
			String firstPart="(//a[contains(@href,'apple')])[";
			String secondPart="]//div[contains(text(),'iPhone')]/../..//div[contains(@class,'WHN1')]";
			//prices.add(Integer.parseInt(
					String total=driver.findElement(By.xpath(firstPart+(i+1)+secondPart)).getText().substring(1);
					String a= total.substring(0, 2);
					
					String b=total.substring(3);
					
					prices.add(Integer.parseInt(a+b));
			//System.out.println(prices.get(i));
		}
		List<Integer> original= new ArrayList<Integer>(prices);
	Collections.sort(prices,Collections.reverseOrder());
	int index= original.indexOf(prices.get(0));

	System.out.println(prices.get(0));
	String highestPrice=String.valueOf(prices.get(0)).substring(0, 2)+","+String.valueOf(prices.get(0)).substring(2);
	System.out.println(highestPrice);
		String firstPart="(//a[contains(@href,'apple')])[" ;
		String secondPart="]//div[contains(text(),'iPhone')]/../..//div[contains(text(),'";
		String thirdPart="')]";
		String finalXpath=firstPart+(index+1)+secondPart+highestPrice+thirdPart;
		//driver.findElement(By.xpath("//span[text()='Next']")).click();
		String parent=driver.getWindowHandle();
		driver.findElement(By.xpath(finalXpath)).click();
		
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr= windows.iterator();
		while(itr.hasNext()) {
			itr.next();
			driver.switchTo().window(itr.next());
		}
		
		driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
		Thread.sleep(3000);
		Assert.assertEquals(prices.get(0), driver.findElement(By.xpath("((//span[text()='Price details']//following::div)[1]//span)[1]")).getText());
	}
		
	}
	


