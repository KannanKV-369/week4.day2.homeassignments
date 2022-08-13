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


public class SalesforceArchitectCertifications {
	/*
//	 * 	1. Launch Salesforce application https://login.salesforce.com/
//		2. Login with Provided Credentials
//		3. Click on Learn More link in Mobile Publisher
//		4. Click  On Resources


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
		Thread.sleep(3000);
		//		5. Click Learning
		WebElement findElementByXPath = dom.findElementByXPath("//span[text()='Learning']");
		findElementByXPath.click();
//		5. Select SalesForce Certification Under Learnings
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement findElementByXPath2 = dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		wait.until(ExpectedConditions.visibilityOf(findElementByXPath2));
		Actions builder = new Actions(driver);
		builder.moveToElement(findElementByXPath2).perform();
		
		WebElement element = dom.findElementByXPath("//a[text()='Salesforce Certification']");
		//WebElement salesForce = dom.findElementByXPath("//a[text()='Salesforce Certification']");
		Actions builder2   = new Actions(driver);
		builder2.click(element).perform();
		//		6. Choose Your Role as Salesforce Architect
		driver.findElement(By.xpath("(//a//div[@class='roleMenu-item_text'])[2]")).click();
		//		7. Get the Text(Summary) of Salesforce Architect
		String text = driver.findElement(By.xpath("//div[@class='cert-site_text slds-text-align--center Lh(1.5) Fz(16px) slds-container--center slds-p-bottom--large']")).getText();
		System.out.println("Salesforce Architect: "+ text);
		//		8. Get the List of Salesforce Architect Certifications Available
		List<WebElement> findElements = driver.findElements(By.xpath("//div[@class='credentials-card_title']//a"));
		int size = findElements.size();
		System.out.println("Size : "+ size);
		for (int i = 0; i < size; i++) {
			int j = i+1;
			String text2 = findElements.get(i).getText();
			System.out.println(j+": "+text2);
			
			
		}
		//		9. Click on Application Architect 
		WebElement findElement = driver.findElement(By.linkText("Application Architect"));
		String text3 = findElement.getText();
		System.out.println(text3);
		findElement.click();
		//		10.Get the List of Certifications available
		List<WebElement> archiTectCert = driver.findElements(By.xpath("//div[@class='credentials-card_title']//a"));
		String actext = "";
		for (int i = 0; i < archiTectCert.size(); i++) {
			int j = i+1;
			actext = archiTectCert.get(i).getText();
			System.out.println("Architect Certifications: "+j+"-"+actext);
		}
		System.out.println(driver.getTitle());
		if(actext.contains("Architect"))
			System.out.println("Success");
		else
			System.out.println("Wrong");
		
		driver.quit();

	}
}
