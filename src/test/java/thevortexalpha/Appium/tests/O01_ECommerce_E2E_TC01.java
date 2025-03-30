package thevortexalpha.Appium.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import thevortexalpha.Appium.pageObjects.CartPage;
import thevortexalpha.Appium.pageObjects.ProductCatalogue;
import thevortexalpha.Appium.testUtils.BaseTest;

public class O01_ECommerce_E2E_TC01 extends BaseTest{
	
	@BeforeMethod (alwaysRun = true)
	public void preSetup() {
		formPage.setActivity();
		// bs://822455e5f58271f5c64bc5cd211faf42c782963d
	}
	
	@Test(dataProvider = "getData", groups = {"Smoke"})
	public void eCommerceE2EHybrid(HashMap<String, String> input) throws InterruptedException {
		
		formPage.setName(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountry(input.get("country"));
		ProductCatalogue productCatalogue = formPage.submitForm();

		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage =  productCatalogue.goToCartPage();
		
		double totalSum = cartPage.getProductSum();
		double totalSumInUI = cartPage.getTotalAmountInUI();
		Assert.assertEquals(totalSum, totalSumInUI);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData("\\src\\test\\java\\thevortexalpha\\appium\\testData\\eCommerce.json");
//		return new Object[][] {
//			{"Sidney Sweeney", "female",  "Australia"},
//			{"Miaa Khhaliffaa", "female",  "Australia"}
//		};
		return new Object[][] {
			{data.get(0)},
			{data.get(1)}
		};
	}
	
}
