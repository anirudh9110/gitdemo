package appiumFrameworkDesign;

import java.io.File;
import com.google.common.collect.ImmutableMap;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException, URISyntaxException
	{
		//String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		service=new AppiumServiceBuilder()
				.withAppiumJS(new File("C://Users//kotaa//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("1c1891a0");
		options.setChromedriverExecutable("C:\\Users\\kotaa\\.appium\\node_modules\\appium-uiautomator2-driver\\node_modules\\appium-chromedriver\\chromedriver\\win\\chromedriver-win64_v130.0.6723.91.exe");
		//options.setApp("C://Users//kotaa//eclipse-workspace//appium//src//test//java//resources//ApiDemos-debug.apk");
		options.setApp("C:\\Users\\kotaa\\eclipse-workspace\\appium\\src\\test\\java\\resources\\General-Store.apk");
		driver =new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void longPressAction(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
						"duration",2000));
	}
	public Double getFormattedAmount(String amount)
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;
		
	}

	@AfterClass
	public void tearDown()
	{
		driver.quit();
		//service.stop();
	}


}
