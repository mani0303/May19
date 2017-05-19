package com.test.core;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import com.test.core.RunnerHelper;
import com.test.pageobjects.DEHQ_HomePage;
import com.test.pageobjects.DEHQ_LoginPage;
import com.test.pageobjects.Hedge_HomePage;
import com.test.pageobjects.Hedge_LoginPage;
import com.test.pageobjects.OTS_AccountUsageStatusPage;
import com.test.pageobjects.OTS_UploadUsagePage;
import com.test.pageobjects.SFDC_CompanyPage;
import com.test.pageobjects.SFDC_ContactsPage;
import com.test.pageobjects.SFDC_LoginPage;
import com.test.pageobjects.SFDC_OpportunityPage;
import com.test.pageobjects.SFDC_ServiceLocationPage;
import com.test.pageobjects.SUI_HomePage;
import com.test.report.Logger;
import com.test.support.Identifier;
import com.test.support.Utility;
/**
 * File name   :TestRunner.java
 * Description  : 
 * Date created :13 Sep 2016
 * Author 		:Veera
 */
public class TestRunner{
	protected Logger log;
	protected WebDriver driver;
	protected HashMap<String, String> data;
	Identifier identifier = Identifier.getInstance();
	protected Utility runtimeData;
	protected SFDC_CompanyPage scp;
	protected SFDC_LoginPage slp;
	protected SFDC_ContactsPage scnp;
	protected SFDC_ServiceLocationPage sslp;
	protected SFDC_OpportunityPage sop;
	protected OTS_UploadUsagePage uup;
	protected OTS_AccountUsageStatusPage ausp;
	protected SUI_HomePage shp;
	protected Hedge_LoginPage hlp;
	protected Hedge_HomePage hhp;
	protected DEHQ_LoginPage dlp;
	protected DEHQ_HomePage dhp;
	@Retention(RetentionPolicy.RUNTIME)
	public
	@interface Test{
		String description();
	}
	public TestRunner(RunnerHelper helper){
		log = helper.log;
		driver=helper.driver;
		data=helper.testData;
		this.runtimeData=Utility.getInstance();
		this.scp = new SFDC_CompanyPage(helper);
		this.slp = new SFDC_LoginPage(helper);
		this.scnp = new SFDC_ContactsPage(helper);
		this.sslp = new SFDC_ServiceLocationPage(helper);
		this.sop = new SFDC_OpportunityPage(helper);
		this.uup = new OTS_UploadUsagePage(helper);
		this.ausp = new OTS_AccountUsageStatusPage(helper);
		this.shp = new SUI_HomePage(helper);
		this.hlp = new Hedge_LoginPage(helper);
		this.hhp = new Hedge_HomePage(helper);
		this.dlp = new DEHQ_LoginPage(helper);
		this.dhp = new DEHQ_HomePage(helper);
	}		
}
