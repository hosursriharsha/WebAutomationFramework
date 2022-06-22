package com.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class baseClass {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	@Before
	public void createDriverInstance() {
		System.out.println("****** Starting Driver******");
		String browser = getConfigValue("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver");
			driver.set(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Drivers/geckodriver");
			driver.set(new FirefoxDriver());
		}
		getDriver().navigate().to(getConfigValue("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Long.parseLong(getConfigValue("timeOutInSecs"))));

	}

	@BeforeClass
	public void setUpThreadCount(ITestContext context) {
		context.getCurrentXmlTest().getSuite().setDataProviderThreadCount(2);
		context.getCurrentXmlTest().getSuite().setPreserveOrder(false);
	}

	public static synchronized WebDriver getDriver() {
		return driver.get();
	}

	public String getConfigValue(String key) {
		Properties property = new Properties();
		try {
			property.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/java/config.properties"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return property.getProperty(key);
	}

	@After
	public void quitDriver(Scenario scenario) {
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll("", "_");
			byte[] sourcePath = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
		}
		getDriver().quit();
	}

}
