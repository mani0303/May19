package com.test.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.test.core.RunnerHelper;
import com.test.support.Status;

public class SFDC_LoginPage extends PageSupporter{

	public SFDC_LoginPage(RunnerHelper helper) {
		super(helper);
	}
	
	public boolean enterURLforTest(){
		try{
			driver.get(data.get("BaseURL"));
			log.log("Launch base url in the browser", Status.INFO);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			log.log("Launch SFDC", Status.PASS);
			return true;
		}catch(Exception e){
			log.log("Launch SFDC Error: "+e.getMessage(), Status.FAIL);
			return false;
		}	
	}
	
	public boolean enterLoginCredentials(){
		try{
			String userName = data.get("UserName");
			String passWord = data.get("Password");
			WebElement username = driver.findElement(By.id(identifier.getIdentifier("username")));
			username.sendKeys(userName);
			WebElement password = driver.findElement(By.id(identifier.getIdentifier("password")));
			password.sendKeys(passWord);
			driver.findElement(By.id(identifier.getIdentifier("Login"))).click();
			log.log("Enter SFDC login credentials", Status.INFO);
			return true;
		}catch(Exception e){
			log.log("Enter SFDC login credentials Error: "+e.getMessage(), Status.FAIL);
			return false;
		}	
	}
		
}
