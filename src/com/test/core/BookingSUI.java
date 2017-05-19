package com.test.core;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.support.Settings;

public class BookingSUI {

	public static void main(String[] args) {
		String chromeDriver=Settings.getInstance().getDriverEXEDir()+"chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriver);
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://rtpwwbdvsui04:81/");
		
		WebElement source = driver.findElement(By.cssSelector("select[id*=ddlSourceSystem]"));
		Select sourceSelection = new Select(source);
		sourceSelection.selectByVisibleText("HedgeDirect");
		
		WebElement productType = driver.findElement(By.cssSelector("select[id*=ddlProductType]"));
		Select productTypeSelection = new Select(productType);
		productTypeSelection.selectByVisibleText("Discrete");
		
		driver.findElement(By.id("btnSearch")).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#SearchQueueTable")));
		List<WebElement> searchResultRows = driver.findElements(By.cssSelector("#SearchQueueTable tr"));
		String accountName;
		for(int rowNo=1;rowNo<searchResultRows.size();rowNo++){
			accountName=searchResultRows.get(rowNo).findElements(By.tagName("td")).get(3).getText();
			System.out.println(accountName);
			if(accountName.contains("GL51S1Regr Gowtham_2")){
				int imgCheck = searchResultRows.get(rowNo).findElements(By.cssSelector("td img")).size();
				if(imgCheck>0){
					searchResultRows.get(rowNo).findElement(By.cssSelector("td img")).click();
					break;
				}
			}
		}
		WebElement acceptbtn = driver.findElement(By.cssSelector("img[id=btnAccept]"));
		acceptbtn.click();
		
	}

}
