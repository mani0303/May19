package com.test.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.support.Settings;

public class Test {

	public static void main(String[] args) {
		String chromeDriver=Settings.getInstance().getDriverEXEDir()+"chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriver);
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 120);
		Actions action = new Actions(driver);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		
		driver.get("https://debdehqtest.directenergy.com/REG1/DEHQ/Offer/Create/131544");
		
		WebElement userName = driver.findElement(By.id("UserName"));
		userName.sendKeys("businessadmin@test.com");
		//https://debdehqtest.directenergy.com/REG1/DEHQ/Prospect/Create
		//https://debdehqtest.directenergy.com/REG1/DEHQ/Account/Index
		WebElement password = driver.findElement(By.id("Password"));
		password.sendKeys("Testtest1");

		WebElement logIn = driver.findElement(By.id("main-form_submit"));
		js.executeScript("arguments[0].click();", logIn);
		WebElement ele = driver.findElement(By.xpath("//label[contains(text(),'Locations Ready To Price')]"));
		wait.until(ExpectedConditions.visibilityOf(ele));
		js.executeScript("arguments[0].click();", ele);

	}

}
