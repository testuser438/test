package Test;

import org.testng.annotations.Test;

import Pages.LandingPage;

public class SearchMaximumPrice extends Base {

	@Test
	public void findMaximumPrice() throws InterruptedException {
		LandingPage page= new LandingPage(driver);
		page.searchPhone("iphone 13 mini");
		page.storePriceOfItems();
	}
	
}
