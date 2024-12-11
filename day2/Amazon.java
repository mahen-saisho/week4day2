package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Amazon {

	public static void main(String[] args) throws IOException,InterruptedException {
		// TODO Auto-generated method stub
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	//Search the mobile	
		Thread.sleep(15000);
//Initially CAPTCHA page will come, given 15 seconds to enter the captcha manually and click continue button manually
		driver.findElement(By.xpath("//input[@name='field-keywords']")).sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();	
	//First Product price printing)
		String firstproduct = driver.findElement(By.xpath("(//span[@class='a-price'])[1]")).getText();
		System.out.println("First Product Price :"+firstproduct);
	//First Product rating printing
		String rating = driver.findElement(By.xpath("(//span[contains(@class, 'a-size-base')])[2]")).getText();
		System.out.println("First Product Rating is "+rating);
	//Select the first text link of the image
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[contains(@class,'a-section a-spacing-none puis-padding-right-small')])[1]/a")).click();
	//Window handling
		Set<String> childwindow = driver.getWindowHandles();
	//Convert Set into List
		ArrayList<String> getwindow = new ArrayList<String>(childwindow);
		driver.switchTo().window(getwindow.get(1));
	//print title
		String title = driver.getTitle();
		System.out.println(title);
	//Snapshot		
		File scr = driver.getScreenshotAs(OutputType.FILE);
		File trg=new File("./Snapshot/AmazonSnap1.png");
		FileUtils.copyFile(scr, trg);		
		System.out.println("Snapshot successfully Stored");
	//Click Add To Cart		
		Thread.sleep(3000);
		// Find all Add to Cart buttons
		WebElement cartone = driver.findElement(By.xpath("(//input[@id='add-to-cart-button'])[1]"));
		WebElement carttwo = driver.findElement(By.xpath("(//input[@id='add-to-cart-button'])[2]"));
		
		if (cartone.isEnabled() && cartone.isDisplayed()) {
		    cartone.click();
		    System.out.println("Clicked the first 'Add to Cart' button.");
		} else if (carttwo.isEnabled() && carttwo.isDisplayed()) {
		    carttwo.click();
		    System.out.println("Clicked the second 'Add to Cart' button.");
		} else {
		    System.out.println("Neither button is enabled or visible.");
		    
		}
	//SubTotal
		Thread.sleep(5000);
		String subtotal = driver.findElement(By.xpath("//div[@id='attach-desktop-sideSheet']//span[@id='attach-accessory-cart-subtotal']")).getText();
		System.out.println("SubTotal Price :"+subtotal);
	//Remove .00 from subtotal	
		if (subtotal.endsWith(".00")) {
            subtotal = subtotal.replace(".00", "");
        }
		int ratingInt = Integer.parseInt(rating.replace("₹", "").replace(",", ""));
        int subtotalInt = Integer.parseInt(subtotal.replace("₹", "").replace(",", ""));
	//Comparing Total and Sub-Total are same
		if(ratingInt==subtotalInt) {
			System.out.println("Total Price "+ ratingInt);
			System.out.println("Sub-Total "+ subtotalInt);
			System.out.println("Total & Sub Total are same");
		}
		else {
			System.out.println("Total and Sub Total are same amount. But sub-total having symbol and comma "+subtotalInt);
		}	
		System.out.println("Amazon Assignment completed");
	}


}
