package com.test.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.core.RunnerHelper;
import com.test.support.Status;

public class SFDC_OpportunityPage extends PageSupporter{
	WebDriverWait wait = new WebDriverWait(driver, 60);
	public SFDC_OpportunityPage(RunnerHelper helper) {
		super(helper);
	}

	public boolean navigateToNewOpportunities(){
		try{
			WebElement searchInput = driver.findElement(By.id(identifier.getIdentifier("SearchInput")));
			searchInput.sendKeys(SFDC_CompanyPage.accountName);
			Thread.sleep(5000);
			WebElement searchButton = driver.findElement(By.id(identifier.getIdentifier("SearchButton")));
			searchButton.click();

			WebElement accountLink = driver.findElement(By.xpath("//th/a[text()='"+SFDC_CompanyPage.accountName+"']"));
			accountLink.click();

			WebElement opportunities = driver.findElement(By.xpath(identifier.getIdentifier("Opportunities")));
			opportunities.click();

			WebElement newOpportunity = driver.findElement(By.name(identifier.getIdentifier("NewOpportunity")));
			newOpportunity.click();
			WebElement newRecordType = driver.findElement(By.id(identifier.getIdentifier("NewRecordType")));
			Select newRecordTypeSelection = new Select(newRecordType);
			newRecordTypeSelection.selectByVisibleText("Electricity - Discrete");
			WebElement continue1 = driver.findElement(By.cssSelector(identifier.getIdentifier("Continue")));
			continue1.click();
			List<WebElement> continue2 = driver.findElements(By.cssSelector(identifier.getIdentifier("Continue")));
			if(continue2.size()>0){
				continue2.get(0).click();
			}
			log.log("Navigate to new opportunities info in SFDC", Status.INFO);
			return true;
		}catch(Exception e){
			log.log("Navigate to new opportunities info in SFDC Error:"+e.getMessage(), Status.FAIL);
			return false;
		}
	}

	public boolean navigateToExistingOpportunities(){
		try{
			WebElement searchInput = driver.findElement(By.id(identifier.getIdentifier("SearchInput")));
			searchInput.sendKeys(SFDC_CompanyPage.accountName);
			Thread.sleep(5000);
			WebElement searchButton = driver.findElement(By.id(identifier.getIdentifier("SearchButton")));
			searchButton.click();

			WebElement accountLink = driver.findElement(By.xpath("//th/a[text()='"+SFDC_CompanyPage.accountName+"']"));
			accountLink.click();

			WebElement opportunities = driver.findElement(By.xpath(identifier.getIdentifier("Opportunities")));
			opportunities.click();

			WebElement existingOpportunity = driver.findElement(By.xpath("//div[contains(@id,'RelatedOpportunityList_body')]//a[contains(text(),'"+SFDC_CompanyPage.accountName+"')]"));
			existingOpportunity.click();
			
			log.log("Navigate to existing opportunities info in SFDC", Status.INFO);
			return true;
		}catch(Exception e){
			log.log("Navigate to existing opportunities info in SFDC Error: "+e.getMessage(), Status.FAIL);
			return false;
		}
	}

	public boolean addOpportunity(){
		try{
			WebElement stage = driver.findElement(By.id(identifier.getIdentifier("Stage")));
			Select stageSelection = new Select(stage);
			stageSelection.selectByVisibleText("Open");

			WebElement type = driver.findElement(By.id(identifier.getIdentifier("Type")));
			Select typeSelection = new Select(type);
			typeSelection.selectByVisibleText("New");

			WebElement opportunityContact = driver.findElement(By.id(identifier.getIdentifier("OpportunityContact")));
			opportunityContact.sendKeys(data.get("LastName"));

			WebElement startDate = driver.findElement(By.id(identifier.getIdentifier("StartDate")));
			startDate.sendKeys("6/1/2017");

			WebElement endDate = driver.findElement(By.id(identifier.getIdentifier("EndDate")));
			endDate.sendKeys("12/31/2022");

			WebElement closeDate = driver.findElement(By.id(identifier.getIdentifier("CloseDate")));
			closeDate.sendKeys("12/31/2022");

			WebElement executionMethod = driver.findElement(By.id(identifier.getIdentifier("ExecutionMethod")));
			Select executionMethodSelection = new Select(executionMethod);
			executionMethodSelection.selectByVisibleText("Other");

			WebElement acquistionSource = driver.findElement(By.id(identifier.getIdentifier("AcquistionSource")));
			Select acquistionSourceSelection = new Select(acquistionSource);
			acquistionSourceSelection.selectByVisibleText("Email / eNewsletter");

			WebElement channelPartner = driver.findElement(By.id(identifier.getIdentifier("ChannelPartner")));
			Select channelPartnerSelection = new Select(channelPartner);
			channelPartnerSelection.selectByVisibleText("No");

			WebElement province = driver.findElement(By.id(identifier.getIdentifier("Province")));
			Select provinceSelection = new Select(province);
			provinceSelection.selectByVisibleText(data.get("State"));

			WebElement edcSelect = driver.findElement(By.id(identifier.getIdentifier("EDCSelect")));
			Select edcSelection = new Select(edcSelect);
			edcSelection.selectByVisibleText(data.get("EDCName"));

			WebElement rightArrow = driver.findElement(By.id(identifier.getIdentifier("RightArrow")));
			rightArrow.click();

			WebElement margin_Unit = driver.findElement(By.id(identifier.getIdentifier("Margin_Unit")));
			margin_Unit.sendKeys("0.050000");

			WebElement annualDealVolume = driver.findElement(By.id(identifier.getIdentifier("AnnualDealVolume")));
			annualDealVolume.sendKeys("1000");

			WebElement existingVolumeElec = driver.findElement(By.id(identifier.getIdentifier("ExistingVolumeElec")));
			existingVolumeElec.sendKeys("0");

			WebElement existingVolumeGas = driver.findElement(By.id(identifier.getIdentifier("ExistingVolumeGas")));
			existingVolumeGas.sendKeys("0");			
			
			//if(driver.findElements(By.id(identifier.getIdentifier("ExistingProjectCode"))).size()>0){
				WebElement existingProjectCode = driver.findElement(By.id(identifier.getIdentifier("ExistingProjectCode")));
				existingProjectCode.sendKeys("0");	
			//}
	
			
			WebElement requestedTerm = driver.findElement(By.id(identifier.getIdentifier("RequestedTerm")));
			Select requestedTermSelection = new Select(requestedTerm);
			requestedTermSelection.selectByVisibleText("60");

			WebElement saveBtn = driver.findElement(By.name(identifier.getIdentifier("SaveBtn")));
			saveBtn.click();

			WebElement desc = driver.findElement(By.cssSelector(identifier.getIdentifier("PageDescription")));
			System.out.println(desc.getText());
			log.log("Create new opportunity in SFDC", Status.INFO);
			return true;
		}catch(Exception e){
			log.log("Create new opportunity in SFDC Error: "+e.getMessage(), Status.FAIL);
			return false;
		}
	}

	public boolean updateExistingOpportunity(){
		
		String state = data.get("State");
		try{
			//Actions action = new Actions(driver);
			WebElement editButton = driver.findElement(By.cssSelector(identifier.getIdentifier("EditButton")));
			editButton.click();
			
	/*		WebElement invoiceReview = driver.findElement(By.id(identifier.getIdentifier("InvoiceReview")));
			invoiceReview.click();*/
	//		if(!state.trim().equals("NJ")){
				WebElement invoiceReviewChkbox = driver.findElement(By.id(identifier.getIdentifier("InvoiceReviewChkbox")));
				invoiceReviewChkbox.click();	
	//		}
			
/*			WebElement noOfInvoices = driver.findElement(By.cssSelector(identifier.getIdentifier("NoOfInvoices")));
			noOfInvoices.click();*/
			WebElement noOfInvoicesInput = driver.findElement(By.cssSelector(identifier.getIdentifier("NoOfInvoicesInput")));
			noOfInvoicesInput.sendKeys("2");
			
		/*	WebElement salesNotes = driver.findElement(By.cssSelector(identifier.getIdentifier("SalesNotes")));
			salesNotes.click();*/
			
			WebElement notes = driver.findElement(By.cssSelector(identifier.getIdentifier("Notes")));
			notes.sendKeys("Test Automation");
			
			/*WebElement OKbutton = driver.findElement(By.cssSelector(identifier.getIdentifier("OKbutton")));
			OKbutton.click();*/
			
			WebElement saveEdit = driver.findElement(By.cssSelector(identifier.getIdentifier("SaveEdit")));
			saveEdit.click();
			
			WebElement manageServiceLocations = driver.findElement(By.name(identifier.getIdentifier("ManageServiceLocations")));
			manageServiceLocations.click();
			
			WebElement locationSelection = driver.findElement(By.cssSelector(identifier.getIdentifier("LocationSelection")));
			locationSelection.click();
			
			WebElement addButton = driver.findElement(By.cssSelector(identifier.getIdentifier("AddButton")));
			addButton.click();
			Thread.sleep(2000);
			WebElement saveButton = driver.findElement(By.cssSelector(identifier.getIdentifier("SaveButton")));
			saveButton.click();
			Thread.sleep(10000);
			log.log("Update opportunity in SFDC", Status.INFO);
			return true;
		}catch(Exception e){
			log.log("Update opportunity in SFDC Error: "+e.getMessage(), Status.FAIL);
			return false;
		}
	}
	public boolean createPricingRequests(){
		try{
			Actions action = new Actions(driver);
			JavascriptExecutor js=(JavascriptExecutor) driver;
			WebElement pricingRequests = driver.findElement(By.partialLinkText(identifier.getIdentifier("PricingRequests")));
			pricingRequests.click();
			WebElement newPricingReq = driver.findElement(By.name(identifier.getIdentifier("NewPricingReq")));
			newPricingReq.click();
			WebElement purpose = driver.findElement(By.cssSelector(identifier.getIdentifier("Purpose")));
			Select purposeSelection = new Select(purpose);
			purposeSelection.selectByVisibleText("Indicative");
			WebElement dueDate = driver.findElement(By.cssSelector(identifier.getIdentifier("DueDate")));
			dueDate.click();
			WebElement prType = driver.findElement(By.cssSelector(identifier.getIdentifier("PRType")));
			Select prTypeSelection = new Select(prType);
			prTypeSelection.selectByVisibleText("New");
			WebElement prBillingMethod = driver.findElement(By.cssSelector(identifier.getIdentifier("PRBillingMethod")));
			Select prBillingMethodSelection = new Select(prBillingMethod);
			prBillingMethodSelection.selectByVisibleText("Dual");
			WebElement PIFNotes = driver.findElement(By.cssSelector(identifier.getIdentifier("PIFNotes")));
			PIFNotes.sendKeys("Test Automation");
			WebElement PRSave = driver.findElement(By.cssSelector(identifier.getIdentifier("PRSave")));
			PRSave.click();
			WebElement PRFinalSave = driver.findElement(By.cssSelector(identifier.getIdentifier("PRFinalSave")));
			PRFinalSave.click();
			WebElement addProductRow = driver.findElement(By.cssSelector(identifier.getIdentifier("AddProductRow")));
			js.executeScript("window.scrollBy(0,600)", "");
			Thread.sleep(5000);
			action.moveToElement(addProductRow).click().build().perform();
			Thread.sleep(10000);
			WebElement productName = driver.findElement(By.cssSelector(identifier.getIdentifier("ProductName")));
			productName.sendKeys("PJM_FP_AI_NJ");
			Thread.sleep(5000);
			WebElement prTerm = driver.findElement(By.cssSelector(identifier.getIdentifier("PRTerm")));
			Select prTermSelection = new Select(prTerm);
			prTermSelection.selectByVisibleText("12");
			Thread.sleep(5000);
			WebElement saveProduct = driver.findElement(By.cssSelector(identifier.getIdentifier("SaveProduct")));
			//js.executeScript("arguments[0].click();", saveProduct);
			//saveProduct.click();			
			action.moveToElement(saveProduct).click().build().perform();
			Thread.sleep(15000);
			WebElement submitToHedge = driver.findElement(By.cssSelector(identifier.getIdentifier("SubmitToHedge")));
			js.executeScript("arguments[0].click();", submitToHedge);
			//submitToHedge.click();
			Thread.sleep(15000);
			WebElement prName = driver.findElement(By.cssSelector(identifier.getIdentifier("PRName")));
			wait.until(ExpectedConditions.visibilityOf(prName));
			System.out.println(prName.getText());
			log.log("Create new pricing request in SFDC", Status.INFO);
			return true;
		}catch(Exception e){
			log.log("Create new pricing request in SFDC Error: "+e.getMessage(), Status.FAIL);
			return false;
		}
	}
}
