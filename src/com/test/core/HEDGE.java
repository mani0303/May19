package com.test.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.test.support.Settings;

public class HEDGE {

	public static void main(String[] args) {
		try{
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requireWindowFocus", true);
			String ieDriver=Settings.getInstance().getDriverEXEDir()+"IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", ieDriver);
			WebDriver driver = new InternetExplorerDriver(capabilities);
			driver.get("http://rtpwwbdvhdg13/HedgeDirect/");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.switchTo().frame("MainFrame");
			driver.findElement(By.id("m_txtLoginId_TextBox")).sendKeys("smaruthamuth");
			driver.findElement(By.id("m_txtLoginPswd_TextBox")).sendKeys("Direct1");
			driver.findElement(By.id("m_btnLogin_m_Button")).click();
			driver.switchTo().parentFrame();
			driver.switchTo().frame("MenuFrame");
			driver.findElement(By.partialLinkText("Manage Contracts")).click();
			driver.switchTo().parentFrame();
			driver.switchTo().frame("MainFrame");
			driver.findElement(By.id("m_IdNumber_TextBox")).sendKeys("3003277");
			driver.findElement(By.id("m_btnSearch_m_Button")).click();
			driver.switchTo().parentFrame();
			driver.switchTo().frame("MainFrame");
			driver.switchTo().frame("iframeData");
			driver.findElement(By.linkText("3003277")).click();
			driver.switchTo().parentFrame();
			driver.switchTo().frame("MainFrame");
			driver.findElement(By.xpath("//td[text()='Price']")).click();
			driver.switchTo().parentFrame();
			driver.switchTo().frame("MainFrame");
			driver.switchTo().frame("tabframe");
			driver.findElement(By.id("m_OverridePriceHeld_m_CheckBox")).click();
			driver.findElement(By.id("m_btnGetNewPrice_m_Button")).click();	 			
			driver.findElement(By.linkText("here")).click();
			driver.switchTo().parentFrame();
			driver.switchTo().frame("MainFrame");
			int counter=0;
			WebElement SearchBtn = driver.findElement(By.id("m_btnSearch_m_Button"));
			SearchBtn.click();
			String firstRowText="";
			do{
				Thread.sleep(6000);
				firstRowText = driver.findElement(By.cssSelector("#dgPricingRequests .DataGridItem")).getText();
				SearchBtn = driver.findElement(By.id("m_btnSearch_m_Button"));
				SearchBtn.click();
				if(counter>10 || firstRowText.contains("Failed")){
					break;
				}
				++counter;
			}while(!firstRowText.contains("Success"));
			driver.switchTo().parentFrame();
			driver.switchTo().frame("RightHeaderFrame");
			driver.findElement(By.id("m_aLogoutButton")).click();			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
