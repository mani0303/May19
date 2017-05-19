package com.test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.test.core.RunnerHelper;
import com.test.support.Status;

public class Hedge_HomePage extends PageSupporter{

	public Hedge_HomePage(RunnerHelper helper) {
		super(helper);
	}
	
	public boolean getNewPricing(){
		String contractId = data.get("ContractId").trim();
		try{
			driver.switchTo().parentFrame();
			driver.switchTo().frame("MenuFrame");
			driver.findElement(By.partialLinkText("Manage Contracts")).click();
			driver.switchTo().parentFrame();
			driver.switchTo().frame("MainFrame");
			driver.findElement(By.id("m_IdNumber_TextBox")).sendKeys(contractId);
			driver.findElement(By.id("m_btnSearch_m_Button")).click();
			driver.switchTo().parentFrame();
			driver.switchTo().frame("MainFrame");
			driver.switchTo().frame("iframeData");
			driver.findElement(By.linkText(contractId)).click();
			driver.switchTo().parentFrame();
			driver.switchTo().frame("MainFrame");
			driver.findElement(By.xpath("//td[text()='Price']")).click();
			driver.switchTo().parentFrame();
			driver.switchTo().frame("MainFrame");
			driver.switchTo().frame("tabframe");
			driver.findElement(By.id("m_OverridePriceHeld_m_CheckBox")).click();
			driver.findElement(By.id("m_btnGetNewPrice_m_Button")).click();
			log.log("Get new price in Hedge", Status.PASS);
			return true;
		}catch(Exception e){
			log.log("Get new price in Hedge Error: "+e.getMessage(), Status.PASS);
			return false;
		}	
	}
	
	public boolean getPricingRequestStatu(){
		try{
			driver.findElement(By.linkText("here")).click();
			driver.switchTo().parentFrame();
			driver.switchTo().frame("MainFrame");
			int counter=0;
			WebElement SearchBtn = driver.findElement(By.id("m_btnSearch_m_Button"));
			SearchBtn.click();
			String firstRowText="";
			do{
				Thread.sleep(6000);
				firstRowText = driver.findElement(By.cssSelector("#dgPricingRequests .DataGridItem")).getText();
				SearchBtn = driver.findElement(By.id("m_btnSearch_m_Button"));
				SearchBtn.click();
				if(counter>10 || firstRowText.contains("Failed")){
					log.log("Get pricing request status in Hedge", Status.FAIL);
					break;
				}
				if(firstRowText.contains("Success")){
					log.log("Get pricing request status in Hedge", Status.PASS);
				}
				++counter;
			}while(!firstRowText.contains("Success"));
			driver.switchTo().parentFrame();
			driver.switchTo().frame("RightHeaderFrame");
			driver.findElement(By.id("m_aLogoutButton")).click();
			log.log("Pricing request status", Status.PASS);
			return true;
		}catch(Exception e){
			log.log("Pricing request status", Status.FAIL);
			return false;
		}	
	}
	
}
