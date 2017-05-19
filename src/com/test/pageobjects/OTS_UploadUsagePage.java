package com.test.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.core.RunnerHelper;
import com.test.support.Status;

public class OTS_UploadUsagePage extends PageSupporter {
	JavascriptExecutor js=(JavascriptExecutor) driver;
	public OTS_UploadUsagePage(RunnerHelper helper) {
		super(helper);
	}
	
	public boolean uploadUsageInOTS(){
		try{
			String baseURL = data.get("BaseURL");
			String usageDataPath = data.get("UsageDataPath");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			WebDriverWait wait = new WebDriverWait(driver, 60);
			
			driver.get(baseURL);
			log.log("Open upload usage page", Status.INFO);
			driver.findElement(By.linkText("Upload Usage")).click();
			
			WebElement usageSourceType = driver.findElement(By.id("SelectedUsageSourceType"));
			Select usageSourceTypeSelection = new Select(usageSourceType);
			usageSourceTypeSelection.selectByVisibleText("Manual");

			WebElement selectedUsageType = driver.findElement(By.id("SelectedUsageType"));
			Select usageTypeSelection = new Select(selectedUsageType);
			usageTypeSelection.selectByVisibleText("HU");

			WebElement selectedUtility = driver.findElement(By.id("SelectedUtility"));
			Select utilitySelection = new Select(selectedUtility);
			utilitySelection.selectByVisibleText("N/A");
			
			driver.findElement(By.id("files")).sendKeys(usageDataPath);
			driver.findElement(By.name("UploadFileButton")).click();
			
			WebElement message = driver.findElement(By.cssSelector(".message-success"));
			wait.until(ExpectedConditions.visibilityOf(message));
			Thread.sleep(3000);
			log.log("Usage upload in OTS", Status.PASS);
			System.out.println(message.getText());
			return true;
		}catch(Exception e){
			log.log("Usage upload in OTS Error: "+e.getMessage(), Status.FAIL);
			return false;
		}	
	}

}
