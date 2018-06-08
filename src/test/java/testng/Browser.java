package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browser {
	static String driverPath = "C:/Lily/TestPro/workspace/MyMavenWebTest/lib/";
		
		public static WebDriver driver;
		
		public static WebDriver getBrowser(String browserType) {
			switch (browserType) {
			case "firefox":
				System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
				return	driver = new FirefoxDriver();
			case "chrome":
				System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
				return	driver = new ChromeDriver();
			case "IE":
				System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer.exe");
				//为 Internet Explorer 设置安全性功能,否则会遇到一个安全问题提示："Protected Mode must be set to the same value (enabled or disabled) for all zones"
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);  
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				//WebDriver my_dr = new InternetExplorerDriver(caps);// 打开ie浏览器
				return	driver = new InternetExplorerDriver(caps);
				//return	driver = new InternetExplorerDriver();
			default:
				System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
				return driver = new FirefoxDriver();
			}
		}
}
