package marathon2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SalesForceContent extends SalesForceBaseClass {

	@BeforeTest
	public void setExcelSheetName() {
		fileName = "SalesForce";
		sheetName = "Content";
	}

	@Test(dataProvider = "fetchData")
	public void Sales_Force_Content(String Question, String Answer) throws InterruptedException {

		// Type Content on the Search box
		Thread.sleep(5000);
		driver.findElement(By.xpath(
				"//div[@class='slds-form-element__control slds-grow slds-input-has-icon slds-input-has-icon_left-right']"))
				.sendKeys("Content");

		// Click Content Link
		driver.findElement(By.xpath("//mark[text()='Content']")).click();

		// Click on Chatter Tab
		Thread.sleep(5000);
		WebElement findElement = driver.findElement(By.xpath("//span[text()='Chatter']"));
		driver.executeScript("arguments[0].click();", findElement);
		Thread.sleep(2000);

		// Verify Chatter title on the page
		String title = driver.getTitle();
		System.out.println(title);
		if (title.contains("Chatter")) {
			System.out.println("Chatter title is verified");
		} else {
			System.out.println("Chatter title is not verified");
		}

		// Click Question tab
		Thread.sleep(5000);
		WebElement findElement1 = driver.findElement(By.xpath("//span[text()='Question']"));
		driver.executeScript("arguments[0].click();", findElement1);
		Thread.sleep(2000);

		// Type Question with data (coming from excel)
		WebElement question = driver
				.findElement(By.xpath("//label/span[contains(text(),'Question')]/following::textarea"));
		question.sendKeys(Question);
		Thread.sleep(10000);
		String questionTyped = question.getAttribute("value");
		System.out.println("Entered Question: " + questionTyped);
		Thread.sleep(5000);

		// Type Details with data (coming from excel)
		WebElement answer = driver.findElement(By.xpath("//span[text()='Details']/following::p"));
		answer.sendKeys(Answer);
		Thread.sleep(5000);

		// Click Ask
		WebElement ask = driver.findElement(By.xpath("// button[text()='Ask']"));
		driver.executeScript("arguments[0].click();", ask);
		Thread.sleep(2000);

		// Confirm the question appears
		WebElement confirmQuestion = driver.findElement(By.xpath("(//span[@class='uiOutputText'])[3]"));
		Thread.sleep(5000);
		String text = confirmQuestion.getText();
		System.out.println("Retrieved Question: " + text);

		if (text.equalsIgnoreCase(questionTyped)) {
			System.out.println("Appeared Question is validated");
		} else {
			System.out.println("Appeared Question is not validated");
		}

	}

}
