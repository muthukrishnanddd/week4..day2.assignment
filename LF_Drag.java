package week4.day2.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LF_Drag {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.leafground.com/drag.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Draggable
		WebElement ele=driver.findElement(By.id("form:conpnl"));
		Actions builder=new Actions(driver);
		builder.dragAndDropBy(ele, 500, 200).perform();
		System.out.println(ele.getLocation().x);
		System.out.println(ele.getLocation().y);
		
		//Droppable
		WebElement drop=driver.findElement(By.id("form:drop"));
		WebElement dragcontent=driver.findElement(By.id("form:drag_content"));
		WebElement dropText=driver.findElement(By.xpath("//div/p[text()='Drop here']"));
		
		builder.dragAndDrop(dragcontent, drop).perform();
		String text=dropText.getText();
		System.out.println(text);
		if(text.equals("Dropped!")) {
			System.out.println("Drop successful");
		}
		//Draggable Columns
		

		WebElement col1=driver.findElement(By.xpath("//th[contains(@class,'ui-state-default ui-draggable-column ui-draggable ui-draggable-handle ui-droppable')][1]"));
		WebElement col2=driver.findElement(By.xpath("//th[contains(@class,'ui-state-default ui-draggable-column ui-draggable ui-draggable-handle ui-droppable')][2]"));
		WebElement col3=driver.findElement(By.xpath("//th[contains(@class,'ui-state-default ui-draggable-column ui-draggable ui-draggable-handle ui-droppable')][3]"));
		
		builder.dragAndDrop(col1, col3).perform(); //Not working for col1 to col3 but placed in col2
		
		Thread.sleep(5000);
		
		//Draggable Rows		
		WebElement r1=driver.findElement(By.xpath("//h5[text()='Draggable Rows']//following::tbody/tr"));
		WebElement r2=driver.findElement(By.xpath("//h5[text()='Draggable Rows']//following::tbody/tr[2]"));
		WebElement r3=driver.findElement(By.xpath("//h5[text()='Draggable Rows']//following::tbody/tr[3]"));
		builder.clickAndHold(r1).moveToElement(r3).pause(Duration.ofSeconds(5)).release().perform(); ///NOT Working, Dropping is not happening correctly
		//builder.dragAndDrop(r1, r3).pause(Duration.ofSeconds(5)).perform();
				
		//Resize		
		WebElement sidebar=driver.findElement(By.xpath("//div[@class='ui-resizable-handle ui-resizable-e']"));
		builder.clickAndHold(sidebar).moveByOffset(30, 0).release().perform();	
		
		//WebElement bottombar=driver.findElement(By.xpath("//div[@class='ui-resizable-handle ui-resizable-s']"));
		//builder.clickAndHold(bottombar).moveByOffset(0, 25).release().perform();
	
		
		
		////Progress Bar
		driver.findElement(By.xpath("//span[text()='Start']/parent::button")).click();
		while(!driver.findElement(By.xpath("//div[@class='ui-progressbar-label']")).getText().equals("100%")) {
			System.out.println(driver.findElement(By.xpath("//div[@class='ui-progressbar-label']")).getText());
			Thread.sleep(2000);
		}
		
		if(driver.findElement(By.xpath("//div[@class='ui-progressbar-label']")).getText().equals("100%")) {
			//if(driver.findElement(By.xpath("//span[text()='Progress Completed']")).getText().equals("Progress Completed")) {
			if(driver.findElement(By.xpath("//span[text()='Progress Completed']")).isDisplayed()) {
				System.out.println("Process Completed");
			}
		}
		
		
		//Range Slider
		WebElement left=driver.findElement(By.xpath("//span[@class='ui-slider-handle ui-corner-all ui-state-default']"));
		WebElement right=driver.findElement(By.xpath("//span[@class='ui-slider-handle ui-corner-all ui-state-default'][2]"));
		
		String leftValue=driver.findElement(By.xpath("//h4[text()='Range Slider']/following-sibling::input")).getAttribute("value");
		//System.out.println("leftValue"+leftValue);
		String rightValue=driver.findElement(By.xpath("//h4[text()='Range Slider']/following-sibling::input[2]")).getAttribute("value");
		//System.out.println("rightValue"+rightValue);
		int setlowValue=40;
		int sethighValue=75;
		
		if(Integer.parseInt(leftValue)<setlowValue) {
			String value;
			do {
				builder.clickAndHold(left).moveByOffset(5, 0).release().perform();
				value=driver.findElement(By.xpath("//h4[text()='Range Slider']/following-sibling::input")).getAttribute("value");
				System.out.println(value);
			}while(Integer.parseInt(value)!=setlowValue);
			
		}
		else if(Integer.parseInt(leftValue)>setlowValue) {
			System.out.println("INside if");
			String value;
			do {
				builder.clickAndHold(left).moveByOffset(-2, 0).release().perform();
				value=driver.findElement(By.xpath("//h4[text()='Range Slider']/following-sibling::input")).getAttribute("value");
				System.out.println(value);
			}while(Integer.parseInt(value)!=setlowValue);
			
		}
		
		if(Integer.parseInt(rightValue)<sethighValue) {
			String value;
			do {
				builder.clickAndHold(right).moveByOffset(4, 0).release().perform();
				value=driver.findElement(By.xpath("//h4[text()='Range Slider']/following-sibling::input[2]")).getAttribute("value");
				System.out.println(value);
			}while(Integer.parseInt(value)!=sethighValue);
			
		}
		if(Integer.parseInt(rightValue)>sethighValue) {
			String value;
			do {
				builder.clickAndHold(right).moveByOffset(-3, 0).release().perform();
				value=driver.findElement(By.xpath("//h4[text()='Range Slider']/following-sibling::input[2]")).getAttribute("value");
				System.out.println(value);
			}while(Integer.parseInt(value)!=sethighValue);
			
		}
		
	}

}
