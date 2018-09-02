package support.libraries;

import java.io.IOException;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.thucydides.core.webdriver.DriverSource;

public class IExplorerDriver implements DriverSource {

	@Override
	public WebDriver newDriver() {
		if (GlobalProperties.envProps.getProperty("browser").equalsIgnoreCase("chrome")
				&& GlobalProperties.envProps.getProperty("webdriver.platform").equalsIgnoreCase("Windows")) {

			System.out.println("Chrome");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			options.addArguments("--start-maximized");
			if (GlobalProperties.envProps.getProperty("webdriver.chrome.driver").contains("2.35")) {
				options.setExperimentalOption("useAutomationExtension", false);

			}

			// CucumberHooks.onceFlag=true;

			DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
			desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
			try {
				return new ChromeDriver(desiredCapabilities);
			} catch (Exception e) {
				System.out
						.println("Failed to intialize Chrome browser for the first time, Initializing the second time");
				return new ChromeDriver(desiredCapabilities);
			}
		}
		
/*		elseif(GlobalProperties.envProps.getProperty("webdriver.platform").equalsIgnoreCase("Andriod")
		&& GlobalProperties.envProps.getProperty("webdriver.platform").equalsIgnoreCase("IOS")){
			
			GridClient gc = new GridClient(GlobalProperties.envProps.getProperty("device.accesskey"), GlobalProperties.envProps.getProperty("seetest.hub.url") );
			Client client = gc.lockDeviceForExecution("SampleTest","@serialnumber='" + GlobalProperties.envProps.getProperty("device.UDID") + "'",false,300,100);
			return new MobileWebDriver(client);
		}  */

		else {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
			capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			// CucumberHooks.onceFlag=true;
			return new InternetExplorerDriver(capabilities);
		}
	}



	@Override
	public boolean takesScreenshots() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void killprocess (String strProcessName){
		try{
			Runtime.getRuntime().exec("taskkill /F /IM" + strProcessName);
			System.out.println("Killed all existing " +strProcessName );
			
		}
		catch(IOException e)
		{
			
			System.out.println("Issue Killing" +strProcessName );
		}
		
	}
}
