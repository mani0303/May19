package com.test.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.test.core.RunnerHelper;

public class DEHQ_LoginPage extends PageSupporter{
	WebDriverWait wait = new WebDriverWait(driver, 120);
	Actions action = new Actions(driver);
	JavascriptExecutor js=(JavascriptExecutor) driver;
	public DEHQ_LoginPage(RunnerHelper helper) {
		super(helper);
	}
	
	
	public boolean logInDEHQ(){
		try{
			String baseURL = data.get("BaseURL");
			String userNameIn = data.get("UserName");
			String passWordIn = data.get("Password");
			driver.get(baseURL);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			WebElement userName = driver.findElement(By.id("UserName"));
			userName.sendKeys(userNameIn);
			WebElement password = driver.findElement(By.id("Password"));
			password.sendKeys(passWordIn);
			WebElement logIn = driver.findElement(By.id("main-form_submit"));
			js.executeScript("arguments[0].click();", logIn);
			return true;
		}catch(Exception e){
			return false;
		}	
	}
	
}
