package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTable {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://finance.yahoo.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	//Click the More dropdown	
		WebElement more = driver.findElement(By.xpath("//button[contains(@class,'rapid-noclick-resp')]//span"));
		more.click();
		Thread.sleep(5000);
	//Click the Crypto Menu
		driver.findElement(By.xpath("//a[contains(@class,'_yb_1bwptbj')and text()='Crypto ']")).click();
	//Xpath for Table
		String column_names = driver.findElement(By.xpath("//table[contains(@class,'markets-table')]/tbody/tr[1]/td[2]")).getText();
		System.out.println(column_names);
		
		int count = column_names.length();
		System.out.println(count);
	//Iteration to find the name column
		for (int i=1; i<column_names.length(); i++) {
			String names = driver.findElement(By.xpath("//table[contains(@class,'markets-table')]/tbody/tr["+i+"]/td[2]")).getText();
			System.out.println(names);
		}
		System.out.println("WebTable Home Assignment completed");
	}

}
