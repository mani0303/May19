package com.test.pageobjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.test.core.RunnerHelper;
import com.test.support.Status;

public class SUI_HomePage extends PageSupporter{

	public SUI_HomePage(RunnerHelper helper) {
		super(helper);
	}

	public boolean acceptContractInBookingSUI(){
		try{
			String accountName1 = data.get("AccountName");
			WebDriverWait wait = new WebDriverWait(driver, 60);
			String sourceIn = data.get("Source");
			String productTypeIn = data.get("PricingType");
			driver.get(data.get("BaseURL"));
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			log.log("Launch Booking SUI", Status.INFO);
			WebElement source = driver.findElement(By.cssSelector("select[id*=ddlSourceSystem]"));
			Select sourceSelection = new Select(source);
			sourceSelection.selectByVisibleText(sourceIn);

			WebElement productType = driver.findElement(By.cssSelector("select[id*=ddlProductType]"));
			Select productTypeSelection = new Select(productType);
			productTypeSelection.selectByVisibleText(productTypeIn);

			driver.findElement(By.id("btnSearch")).click();
			
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#SearchQueueTable")));
			List<WebElement> searchResultRows = driver.findElements(By.cssSelector("#SearchQueueTable tr"));
			String accountName;
			for(int rowNo=1;rowNo<searchResultRows.size();rowNo++){
				accountName=searchResultRows.get(rowNo).findElements(By.tagName("td")).get(3).getText();
				System.out.println(accountName);
				if(accountName.contains(accountName1)){
					int imgCheck = searchResultRows.get(rowNo).findElements(By.cssSelector("td img")).size();
					if(imgCheck>0){
						searchResultRows.get(rowNo).findElement(By.cssSelector("td img")).click();
						break;
					}
				}
			}
			WebElement acceptbtn = driver.findElement(By.cssSelector("img[id=btnAccept]"));
			acceptbtn.click();
			log.log("Accept contract in SUI", Status.PASS);
			return true;
		}catch(Exception e){
			log.log("Accept contract in SUI Error: "+e.getMessage(), Status.FAIL);
			return false;
		}	
	}
}
