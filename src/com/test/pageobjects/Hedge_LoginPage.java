package com.test.pageobjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import com.test.core.RunnerHelper;
import com.test.support.Status;

public class Hedge_LoginPage extends PageSupporter{

	public Hedge_LoginPage(RunnerHelper helper) {
		super(helper);
	}
	
	public boolean logInHedge(){
		try{
			String userName = data.get("UserName");
			String passWord = data.get("Password");
			String baseURL = data.get("BaseURL");
			driver.get(baseURL);
			log.log("Launch Hedge application", Status.INFO);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.switchTo().frame("MainFrame");
			driver.findElement(By.id("m_txtLoginId_TextBox")).sendKeys(userName);
			driver.findElement(By.id("m_txtLoginPswd_TextBox")).sendKeys(passWord);
			driver.findElement(By.id("m_btnLogin_m_Button")).click();
			log.log("Hedge login", Status.PASS);
			return true;
		}catch(Exception e){
			log.log("Hedge login Error: "+e.getMessage(), Status.FAIL);
			return false;
		}	
	} 
}
