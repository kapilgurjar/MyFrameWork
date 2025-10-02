package com.qa.myframework.testscripts;

import org.testng.annotations.Test;

import com.qa.myframework.basetest.BaseTest;

public class GoogleTC extends BaseTest {

	@Test
	public void logginToApp() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("userName is ===="+prop.getProperty("uname") + "paasword is ===" +prop.getProperty("pass"));
		lPage.loginToApp(prop.getProperty("uname"), prop.getProperty("pass"));

	}

	@Test
	public void verifyForgotPass() throws InterruptedException {
		Thread.sleep(2000);
		lPage.clickOnForgotPasswordLink();
	}

}
