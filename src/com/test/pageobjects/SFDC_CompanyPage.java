package com.test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.test.core.RunnerHelper;
import com.test.support.Status;

public class SFDC_CompanyPage extends PageSupporter{
	public SFDC_CompanyPage(RunnerHelper helper) {
		super(helper);
	}
	public static String accountName;
	public boolean createCustomer(){
		try{
			
			WebElement Companies = driver.findElement(By.linkText(identifier.getIdentifier("Companies")));
			Companies.click();
			WebElement NewCompany = driver.findElement(By.cssSelector(identifier.getIdentifier("NewCompany")));
			NewCompany.click();
			WebElement RecordType = driver.findElement(By.id(identifier.getIdentifier("RecordType")));
			Select recordType = new Select(RecordType);
			recordType.selectByVisibleText(data.get("RecordType"));
			WebElement Continue = driver.findElement(By.cssSelector(identifier.getIdentifier("Continue")));
			Continue.click();
			WebElement Next = driver.findElement(By.cssSelector(identifier.getIdentifier("Next")));
			Next.click();
			WebElement AccountName = driver.findElement(By.cssSelector(identifier.getIdentifier("AccountName")));
			accountName = data.get("AccountName")+"_"+System.currentTimeMillis();
			System.out.println(accountName);
			AccountName.sendKeys(accountName);			
			WebElement PostalCode = driver.findElement(By.cssSelector(identifier.getIdentifier("PostalCode")));
			PostalCode.sendKeys(data.get("PostalCode"));
			WebElement Street0 = driver.findElement(By.cssSelector(identifier.getIdentifier("Street0")));
			Street0.sendKeys(data.get("Street0"));
			WebElement City = driver.findElement(By.cssSelector(identifier.getIdentifier("City")));
			City.sendKeys(data.get("City"));
			WebElement state = driver.findElement(By.cssSelector(identifier.getIdentifier("State")));
			Select State = new Select(state);
			State.selectByVisibleText(data.get("State"));
			WebElement Search = driver.findElement(By.cssSelector(identifier.getIdentifier("btnSearch")));
			Search.click();			
			WebElement chkAddress = driver.findElement(By.xpath(identifier.getIdentifier("chkAddress")));
			chkAddress.click();
			Thread.sleep(5000);
			WebElement CreateNew = driver.findElement(By.cssSelector(identifier.getIdentifier("CreateNew")));
			CreateNew.click();
			
			WebElement accountSource = driver.findElement(By.id(identifier.getIdentifier("AccountSource")));
			Select accountSourceSelection = new Select(accountSource);
			accountSourceSelection.selectByVisibleText(data.get("AccountSource"));
			
			WebElement industry = driver.findElement(By.id(identifier.getIdentifier("Industry")));
			Select industrySelection = new Select(industry);
			industrySelection.selectByVisibleText(data.get("Industry"));
			
			WebElement saveBtn = driver.findElement(By.name(identifier.getIdentifier("SaveBtn")));
			saveBtn.click();
			log.log("Create company in SFDC", Status.PASS);
			return true;
		}catch(Exception e){
			log.log("Create company in SFDC "+e.getMessage(), Status.FAIL);
			return false;
		}	
	}
	public boolean navigateToCompany(){
		try{
			WebElement searchInput = driver.findElement(By.id(identifier.getIdentifier("SearchInput")));
			searchInput.sendKeys(SFDC_CompanyPage.accountName);
			Thread.sleep(5000);
			WebElement searchButton = driver.findElement(By.id(identifier.getIdentifier("SearchButton")));
			searchButton.click();
			
			WebElement accountLink = driver.findElement(By.xpath("//th/a[text()='"+SFDC_CompanyPage.accountName+"']"));
			accountLink.click();
			log.log("Navigate to company info", Status.INFO);	
			return true;
		}catch(Exception e){
			log.log("Navigate to company info Error: "+e.getMessage(), Status.FAIL);	
			return false;
		}
	}
	
	public boolean changeAccountOwner(){
		try{
			navigateToCompany();
			WebElement changeOwner = driver.findElement(By.linkText(identifier.getIdentifier("ChangeOwner")));
			changeOwner.click();
			WebElement newOwner = driver.findElement(By.id(identifier.getIdentifier("NewOwner")));
			newOwner.sendKeys("Jennifer Lloyd");
			
			WebElement saveBtn = driver.findElement(By.name(identifier.getIdentifier("SaveBtn")));
			saveBtn.click();
				
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}