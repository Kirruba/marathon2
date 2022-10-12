package marathon2;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceBaseClass {

	public RemoteWebDriver driver;
	String fileName;
	String sheetName;

	@Parameters({ "browser", "url", "username", "password" })

	@BeforeMethod
	public void preConditions(String browser, String url, String username, String password)
			throws InterruptedException {
		if (browser.equalsIgnoreCase("Chrome")) {

			// Launch Chrome
			WebDriverManager.chromedriver().setup();

			// Disable Notifications
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);

		} else if (browser.equalsIgnoreCase("Edge")) {

			// Launch Edge
			WebDriverManager.edgedriver().setup();

			// Disable Notifications
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--disable-notifications");
			driver = new EdgeDriver(options);

		}

		// Load Salesforce link
		driver.get(url);
		driver.manage().window().maximize();

		// Add Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		// enter username
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);

		// enter password
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);

		// click login button
		driver.findElement(By.xpath("//input[@id='Login']")).click();

		// Click on the App Launcher (dots)
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();

		// Click view All
		Thread.sleep(10000);
		driver.findElement(By.xpath("//button[text()='View All']")).click();

	}

	@AfterMethod
	public void postConditions() {
		driver.close();
	}

	@DataProvider(name = "fetchData")
	public String[][] fetchData() throws IOException {

		return ReadExcelData.readExcel(fileName, sheetName);

	}

}
