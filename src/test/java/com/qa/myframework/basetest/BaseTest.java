package com.qa.myframework.basetest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.qa.myframework.factory.DriverFactory;
import com.qa.myframework.pages.LoginPage;

public class BaseTest {
	DriverFactory df;
	protected WebDriver driver;
	protected Properties prop;
	protected LoginPage lPage;

	@BeforeMethod
	@Parameters({"browser", "browserversion"})
	public void setUp(String browserName,String browserVersion) {
		df = new DriverFactory();
		prop = df.initProp();
		if (browserName!= null) {
			prop.setProperty("browser",browserName);
			prop.setProperty("browserversion",browserVersion);
		}
		driver = df.intDriver(prop);
		lPage = new LoginPage(driver);

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
