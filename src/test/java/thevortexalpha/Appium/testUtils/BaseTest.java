package thevortexalpha.Appium.testUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import thevortexalpha.Appium.pageObjects.FormPage;
import thevortexalpha.Appium.utils.AppiumUtils;

public class BaseTest extends AppiumUtils{

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;
	
	@BeforeClass(alwaysRun = true)
	public void configureAppium() throws URISyntaxException, IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\thevortexalpha\\resources\\data.properties");
		prop.load(fis);
		
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		
		service = startAppiumServer(ipAddress, Integer.parseInt(prop.getProperty("port")));
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("androidDeviceName"));
		options.setChromedriverExecutable(System.getProperty("user.dir")+"\\src\\test\\java\\thevortexalpha\\resources\\chromedriver.exe");
//		options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\thevortexalpha\\resources\\ApiDemos-debug.apk");
		options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\thevortexalpha\\resources\\General-Store.apk");
		
		driver = new AndroidDriver(service.getUrl(), options);	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}
	

}
