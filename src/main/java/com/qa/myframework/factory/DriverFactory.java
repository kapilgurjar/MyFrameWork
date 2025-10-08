package com.qa.myframework.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.myframework.exceptions.FrameWorkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManger op;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver intDriver(Properties prop) {
		op = new OptionsManger(prop);
		String browserName = prop.getProperty("browser");
		System.out.println("browser name is " + browserName);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				inItRemoteDriver("chrome");
			} else {
				tlDriver.set(new ChromeDriver(op.getChromeOptions()));
			}

			break;

		case "firefox":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				inItRemoteDriver("firefox");
			} else {
				tlDriver.set(new FirefoxDriver(op.getFireFoxOption()));
			}

			break;

		case "edge":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				inItRemoteDriver("edge");
			} else {
				tlDriver.set(new EdgeDriver());
			}
			break;

		default:
			System.out.println("please pass the correct browser name " + browserName);

			throw new FrameWorkException("Invalid browser name");
		}
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	private void inItRemoteDriver(String browserName) {
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), op.getChromeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			break;

		case "firefox":
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), op.getFireFoxOption()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			break;

		default:
			throw new FrameWorkException("Invalid browser");
		}

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	// mvn clean test -DenvName=qa
	public Properties initProp() {
		prop = new Properties();
		FileInputStream fis = null;
		String envName = System.getProperty("env");
		try {
			if (envName == null) {
				System.out.println("No envirment is passed running test cases on QA env");

				fis = new FileInputStream("./src/test/resources/config/config.qa.properties");

			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					fis = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;

				case "stage":
					fis = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;

				case "dev":
					fis = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;

				case "prod":

					fis = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					throw new FrameWorkException("Unexpected value: " + envName.toLowerCase().trim());
				}
			}

		} catch (FileNotFoundException e) {

		}

		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

}
