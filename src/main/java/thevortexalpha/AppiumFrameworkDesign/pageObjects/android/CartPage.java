package thevortexalpha.AppiumFrameworkDesign.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import thevortexalpha.AppiumFrameworkDesign.utils.AndroidActions;

public class CartPage extends AndroidActions{
	
	AndroidDriver driver;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrices;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement termsConditionButton;
	
	@AndroidFindBy(id = "android:id/button1")
	private WebElement acceptButton;
	
	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkBox;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedButton;
	
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public double getProductSum() {
		int productPricesCount = productPrices.size();
		double sumOfProducts = 0;
		
		for(int i=0; i<productPricesCount; i++) {
			String amountString = productPrices.get(i).getText();
			Double price = getFormattedAmount(amountString);
			sumOfProducts = price + sumOfProducts;
		}
		return sumOfProducts;
	}
	
	public Double getTotalAmountInUI() {
		return getFormattedAmount(totalAmount.getText());
	}
	
	public Double getFormattedAmount(String stringValue) {
		Double price = Double.parseDouble(stringValue.substring(1));
		return price;
	}
	
	public void acceptTermsConditions() {
		longPressAction(termsConditionButton);
		acceptButton.click();
	}
	
	public void submitOrder() throws InterruptedException {
		checkBox.click();
		proceedButton.click();
		Thread.sleep(5000);
	}

}
