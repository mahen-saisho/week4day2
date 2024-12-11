package week4.day2;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.sl.usermodel.Shadow;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BigBasket {

	public static void main(String[] args) throws IOException,InterruptedException {
		// TODO Auto-generated method stub
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	//Click Shop by Category
		driver.findElement(By.xpath("//div[contains(@class,'relative')]//following::span[text()='Shop by']")).click();
		//driver.findElement(By.id("nav-search-submit-button")).click();
	//Hover First menu	
		//WebElement locate1 = driver.findElement(By.xpath("//div[contains(@id,'headlessui-menu-items')]//following::ul[contains(@class,'jsx')]//a[text()='Foodgrains, Oil & Masala']"));
		WebElement text1 = driver.findElement(By.xpath("(//a[contains(text(),'Foodgrains, Oil & Masala')])[2]"));
		String text = text1.getText();
		System.out.println(text);
		Thread.sleep(10000);

		//driver.executeScript("arguments[0].scrollIntoView(true);", locate1);
		Actions actions_firstmenu = new Actions(driver);
		actions_firstmenu.moveToElement(text1).perform();
		Thread.sleep(5000);
		
	//Hover Second Menu inside of first menu
		WebElement locate2 = driver.findElement(By.xpath("//div[contains(@id,'headlessui-menu-items')]//following::ul[contains(@class,'jsx')]//a[text()='Rice & Rice Products']"));
		Actions actions_secondmenu = new Actions(driver);
		actions_secondmenu.moveToElement(locate2).perform();
		Thread.sleep(5000);
	//Hover 3rd menu and click		
		WebElement locate3 = driver.findElement(By.xpath("//div[contains(@id,'headlessui-menu-items')]//following::ul[contains(@class,'jsx')]//a[text()='Boiled & Steam Rice']"));
		Thread.sleep(3000);
		locate3.click();
	//In the filter select BB Royal
		Thread.sleep(7000);
		WebElement bbroyal = driver.findElement(By.xpath("//div[@id='side-filter-by-rating']//following::input[@id='i-BBRoyal']"));
		Actions bbroyal_checkbox = new Actions(driver);
		bbroyal_checkbox.moveToElement(bbroyal).perform();
		Thread.sleep(5000);
		bbroyal.click();
	//Select TamilPooni Boiled Rice	
		Thread.sleep(5000);
		WebElement rice = driver.findElement(By.xpath("//div[contains(@class,'break-word')]//h3[text()='Tamil Ponni Boiled Rice']"));
		Actions Pooni_Rice = new Actions(driver);
		Pooni_Rice.moveToElement(rice).perform();
		Thread.sleep(2000);
		rice.click();
	//Window handling
		Set<String> childwindow = driver.getWindowHandles();
	//Convert Set into List
		ArrayList<String> getwindow = new ArrayList<String>(childwindow);
		driver.switchTo().window(getwindow.get(1));
	//Add 5 KG
		Thread.sleep(5000);
		WebElement fivekg = driver.findElement(By.xpath("//div[@class='w-full']//span[text()='5 kg']"));
		Actions fivekg_rice = new Actions(driver);
		fivekg_rice.moveToElement(fivekg).perform();
		Thread.sleep(2000);
		fivekg.click();
	//PRINTING THE PRICE OF 5KG
		String price = driver.findElement(By.xpath("//div[@class='w-full']//span[text()='375']")).getText();
		System.out.println("5KG Rice price is :"+price);
	//Add to Cart
		Thread.sleep(5000);
		WebElement cart = driver.findElement(By.xpath("//section[contains(@class,'StyledSection2-sc-yj3ixq-4')]//following::button[text()='Add to basket']"));
		Actions add_cart = new Actions(driver);
		add_cart.moveToElement(cart).perform();
		Thread.sleep(2000);
		cart.click();

		String riceadded = driver.findElement(By.xpath("//p[@class='mx-4 flex-1']")).getText();
		System.out.println(riceadded);
		
	//Snapshot		
		File scr = driver.getScreenshotAs(OutputType.FILE);
		File trg=new File("./Snapshot/BigBasketSnap1.png");
		FileUtils.copyFile(scr, trg);
		System.out.println("BigBasket Assignment completed");
		
	}

}
