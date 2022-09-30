package week4.day2.assignments;

import java.awt.Window;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions builder=new Actions(driver);

		
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		builder.moveToElement(brands).perform();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//div[@id='list_topbrands']/following-sibling::div/a[text()=\"L'Oreal Paris\"]")).click();
		String title2 = driver.getTitle();
		if(title2.contains("L'Oreal")) {
			System.out.println("Title :" + title2);
		}
		
		//sortby 
		driver.findElement(By.xpath("//span[contains(text(),'Sort By')]/..")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']/../following-sibling::div")).click();
		Thread.sleep(2000);
		//String mainwindow = driver.getWindowHandle();
		
		WebElement hair=driver.findElement(By.xpath("//a[text()='hair']"));
		builder.moveToElement(hair).perform();
		Thread.sleep(2000);

		
		driver.findElement(By.xpath("//a[text()='hair']/following-sibling::ul//a[text()='Shampoo']")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		for (String i : windowHandles) {
			String title = driver.switchTo().window(i).getTitle();
			if(title.contains("Shampoo")) {
				driver.switchTo().window(i);				
			}
		}
		
		driver.findElement(By.xpath("//span[text()='Concern']/..")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']/../following-sibling::div")).click();
		
		//check filter applied or not
		String text = driver.findElement(By.xpath("//span[text()='Filters Applied']/../following-sibling::div/div/span")).getText();
		if(text.equals("Color Protection")) {
			System.out.println("Filter applied successfully");
		}
		
		List<WebElement> findElements = driver.findElements(By.xpath("//div[@id='product-list-wrap']/div/div/div/a/div[2]/div[1]"));
		for (WebElement i : findElements) {
			if(i.getText().contains("L'Oreal Paris Colour Protect Shampoo")) {
				driver.findElement(By.xpath("//div[text()=\"L'Oreal Paris Colour Protect Shampoo\"]")).click(); 
				break;

			}
		}
		Set<String> windowHandles1 = driver.getWindowHandles();
		for (String i : windowHandles1) {
			String title = driver.switchTo().window(i).getTitle();
			if(title.contains("L'Oreal Paris Colour Protect")) {
				driver.switchTo().window(i);				
			}
		}
		
		System.out.println("MRP : "+ driver.findElement(By.xpath("//span[text()='MRP:']/following-sibling::span")).getText());
		
		driver.findElement(By.xpath("//span[text()='Add to Bag']/..")).click();
		
		driver.findElement(By.xpath("//div[@id='header_id']/div[2]/div[1]/div[2]//button")).click();
		Thread.sleep(3000);
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='portal-root']//iframe")));
		
		String grandTotal = driver.findElement(By.xpath("//span[text()='Grand Total']/../following-sibling::div")).getText();
		
		System.out.println("Grand Total :" + grandTotal);
		
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[1]/div/div[2]/div/div/div[2]/div[2]/div/div[2]/button")).click();
		
		driver.switchTo().defaultContent();
		
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		
		String finalFrandTotal = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div")).getText();
		System.out.println("finalFrandTotal :" + finalFrandTotal);
		if(grandTotal.equals(finalFrandTotal)) System.out.println("Grand Total matches");
		driver.quit();
		
		
	}

}
