package com.test.pageobjects;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.core.RunnerHelper;
import com.test.support.Status;

public class OTS_AccountUsageStatusPage extends PageSupporter{
	JavascriptExecutor js=(JavascriptExecutor) driver;
	public OTS_AccountUsageStatusPage(RunnerHelper helper) {
		super(helper);
	}

	public boolean validateAccountUsageStatus(){
		try{
			WebDriverWait wait = new WebDriverWait(driver, 60);
			String accountNo = data.get("AccountNo").trim();
			WebElement usageStatus = driver.findElement(By.xpath("//li[contains(text(),'Usage Status')]"));
			Actions builder = new Actions(driver);
			builder.moveToElement(usageStatus).perform();
			WebElement accountUsageStatus = driver.findElement(By.linkText("Account Usage Status"));
			js.executeScript("arguments[0].click();", accountUsageStatus);
			Thread.sleep(3000);
			log.log("Navigate to Account usage status", Status.INFO);
			String content;
			int tryCount = 0;
			do{
				driver.findElement(By.id("SearchButton")).click();
				Thread.sleep(10000);
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ValidationStatusTable"))));
				content = driver.findElement(By.id("ValidationStatusTable")).getText();
				if(content.contains(accountNo)&&!content.contains("Processing")){
					break;
				}
				++tryCount;
			}while(tryCount<4);

			List<WebElement> accountNoList = driver.findElements(By.cssSelector("#ValidationStatusTable .accountNumber"));
			List<WebElement> validations = driver.findElements(By.cssSelector("table[id='ValidationStatusTable'] button[class*='view-validations']"));
			//List<WebElement> processingStatus = driver.findElements(By.cssSelector("#ValidationStatusTable tbody tr"));
			String appAccountNo;
			int count=0;
			for(int i=0;i<accountNoList.size();i++){
			count = i + 1;
			WebElement status = driver.findElement(By.xpath("//table[@id='ValidationStatusTable']//tr["+count+"]//td[5]"));
				while(status.getText().contains("Processing")){
					Thread.sleep(2000);
					status = driver.findElement(By.xpath("//table[@id='ValidationStatusTable']//tr["+count+"]//td[5]"));
					if(!status.getText().contains("Processing")){
						break;
					}
				}
				appAccountNo = accountNoList.get(i).getText();
				if(appAccountNo.trim().equals(accountNo)){
					validations.get(i).click();
					break;
				}
			}
			List<WebElement> validationTableList = driver.findElements(By.cssSelector("form[id='updateAccountForm'] tbody"));
			List<WebElement> validationTable = validationTableList.get(0).findElements(By.cssSelector("tr"));
			List<WebElement> validationValueList,validationStatusList;
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			int attempt=0;
			for(int rowNo = 1;rowNo<=validationTable.size();rowNo++){
				try{
					//validationTable = validationTableList.get(0).findElements(By.cssSelector("tr"));
					WebElement row = driver.findElement(By.xpath("//form[@id='updateAccountForm']//tbody/tr["+rowNo+"][1]"));
					//WebElement row = validationTable.get(rowNo);
					validationValueList = row.findElements(By.cssSelector("td select[id*='selectedData']"));
					validationStatusList = row.findElements(By.cssSelector("td div[class*='Error']"));
					if(validationValueList.size()>0&&validationStatusList.size()>0){
						Select select = new Select(validationValueList.get(0));
						WebElement optionValue = select.getOptions().get(1);
						select.selectByVisibleText(optionValue.getText());
					}
				}catch(StaleElementReferenceException e){
					rowNo--;
					attempt++;
					System.out.println("attempt: "+attempt);
					if(attempt==5){
						break;
					}
				}
			}

			driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("#updateAccountForm input[value='Submit']")).click();
			Thread.sleep(5000);
			WebElement publishButton = driver.findElement(By.id("publishRadioButton"));
			wait.until(ExpectedConditions.visibilityOf(publishButton));
			publishButton.click();
			Thread.sleep(5000);
			WebElement submitButton = driver.findElement(By.cssSelector("#updateAccountForm input[value='Submit']"));
			wait.until(ExpectedConditions.visibilityOf(submitButton));
			submitButton.click();
			Thread.sleep(5000);
			WebElement validationMessage=driver.findElement(By.id("ValidationResultsPopupPage"));
			wait.until(ExpectedConditions.visibilityOf(validationMessage));
			System.out.println(validationMessage.getText());
			WebElement close = driver.findElement(By.partialLinkText("close"));
			wait.until(ExpectedConditions.visibilityOf(close));
			close.click();
			log.log("Publish usage in OTS", Status.PASS);
			return true;
		}catch(Exception e){
			
			log.log("Publish usage in OTS Error: "+e.getMessage(), Status.FAIL);
			return false;
		}	
	}
}
