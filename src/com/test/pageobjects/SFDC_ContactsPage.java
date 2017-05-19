package com.test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.test.core.RunnerHelper;
import com.test.support.Status;

public class SFDC_ContactsPage extends PageSupporter{

	public SFDC_ContactsPage(RunnerHelper helper) {
		super(helper);
	}

	public boolean navigateToContacts(){
		try{
			WebElement searchInput = driver.findElement(By.id(identifier.getIdentifier("SearchInput")));
			searchInput.sendKeys(SFDC_CompanyPage.accountName);
			Thread.sleep(5000);
			WebElement searchButton = driver.findElement(By.id(identifier.getIdentifier("SearchButton")));
			searchButton.click();
			
			WebElement accountLink = driver.findElement(By.xpath("//th/a[text()='"+SFDC_CompanyPage.accountName+"']"));
			accountLink.click();
			
			WebElement contacts = driver.findElement(By.xpath(identifier.getIdentifier("Contacts")));
			contacts.click();
			
			WebElement newContact = driver.findElement(By.name(identifier.getIdentifier("NewContact")));
			newContact.click();
			log.log("Navigate to contacts in SFDC", Status.INFO);	
			return true;
		}catch(Exception e){
			log.log("Navigate to contacts in SFDC Error: "+e.getMessage(), Status.FAIL);
			return false;
		}
	}

	public boolean addContacts(){
		try{
			WebElement lastName = driver.findElement(By.id(identifier.getIdentifier("LastName")));
			lastName.sendKeys(data.get("LastName"));
			WebElement phoneNo = driver.findElement(By.id(identifier.getIdentifier("PhoneNo")));
			phoneNo.sendKeys(data.get("PhoneNo"));
			WebElement email = driver.findElement(By.id(identifier.getIdentifier("Email")));
			email.sendKeys(data.get("Email"));
			WebElement saveBtn = driver.findElement(By.name(identifier.getIdentifier("SaveBtn")));
			saveBtn.click();
			log.log("Create contacts in SFDC", Status.INFO);
			return false;
		}catch(Exception e){
			log.log("Create contacts in SFDC Error: "+e.getMessage(), Status.FAIL);
			return false;
		}

	}
}
