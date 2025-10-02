package com.qa.myframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.myframework.utils.ElementUtil;

public class LoginPage {
	 private WebDriver driver;
	 private ElementUtil eleUtil;
	
	private By userName=By.name("username");
	private By password=By.xpath("//input[@name='password']");
	private By loginButton=By.xpath("//button[text()=\"Log in\"]");
	private By forgotPassword=By.xpath("//*[text()='Forgot password?']");
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}
	
	
	public void loginToApp(String uName,String pass) throws InterruptedException {
		Thread.sleep(1000);
		eleUtil.doSendKeys(userName, uName,30);
		eleUtil.doSendKeys(password, pass,30);
	}
	
	public void clickOnForgotPasswordLink() {
		eleUtil.doClick(forgotPassword,30);
	}

}
