package thevortexalpha.AppiumFrameworkDesign;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import thevortexalpha.AppiumFrameworkDesign.pageObjects.android.FormPage;

public class BaseTest {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;
	
	@BeforeClass
	public void configureAppium() throws MalformedURLException, URISyntaxException {
		service = new AppiumServiceBuilder().withAppiumJS(new File("C://Users//Vsvat//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Vortex Pixel 3a");
		options.setChromedriverExecutable("C:\\Users\\Vsvat\\Documents\\Docs\\VortexScripts\\eclipse-workspace\\Appium\\chromedriver\\chromedriver.exe");
//		options.setApp("C:\\Users\\Vsvat\\Documents\\Docs\\VortexScripts\\eclipse-workspace\\Appium\\apps\\ApiDemos-debug.apk");
		options.setApp("C:\\Users\\Vsvat\\Documents\\Docs\\VortexScripts\\eclipse-workspace\\Appium\\apps\\General-Store.apk");
		
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), options);	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
	

}
