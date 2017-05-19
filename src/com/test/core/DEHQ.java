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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.support.Settings;

public class DEHQ {

	public static void main(String[] args) {
		try{
			String chromeDriver=Settings.getInstance().getDriverEXEDir()+"chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriver);
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			WebDriverWait wait = new WebDriverWait(driver, 120);
			Actions action = new Actions(driver);
			JavascriptExecutor js=(JavascriptExecutor) driver;
			
			driver.get("https://debdehqtest.directenergy.com/REG1/DEHQ/Account/Index");
			WebElement userName = driver.findElement(By.id("UserName"));
			userName.sendKeys("businessadmin@test.com");
			//https://debdehqtest.directenergy.com/REG1/DEHQ/Prospect/Create
			//https://debdehqtest.directenergy.com/REG1/DEHQ/Account/Index
			WebElement password = driver.findElement(By.id("Password"));
			password.sendKeys("Testtest1");

			WebElement logIn = driver.findElement(By.id("main-form_submit"));
			js.executeScript("arguments[0].click();", logIn);

			WebElement newCustomer = driver.findElement(By.linkText("NEW CUSTOMER"));
			newCustomer.click();

			WebElement legalEntityName = driver.findElement(By.id("Customer_LegalEntityName"));
			js.executeScript("arguments[0].click();", legalEntityName);
			Thread.sleep(2000);
			legalEntityName.sendKeys("TestAutomation");

			WebElement address1 = driver.findElement(By.id("Customer_Address1"));
			js.executeScript("arguments[0].click();", address1);
			Thread.sleep(2000);
			address1.sendKeys("Street 1");

			WebElement city = driver.findElement(By.id("Customer_City"));
			js.executeScript("arguments[0].click();", city);
			Thread.sleep(2000);
			city.sendKeys("Avenel");

			WebElement custState = driver.findElement(By.cssSelector("span[aria-owns='Customer_State_listbox']"));
			js.executeScript("arguments[0].click();", custState);
			Thread.sleep(2000);

			List<WebElement> custStateItems = driver.findElements(By.cssSelector("#Customer_State_listbox li.k-item"));
			WebElement custStateItem = custStateItems.get(29);
			js.executeScript("arguments[0].click();", custStateItem);
			Thread.sleep(2000);

			WebElement zip = driver.findElement(By.id("Customer_Zip"));
			js.executeScript("arguments[0].click();", zip);
			Thread.sleep(1000);
			zip.sendKeys("-35244");

			WebElement custSearch = driver.findElement(By.cssSelector("input[value='Customer Search']"));
			js.executeScript("arguments[0].click();", custSearch);
			Thread.sleep(2000);

			WebElement custNotFound = driver.findElement(By.id("secondaryActionButton"));
			wait.until(ExpectedConditions.visibilityOf(custNotFound));
			js.executeScript("arguments[0].click();", custNotFound);
			/*try{
				js.executeScript("arguments[0].click();", custNotFound);
			}catch(Exception e){
				System.out.println("Pass");
				custNotFound.click();
			}*/
			Thread.sleep(2000);

			WebElement createNewCustomer = driver.findElement(By.linkText("CREATE NEW CUSTOMER"));
			js.executeScript("arguments[0].click();", createNewCustomer);
			Thread.sleep(2000);

			WebElement creditIcon = driver.findElement(By.cssSelector("#CreditInfoTitle span[class*=circle-plus]"));
			js.executeScript("arguments[0].click();", creditIcon);
			Thread.sleep(2000);

			WebElement startDate = driver.findElement(By.cssSelector("span[aria-owns='CreditInfo_TermStartDate_listbox']"));
			js.executeScript("arguments[0].click();", startDate);
			Thread.sleep(2000);

			List<WebElement> startDateItems = driver.findElements(By.cssSelector("#CreditInfo_TermStartDate_listbox li.k-item"));
			WebElement startDateFirstItem = startDateItems.get(1);
			js.executeScript("arguments[0].click();", startDateFirstItem);
			Thread.sleep(2000);


			WebElement annualUsuage = driver.findElement(By.cssSelector("span[class*='k-numerictextbox'] input[class*='k-input']"));
			wait.until(ExpectedConditions.elementToBeClickable(annualUsuage));
			js.executeScript("arguments[0].click();", annualUsuage);
			Thread.sleep(2000);
			//js.executeScript("arguments[0].setAttribute('value','35244');", annualUsuage);
			annualUsuage.sendKeys(Keys.chord("-35244"));

			WebElement runCredit = driver.findElement(By.id("RunCreditButton"));
			wait.until(ExpectedConditions.elementToBeClickable(runCredit));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", runCredit);

			WebElement acceptCredit = driver.findElement(By.name("CreditInfo.InvoiceVerification"));
			wait.until(ExpectedConditions.elementToBeClickable(acceptCredit));
			js.executeScript("arguments[0].click();", acceptCredit);
			Thread.sleep(2000);

			WebElement custContactIcon = driver.findElement(By.cssSelector("#ContactInfoTitle span[class*=circle-plus]"));
			js.executeScript("arguments[0].click();", custContactIcon);
			Thread.sleep(2000);

			WebElement contactFirstName = driver.findElement(By.id("Contact_FirstName"));
			js.executeScript("arguments[0].click();", contactFirstName);
			Thread.sleep(2000);
			js.executeScript("arguments[0].setAttribute('value','Test');", contactFirstName);

			WebElement contactLastName = driver.findElement(By.id("Contact_LastName"));
			js.executeScript("arguments[0].click();", contactLastName);
			Thread.sleep(2000);
			js.executeScript("arguments[0].setAttribute('value','Automation');", contactLastName);

			WebElement primaryPhoneNumber = driver.findElement(By.id("Contact_PrimaryPhoneNumber"));
			js.executeScript("arguments[0].click();", primaryPhoneNumber);
			Thread.sleep(2000);

			//action.sendKeys(primaryPhoneNumber, "8756455556");
			primaryPhoneNumber.sendKeys(Keys.chord("-8756455556"));
			//js.executeScript("arguments[0].setAttribute('value','875-645-5556');", primaryPhoneNumber);

			WebElement primaryPhone = driver.findElement(By.cssSelector("span[aria-owns='Contact_PrimaryPhoneTypeId_listbox']"));
			js.executeScript("arguments[0].click();", primaryPhone);
			Thread.sleep(2000);

			List<WebElement> primaryPhoneItems = driver.findElements(By.cssSelector("#Contact_PrimaryPhoneTypeId_listbox li.k-item"));
			WebElement primaryPhoneFirstItem = primaryPhoneItems.get(1);
			js.executeScript("arguments[0].click();", primaryPhoneFirstItem);
			Thread.sleep(2000);

			WebElement locationIcon = driver.findElement(By.cssSelector("#LocationsTitle span[class*=circle-plus]"));
			js.executeScript("arguments[0].click();", locationIcon);
			Thread.sleep(2000);

			WebElement enterNewLocation = driver.findElement(By.cssSelector("input[value='ENTER A LOCATION']"));
			js.executeScript("arguments[0].click();", enterNewLocation);
			Thread.sleep(2000);

			WebElement stateCode = driver.findElement(By.cssSelector("span[aria-owns='StateCode_listbox']"));
			js.executeScript("arguments[0].click();", stateCode);
			Thread.sleep(2000);

			List<WebElement> stateCodeItems = driver.findElements(By.cssSelector("#StateCode_listbox li.k-item"));
			WebElement stateCodeItem = stateCodeItems.get(7);
			js.executeScript("arguments[0].click();", stateCodeItem);
			Thread.sleep(2000);

			WebElement utility = driver.findElement(By.cssSelector("span[aria-owns='UtilityCode_listbox']"));
			wait.until(ExpectedConditions.elementToBeClickable(utility));
			js.executeScript("arguments[0].click();", utility);
			Thread.sleep(2000);

			List<WebElement> utilityItems = driver.findElements(By.cssSelector("#UtilityCode_listbox li.k-item"));
			WebElement utilityItem = utilityItems.get(2);
			js.executeScript("arguments[0].click();", utilityItem);
			Thread.sleep(2000);

			WebElement accountNumber = driver.findElement(By.id("LdcAccountNumber"));
			js.executeScript("arguments[0].click();", accountNumber);
			Thread.sleep(3000);			
			accountNumber.sendKeys(Keys.chord("-123455432112345533"));

			WebElement continue1 = driver.findElement(By.linkText("CONTINUE"));
			js.executeScript("arguments[0].click();", continue1);
			Thread.sleep(2000);

			WebElement serviceLocation = driver.findElement(By.id("ServiceLocationLocationName"));
			js.executeScript("arguments[0].click();", serviceLocation);
			Thread.sleep(2000);
			js.executeScript("arguments[0].setAttribute('value','Test');", serviceLocation);

			WebElement pricingType = driver.findElement(By.cssSelector("span[aria-owns='PricingType_listbox']"));
			js.executeScript("arguments[0].click();", pricingType);
			Thread.sleep(2000);

			List<WebElement> pricingTypeItems = driver.findElements(By.cssSelector("#PricingType_listbox li.k-item"));
			WebElement pricingTypeItem = pricingTypeItems.get(2);
			js.executeScript("arguments[0].click();", pricingTypeItem);
			Thread.sleep(2000);



			WebElement addLocation = driver.findElement(By.linkText("ADD LOCATION"));
			js.executeScript("arguments[0].click();", addLocation);
			Thread.sleep(2000);

			WebElement applyReleaseChk = driver.findElement(By.cssSelector("#DocumentEditProspectLocations .masterCheckbox"));
			js.executeScript("arguments[0].click();", applyReleaseChk);
			Thread.sleep(2000);

			WebElement assignRep = driver.findElement(By.linkText("Assign New Sales Rep"));
			js.executeScript("arguments[0].click();", assignRep);
			Thread.sleep(2000);

			WebElement searchSalesRep = driver.findElement(By.id("searchSalesRep"));
			js.executeScript("arguments[0].click();", searchSalesRep);
			Thread.sleep(2000);
			searchSalesRep.sendKeys("Maria Alderete");
			searchSalesRep.sendKeys(Keys.ENTER);

			WebElement searchButton = driver.findElement(By.cssSelector("#searchButton span"));
			js.executeScript("arguments[0].click();", searchButton);
			Thread.sleep(2000);

			WebElement selectSalesRep = driver.findElement(By.xpath("//td[contains(text(),'Maria')]"));
			Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(selectSalesRep));
			selectSalesRep.click();
			Thread.sleep(2000);

			WebElement assign = driver.findElement(By.linkText("ASSIGN"));
			js.executeScript("arguments[0].click();", assign);
			Thread.sleep(2000);

			applyReleaseChk = driver.findElement(By.cssSelector("#releaseRequestSelectionWindow .masterCheckbox"));
			js.executeScript("arguments[0].click();", applyReleaseChk);
			Thread.sleep(2000);

			WebElement applyRelease = driver.findElement(By.linkText("APPLY RELEASE"));
			js.executeScript("arguments[0].click();", applyRelease);
			Thread.sleep(2000);

			WebElement releaseLocation = driver.findElement(By.linkText("RELEASE LOCATION(S)"));
			js.executeScript("arguments[0].click();", releaseLocation);
			Thread.sleep(2000);

			WebElement OKButton = driver.findElement(By.linkText("OK"));
			js.executeScript("arguments[0].click();", OKButton);
			Thread.sleep(2000);

			WebElement saveAndContinue = driver.findElement(By.id("BottomCreateOfferButton"));
			js.executeScript("arguments[0].click();", saveAndContinue);
			Thread.sleep(2000);
			
			WebElement accountLink = driver.findElement(By.id("//label[contains(text(),'Locations Ready To Price')]"));
			wait.until(ExpectedConditions.visibilityOf(accountLink));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", accountLink);
			
			Thread.sleep(2000);
			
			OKButton = driver.findElement(By.linkText("OK"));
			js.executeScript("arguments[0].click();", OKButton);
			Thread.sleep(2000);
			
			WebElement product = driver.findElement(By.cssSelector("span[aria-owns='NewOffer_ProductId_listbox']"));
			wait.until(ExpectedConditions.elementToBeClickable(product));
			js.executeScript("arguments[0].click();", product);
			Thread.sleep(2000);

			List<WebElement> productItems = driver.findElements(By.cssSelector("#NewOffer_ProductId_listbox li.k-item"));
			WebElement productItem = productItems.get(2);
			js.executeScript("arguments[0].click();", productItem);
			Thread.sleep(2000);
			
			WebElement taxExempt = driver.findElement(By.cssSelector("span[aria-owns='TaxExempt_listbox']"));
			wait.until(ExpectedConditions.elementToBeClickable(taxExempt));
			js.executeScript("arguments[0].click();", taxExempt);
			Thread.sleep(2000);

			List<WebElement> taxExemptItems = driver.findElements(By.cssSelector("#TaxExempt_listbox li.k-item"));
			WebElement taxExemptItem = taxExemptItems.get(2);
			js.executeScript("arguments[0].click();", taxExemptItem);
			Thread.sleep(2000);
			
			WebElement priceButton = driver.findElement(By.id("CreateOfferSubmitButton"));
			js.executeScript("arguments[0].click();", priceButton);
			Thread.sleep(2000);
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
