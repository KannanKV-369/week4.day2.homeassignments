package week4.day2.homeassignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;


public class SalesforceCustomerServiceOptions {
	/*
			Assignments 3.Customer_Service_Options
		====================================
		1. Launch Salesforce application https://login.salesforce.com/
		2. Login with Provided Credentials
		3. Click on Learn More link in Mobile Publisher
		4. Click on Products and Mousehover on Service 
		5. Click on Customer Services
		6. Get the names Of Services Available 
		7. Verify the title

	 */

	public static void main(String[] args) throws InterruptedException {
		//Launch WebDriver
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		//Launch Chrome Browser
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//1. Launch Salesforce application https://login.salesforce.com
		driver.get("https://login.salesforce.com");
		Shadow dom = new Shadow(driver);
		Thread.sleep(3000);
		//2. Login with username as "ramkumar.ramaiah@testleaf.com " and password as "Password$123"
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password#123");
		driver.findElement(By.id("Login")).click();
		//3. Click on Learn More link in Mobile Publisher
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@title='Learn More']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstWindowHandles = new ArrayList<String>(windowHandles);
		driver.switchTo().window(lstWindowHandles.get(1));
		//		4. Click confirm on Confirm redirect
		driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand']")).click();

		//		4. Click on Products and Mousehover on Service
		WebElement element = dom.findElementByXPath("//span[text()='Products']");
		element.click();
		//		5. Click on Customer Services
		WebElement service = dom.findElementByXPath("//span[text()='Service']");
		Actions builder = new Actions(driver);
		builder.moveToElement(service).perform();
		builder.click(service).perform();
		WebElement custser = dom.findElementByXPath("//a[text()='Customer Service']");
		Actions builder2 = new Actions(driver);
		builder2.scrollToElement(custser).perform();
		builder2.click(custser).click().perform();
		//		6. Get the names Of Services Available
		List<WebElement> elements = driver.findElements(By.xpath("//a[@class='page-list-item ']"));
		for (int i = 0; i < elements.size(); i++) {
			int j = i+1;
			String text = elements.get(i).getText();
			System.out.print("Services Available :-");
			System.out.println(j+" "+text);
		}
		//		7. Verify the title
		String title = driver.getTitle();
		if(title.contains("Customer Service"))
			System.out.println("Success:   "+"Title is:  "+title);
		else
			System.out.println("Wrong");
		driver.quit();
	}
}
