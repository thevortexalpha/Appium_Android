package thevortexalpha.AppiumFrameworkDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

public class O01_ECommerce_E2E_TC02 extends BaseTest{
	
	@BeforeMethod
	public void preSetup() {
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
				"intent", "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"
		));
	}

	@Test
	public void fillFormNegativeFlow() {
		formPage.setGender("female");
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		String errorToastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getText();
		Assert.assertEquals(errorToastMessage, "Please enter your name");
	}
	
	@Test
	public void fillFormPositiveFlow() {
		formPage.setName("Sidney Sweeney");
		formPage.setGender("female");
		formPage.setCountry("Australia");
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size() < 1);
	}

}
