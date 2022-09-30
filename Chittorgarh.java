package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Chittorgarh {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		
		// 1. Launch https://www.snapdeal.com/
		driver.get("https://www.chittorgarh.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions builder=new Actions(driver);
		
		driver.findElement(By.id("navbtn_stockmarket")).click();
		driver.findElement(By.xpath("//a[text()='NSE Bulk Deals']")).click();
		
		List<WebElement> secNameEle = driver.findElements(By.xpath("//table[@class='table table-bordered table-condensed table-striped']/tbody/tr/td[3]"));
		
		List<String> nameList=new ArrayList<String>();
		Set<String> nameset=new LinkedHashSet<String>();
		
		for (WebElement i : secNameEle) {
			String name=i.getText();
			nameList.add(name);
			nameset.add(name);			
		}
		if(nameList.size()==nameset.size()) System.out.println("No Duplicates listed");
		else System.out.println(nameList.size()-nameset.size() + " Duplicates available");
		
	}

}
