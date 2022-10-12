package marathon2;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SalesForceIndividuals extends SalesForceBaseClass {

	String fileName, sheetName;

	@BeforeTest
	public void setExcelSheetName() {
		fileName = "SalesForce";
		sheetName = "Individuals";
	}

	@Test(dataProvider = "fetchData")
	public void Sales_Force_Individuals(String LastName) throws InterruptedException {

		// Type Individuals on the Search box
		Thread.sleep(5000);
		driver.findElement(By.xpath(
				"//div[@class='slds-form-element__control slds-grow slds-input-has-icon slds-input-has-icon_left-right']"))
				.sendKeys("Individuals");
		driver.findElement(By.xpath("//mark[text()='Individuals']")).click();

		// Click New
		driver.findElement(By.xpath("//div[text()='New']")).click();

		// Select Salutation with data
		Thread.sleep(2000);
		WebElement findElement = driver.findElement(By.xpath("//span[text()='Salutation']/following::a"));
		driver.executeScript("arguments[0].click();", findElement);
		Thread.sleep(2000);
		WebElement findElement2 = driver.findElement(By.xpath("//a[text()='Ms.']"));
		driver.executeScript("arguments[0].click();", findElement2);

		// Type Last Name
		WebElement lastName = driver.findElement(By.xpath("//span[text()='Last Name']/following::input"));
		lastName.sendKeys(LastName);
		Thread.sleep(2000);
		String typedLastName = lastName.getAttribute("value");
		System.out.println("Entered name: " + typedLastName);

		// Click Save
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();

		// Click on the App Launcher (dots)
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();

		// Click View All
		Thread.sleep(10000);
		driver.findElement(By.xpath("//button[text()='View All']")).click();

		// Type Customers on the Search box
		Thread.sleep(5000);
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']"));
		// driver.executeScript("arguments[0].click;", search);
		// Thread.sleep(5000);
		search.sendKeys("Customers");
		Thread.sleep(5000);

		// Click Customers Link
		driver.findElement(By.xpath("//mark[text()='Customers']")).click();
		Thread.sleep(5000);

		// Click New
		driver.findElement(By.xpath("//div[text()='New']")).click();
		Thread.sleep(2000);

		// Type the same name provided in step 8 and confirm it appears
		driver.findElement(By.xpath("//div[@class='autocompleteWrapper slds-grow']/input")).sendKeys(typedLastName);
		Thread.sleep(5000);
		String retrievedName = driver
				.findElement(By.xpath("//div[@class='primaryLabel slds-truncate slds-lookup__result-text']"))
				.getAttribute("title");
		System.out.println("Retrieved name: " + retrievedName);
		if (typedLastName.equalsIgnoreCase(retrievedName)) {
			System.out.println("Name is verified successfully");
		} else {
			System.out.println("Name is not verified");

		}
	}

	@DataProvider(name = "fetchData")
	public String[][] fetchData() throws IOException {

		return ReadExcelData.readExcel(fileName, sheetName);

	}

}

/*
 * Test case : 001 (Chrome) 01) Launch https://login.salesforce.com/ 02) Login
 * to Salesforce with your username and password 03) Click on the App Launcher
 * (dots) 04) Click View All 05) Type Individuals on the Search box 06) Click
 * Individuals Link 07) Click New 08) Select Salutation with data (coming from
 * excel) 09) Type Last Name 10) Click Save 11) Click on the App Launcher (dots)
 * 12) Click View All 13) Type Customers on the Search box 14) Click Customers
 * Link 15) Click New 16) Type the same name provided in step 8 and confirm it
 * appears 17) Close the browser
 */
/*
 * ChromeOptions ch=new ChromeOptions();
 * ch.addArguments("--disable-notifications"); ChromeDriver driver=new
 * ChromeDriver(ch);
 */