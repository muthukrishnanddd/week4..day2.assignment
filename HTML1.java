package week4.day2.assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HTML1 {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://html.com/tags/table/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
		System.out.println("Rows count :" + rows.size());
		
		List<WebElement> columnTable1 = driver.findElements(By.xpath("//table/thead/tr/th"));
		List<WebElement> columnTable2 = driver.findElements(By.xpath("//table/tbody/tr[1]/td"));
		int total=columnTable1.size() + columnTable2.size();
		System.out.println("Column count of table 1 + Table2 :"+total );
		
		
		
		

	}

}
