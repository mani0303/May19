package com.test.core;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.test.support.Settings;

public class OTS {

	public static void main(String[] args) {
		try{
			String chromeDriver=Settings.getInstance().getDriverEXEDir()+"chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriver);
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			JavascriptExecutor js=(JavascriptExecutor) driver;
			driver.get("http://rtpwapdverm14:8605/Status/ValidationStatus#");
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
			driver.findElement(By.id("files")).sendKeys("C:\\Users\\schangala\\Veera\\test-auto7.xlsx");
			driver.findElement(By.name("UploadFileButton")).click();
			Thread.sleep(15000);
			Thread.sleep(3000);
			WebElement usageStatus = driver.findElement(By.xpath("//li[contains(text(),'Usage Status')]"));
			Actions builder = new Actions(driver);
			builder.moveToElement(usageStatus).perform();
			WebElement accountUsageStatus = driver.findElement(By.linkText("Account Usage Status"));
			accountUsageStatus.click();
			js.executeScript("arguments[0].click();", accountUsageStatus);
			Thread.sleep(3000);
			String content;
			int tryCount = 0;
			do{
				driver.findElement(By.id("SearchButton")).click();
				content = driver.findElement(By.id("ValidationStatusTable")).getText();
				if(content.contains("08901003494944207777")){
					break;
				}
				Thread.sleep(15000);
				++tryCount;
			}while(tryCount<4);



			List<WebElement> accountNoList = driver.findElements(By.cssSelector("#ValidationStatusTable .accountNumber"));
			List<WebElement> validations = driver.findElements(By.cssSelector("table[id='ValidationStatusTable'] button[class*='view-validations']"));
			String accountNo;
			for(int i=0;i<accountNoList.size();i++){
				accountNo = accountNoList.get(i).getText();
				if(accountNo.equals("08901003494944207777")){
					validations.get(i).click();
				}
			}
			List<WebElement> validationTableList = driver.findElements(By.cssSelector("form[id='updateAccountForm'] tbody"));
			List<WebElement> validationTable = validationTableList.get(0).findElements(By.cssSelector("tr"));
			List<WebElement> validationValueList,validationStatusList;
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

			for(int rowNo = 0;rowNo<validationTable.size();rowNo++){
				validationTable = validationTableList.get(0).findElements(By.cssSelector("tr"));
				WebElement row = validationTable.get(rowNo);
				validationValueList = row.findElements(By.cssSelector("td select[id*='selectedData']"));
				validationStatusList = row.findElements(By.cssSelector("td div[class*='Error']"));
				if(validationValueList.size()>0&&validationStatusList.size()>0){
					Select select = new Select(validationValueList.get(0));
					WebElement optionValue = select.getOptions().get(1);
					select.selectByVisibleText(optionValue.getText());
				}
			}

			/*			for(WebElement row:validationTable){
				validationValueList = row.findElements(By.cssSelector("td select[id*='selectedData']"));
				validationStatusList = row.findElements(By.cssSelector("td div[class*='Error']"));
				if(validationValueList.size()>0&&validationStatusList.size()>0){
					Select select = new Select(validationValueList.get(0));
					WebElement optionValue = select.getOptions().get(1);
					select.selectByVisibleText(optionValue.getText());
				}
			}*/

			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.cssSelector("#updateAccountForm input[value='Submit']")).click();
			driver.findElement(By.id("publishRadioButton")).click();
			driver.findElement(By.cssSelector("#updateAccountForm input[value='Submit']")).click();
			String validationMessage=driver.findElement(By.id("ValidationResultsPopupPage")).getText();
			System.out.println(validationMessage);
			WebElement close = driver.findElement(By.partialLinkText("close"));
			close.click();
		}catch(Exception e){
			e.printStackTrace();
		}


	}

}
