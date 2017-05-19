package com.test.functional;

import com.test.core.RunnerHelper;
import com.test.core.TestRunner;

public class First extends TestRunner{	

	public First(RunnerHelper helper) {
		super(helper);
	}
	
	@Test(description= "Create opportunity and pricing request through SFDC")
	public void createOpportunityAndPEInSDFC(){
		slp.enterURLforTest();
		slp.enterLoginCredentials();
		scp.createCustomer();
		scnp.navigateToContacts();
		scnp.addContacts();
		sslp.navigateToServiceLocations();
		sslp.addServiceLocations();
		sop.navigateToNewOpportunities();
		sop.addOpportunity();
		scp.changeAccountOwner();
		sop.navigateToExistingOpportunities();
		sop.updateExistingOpportunity();
		sop.createPricingRequests();
	}
	
	@Test(description= "Upload and publish usage in OTS")
	public void uploadAndPublishUsage(){
		uup.uploadUsageInOTS();
		ausp.validateAccountUsageStatus();
	}
	
	@Test(description= "Accept contract through Booking SUI")
	public void acceptContractInSUI(){
		shp.acceptContractInBookingSUI();
	}
	
	@Test(description= "Get pricing for the contract in Hedge")
	public void getPricingInHedge(){
		hlp.logInHedge();
		hhp.getNewPricing();
		hhp.getPricingRequestStatu();
	}
	
	
	@Test(description= "Create account and pricing request in DEHQ")
	public void createAccountAndPricingRequest(){
		dlp.logInDEHQ();
		dhp.createNewCustomer();
		dhp.runCreditStatus();
		dhp.updateContactInfo();
		dhp.addServiceLocation();
		//dhp.uploadDocuments();
		dhp.createNewPricingRequest();
	}
}
