package com.qa.myframework.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManger {

	Properties prop;
	ChromeOptions co;
	FirefoxOptions fo;

	public OptionsManger(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			co.addArguments("--incognito");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))){
			co.setCapability("browserName", "chrome");
		}
		
		return co;
	}
	
	public FirefoxOptions getFireFoxOption() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fo.addArguments("-private");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote"))){
			fo.setCapability("browserName", "firefox");
		}
		
		return fo;
	}
	
	

}
