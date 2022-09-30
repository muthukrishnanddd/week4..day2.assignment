package week4.day2.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;
/*
 * 1. Launch https://www.snapdeal.com/
2. Go to Mens Fashion
3. Go to Sports Shoes
4. Get the count of the sports shoes
5. Click Training shoes
6. Sort by Low to High
7. Check if the items displayed are sorted correctly
8.Select the price range (900-1200)
9.Filter with color Navy 
10 verify the all applied filters 
11. Mouse Hover on first resulting Training shoes
12. click QuickView button
13. Print the cost and the discount percentage
14. Take the snapshot of the shoes.
15. Close the current window
16. Close the main window
 */
public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		
		// 1. Launch https://www.snapdeal.com/
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions builder=new Actions(driver);
		Thread.sleep(2000);
		//2. Go to Mens Fashion
		WebElement ele=driver.findElement(By.xpath("//span[text()=\"Men's Fashion\"]/../.."));
		ele.click();
		Thread.sleep(3000);
		
		//3. Go to Sports Shoes
		driver.findElement(By.xpath("//span[text()=\"Sports Shoes\"]/..")).click();
		
		//4. Get the count of the sports shoes
		String sportsShoeCount = driver.findElement(By.xpath("//div[text()=\"Sports Shoes for Men\"]/../div[2]")).getText();
		System.out.println("sportsShoeCount" + sportsShoeCount);
		
		//5. Click Training shoes
		driver.findElement(By.xpath("//div[text()=\"Training Shoes\"]")).click();
		
		driver.findElement(By.xpath("//span[text()=\"Sort by:\"]/following-sibling::i")).click();

		//6. Sort by Low to High
		driver.findElement(By.xpath("//li[text()[normalize-space()='Price Low To High']]")).click();
		Thread.sleep(2000);
		
		//7. Check if the items displayed are sorted correctly
		List<WebElement> eleList = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<Integer> priceList=new ArrayList<Integer>();		
		
		for (WebElement i : eleList) {
			String p=i.getAttribute("data-price");
			int price=Integer.parseInt(p);
			//System.out.println("Price :" + price);
			priceList.add(price);			
		}

		List<Integer> spriceList = new ArrayList<Integer>(priceList);
		Collections.sort(spriceList);
		System.out.println("======");

		if(spriceList.equals(priceList)) System.out.println("Items are sorted correctly from Low to High");
		else System.out.println("Items are NOT sorted correctly from Low to High");
		
		//8.Select the price range (900-1200)
		driver.findElement(By.name("fromVal")).clear();
		driver.findElement(By.name("fromVal")).sendKeys("900");
		
		driver.findElement(By.name("toVal")).clear();
		driver.findElement(By.name("toVal")).sendKeys("1200");		

		driver.findElement(By.xpath("//div[text()[normalize-space()='GO']]")).click();
		Thread.sleep(2000);
		
		//9.Filter with color Navy 
		driver.findElement(By.xpath("//label[@for=\"Color_s-Blue\"]")).click();		
		Thread.sleep(2000);
		
		//10 verify the all applied filters 
		List<WebElement> ele2 = driver.findElements(By.xpath("//div[@class='filters-top-selected']/div/div/a"));
		for (WebElement i : ele2) {
			System.out.println(i.getText());
			if(i.getText().equalsIgnoreCase("Blue") ||i.getText().equals("Rs. 900 - Rs. 1200")) {
				System.out.println(i.getText() + "Filter is applied" );
			}
		}
		
		//11. Mouse Hover on first resulting Training shoes
		builder.moveToElement(driver.findElement(By.xpath("//input[@id='sortVal']/following-sibling::section/div"))).perform();
		
		//12. click QuickView button
		driver.findElement(By.xpath("//input[@id='sortVal']/following-sibling::section//div[contains(text(),'Quick View')]")).click();
		
		//13. Print the cost and the discount percentage
		String price = driver.findElement(By.xpath("//div[@class='lfloat']/div[2]/span[1]")).getText();
		String discount = driver.findElement(By.xpath("//div[@class='lfloat']/div[2]/span[2]")).getText();
		System.out.println("Price :" + price);
		System.out.println("Discount :" + discount);
		
		//14. Take the snapshot of the shoes.
		TakesScreenshot sshot= driver;
		File src = sshot.getScreenshotAs(OutputType.FILE);		
		File trg=new File("./Screenshot/snapdeal.png");		
		FileHandler.copy(src, trg);
		
		//Close the current window
		driver.findElement(By.xpath("//div[@class='close close1 marR10']")).click();
		//16. Close the main window
		driver.close();
		
		
		
		 
		
		

	}

}
