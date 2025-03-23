package thevortexalpha.AppiumFrameworkDesign;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import thevortexalpha.AppiumFrameworkDesign.pageObjects.android.CartPage;
import thevortexalpha.AppiumFrameworkDesign.pageObjects.android.FormPage;
import thevortexalpha.AppiumFrameworkDesign.pageObjects.android.ProductCatalogue;

public class O13_ECommerce_E2E_TC04 extends BaseTest{

	@Test
	public void eCommerceE2EHybrid() throws InterruptedException {
		
		formPage.setName("Sidney Sweeney");
		formPage.setGender("female");
		formPage.setCountry("Australia");
		ProductCatalogue productCatalogue = formPage.submitForm();

		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage =  productCatalogue.goToCartPage();
		
		double totalSum = cartPage.getProductSum();
		double totalSumInUI = cartPage.getTotalAmountInUI();
		Assert.assertEquals(totalSum, totalSumInUI);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();
		
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));		
//		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

//		Set<String> contexts = driver.getContextHandles();
//		for(String context : contexts) System.out.println(context);
//		driver.context("WEBVIEW_com.androidsample.generalstore");
//		
//		driver.findElement(By.name("q")).sendKeys("Vibin Abishek Vijayakumar");
//		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//		Thread.sleep(5000);
//		
//		driver.pressKey(new KeyEvent(AndroidKey.BACK));
//		driver.context("NATIVE_APP");
	}
	
}
