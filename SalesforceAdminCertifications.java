package week4.day2.homeassignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;


public class SalesforceAdminCertifications {
	/*Assignments 1. Administrator Certifications
		==============================
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
		//		5. Click Learning and mouse hover on Learning On Trailhead
		WebElement findElementByXPath = dom.findElementByXPath("//span[text()='Learning']");
		findElementByXPath.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement findElementByXPath2 = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		wait.until(ExpectedConditions.visibilityOf(findElementByXPath2));
		Actions builder = new Actions(driver);
		builder.moveToElement(findElementByXPath2).perform();
		//		6. Clilck on Salesforce Certifications
		//WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement findElementByXPath3 = dom.findElementByXPath("//a[text()='Salesforce Certification']");
		Actions builder1 = new Actions(driver);
		//builder1.moveToElement(findElementByXPath3).perform();
		builder1.scrollToElement(findElementByXPath3).perform();
		findElementByXPath3.click();
		//		6. Click on Ceritification Administrator
		driver.findElement(By.linkText("Administrator")).click();
		//		7. Navigate to Certification 
		//- Administrator Overview window
		String title = driver.getTitle();
		System.out.println("Window: "+title);
		//		8. Verify the Certifications available for Administrator Certifications should be displayed
		List<WebElement> certifications = driver.findElements(By.xpath("//div[@class='credentials-card_title']//a"));
		for (int i = 0; i < certifications.size(); i++) {
			int j = i+1;
			String text = certifications.get(i).getText();
			System.out.println(j+" "+ text);
		}
		String title2 = driver.getTitle();
		if(title2.contains("Administrator"))
			System.out.println("Success");
			else
				System.out.println("Something wrong");
		
		driver.quit();
	
}
}
