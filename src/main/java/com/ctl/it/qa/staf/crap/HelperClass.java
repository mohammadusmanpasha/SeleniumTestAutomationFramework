package com.ctl.it.qa.staf.crap;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

//TODO: Remove this crap
public class HelperClass {

	public static WebElement findWebElement(final String xpath, WebDriver driver) throws Exception {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement element = null;

		int attempts = 0;

		while (attempts < 3){
			try {
				element = wait.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver webDriver) {
						return webDriver.findElement(By.xpath(xpath));
					}
				});

				break;
			} catch (StaleElementReferenceException e){}
			catch(Exception e1){
				throw e1;
			}

			attempts ++;
		}

		if(!element.isDisplayed())
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		return element;
	}
	
	public static List<WebElement> findWebElements(final String xpath, WebDriver driver) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);


		return wait.until(new Function<WebDriver, List<WebElement>>() {
			public List<WebElement> apply(WebDriver webDriver) {
				return webDriver.findElements(By.xpath(xpath));
			}
		});
	}
	
	public static String getCurrentMethodName() {

		int index = 2;
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		return ste[index].getMethodName();
	}

	public static  String getCurrentMethodName(int index) {

		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		return ste[index].getMethodName();
	}
	
	public static Boolean invisiblityOfElement(final String xpath, WebDriver driver) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(1000, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);

		return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
	}

	public InputStream getResource(String rsc) throws ClassNotFoundException {

		ClassLoader classLoader = getClass().getClassLoader();

		InputStream iStream = classLoader.getResourceAsStream(rsc);

		return iStream;
	}
	
	public File getResourceFile(String rsc) throws Exception {

		ClassLoader classLoader = getClass().getClassLoader();

		URI uri = classLoader.getResource(rsc).toURI();

		File f = new File(uri);

		return f;
	}

	

	public void setDriverProperty() throws Exception {

		String path = getResourceFile("Drivers\\ChromeDriver.exe").getAbsolutePath();
		System.setProperty("webdriver.chrome.driver", path);


		path =  getResourceFile("Drivers\\IEDriverServer.exe").getAbsolutePath();
		System.setProperty("webdriver.ie.driver", path);
	}
	
	public Properties setprop(String propertyfilePath) throws IOException, ClassNotFoundException{

		Properties prop = new Properties();

		prop.load(getResource(propertyfilePath));

		return prop;
	}

	public WebDriver setWebDriver(String browserType) throws Exception {
		WebDriver driver = null;
		try {
			if (browserType.trim().equalsIgnoreCase("IE")) {

				/*InternetExplorerDriverService service = new InternetExplorerDriverService.Builder().usingDriverExecutable(new File(getResourceFile("Drivers\\IEDriverServer_32.exe").getAbsolutePath())).usingPort(1200).build();
				service.start();

				// Thread.sleep(3000);
				driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.internetExplorer());*/

				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
				ieCapabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
				ieCapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				ieCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				ieCapabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, "accept");

				driver = new InternetExplorerDriver(ieCapabilities);
				

			} else if (browserType.trim().equalsIgnoreCase("Firefox")) {
				driver = new FirefoxDriver();
			} else if (browserType.trim().equalsIgnoreCase("Chrome")) {
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("chrome.switches", Arrays.asList("--disable-logging"));
				driver = new ChromeDriver(capabilities);
			}

			driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );

			driver.manage().window().maximize();

			/*WebElement html = driver.findElement(By.tagName("html"));

			html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));*/

			//((JavascriptExecutor)driver).executeScript("document.body.style.zoom='90%';");

			Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = caps.getBrowserName();
			String browserVersion = caps.getVersion();
			System.out.println("Automated test run. We�re running on "+ browserName + " " + browserVersion);
		} 
		catch (Exception e) {
			System.out.println(e);
			throw e;
		}

		return driver;
	}
}
