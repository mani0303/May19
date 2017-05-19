package com.test.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.test.core.RunnerHelper;
import com.test.support.Status;

public class SFDC_ServiceLocationPage extends PageSupporter{

	public SFDC_ServiceLocationPage(RunnerHelper helper) {
		super(helper);
	}
	
	public boolean navigateToServiceLocations(){
		try{
			WebElement searchInput = driver.findElement(By.id(identifier.getIdentifier("SearchInput")));
			searchInput.sendKeys(SFDC_CompanyPage.accountName);
			Thread.sleep(5000);
			WebElement searchButton = driver.findElement(By.id(identifier.getIdentifier("SearchButton")));
			searchButton.click();
			
			WebElement accountLink = driver.findElement(By.xpath("//th/a[text()='"+SFDC_CompanyPage.accountName+"']"));
			accountLink.click();
			
			WebElement billingGroups = driver.findElement(By.xpath(identifier.getIdentifier("BillingGroups")));
			billingGroups.click();
			WebElement billingGroupsLink = driver.findElement(By.partialLinkText(SFDC_CompanyPage.accountName+" - Billing Group"));
			billingGroupsLink.click();
			
			WebElement newLocation = driver.findElement(By.name(identifier.getIdentifier("NewLocation")));
			newLocation.click();
			log.log("Navigate to new service location", Status.INFO);
			return true;
		}catch(Exception e){
			log.log("Navigate to new service location Error: "+e.getMessage(), Status.INFO);
			return false;
		}
	}
	
	public boolean addServiceLocations(){
		try{
			WebElement locationName = driver.findElement(By.id(identifier.getIdentifier("LocationName")));
			locationName.sendKeys(data.get("LocationName"));
			
			WebElement street0 = driver.findElement(By.id(identifier.getIdentifier("LocStreet")));
			street0.sendKeys(data.get("Street0"));
			
			WebElement city = driver.findElement(By.id(identifier.getIdentifier("LocCity")));
			city.sendKeys(data.get("City"));
			
			WebElement state = driver.findElement(By.id(identifier.getIdentifier("LocState")));
			state.sendKeys(data.get("State"));
			
			WebElement postalCode = driver.findElement(By.id(identifier.getIdentifier("LocZip")));
			postalCode.sendKeys(data.get("PostalCode"));
			
			WebElement iso = driver.findElement(By.id(identifier.getIdentifier("ISO")));
			Select isoSelection = new Select(iso);
			isoSelection.selectByVisibleText(data.get("ISO"));
			
			WebElement edc = driver.findElement(By.id(identifier.getIdentifier("EDC")));
			Select edcSelection = new Select(edc);
			edcSelection.selectByVisibleText(data.get("EDC"));
			
			WebElement pricingType = driver.findElement(By.id(identifier.getIdentifier("PricingType")));
			Select pricingTypeSelection = new Select(pricingType);
			pricingTypeSelection.selectByVisibleText(data.get("PricingType"));
			
			WebElement accountNo = driver.findElement(By.id(identifier.getIdentifier("AccountNo")));
			accountNo.sendKeys(data.get("AccountNo"));
			
			WebElement custClass = driver.findElement(By.id(identifier.getIdentifier("CustClass")));
			Select custClassSelection = new Select(custClass);
			custClassSelection.selectByVisibleText(data.get("CustClass"));
			
			WebElement meterType = driver.findElement(By.id(identifier.getIdentifier("MeterType")));
			Select meterTypeSelection = new Select(meterType);
			meterTypeSelection.selectByVisibleText(data.get("MeterType"));
			
			WebElement saveBtn = driver.findElement(By.name(identifier.getIdentifier("SaveBtn")));
			saveBtn.click();
			log.log("Add service location in SFDC", Status.INFO);
			return true;
		}catch(Exception e){
			log.log("Add service location in SFDC Error: "+e.getMessage(), Status.FAIL);
			return false;
		}
	}
}
