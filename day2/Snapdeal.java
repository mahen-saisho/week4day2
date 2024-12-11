package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

public class Snapdeal {

    public static void main(String[] args) throws InterruptedException, IOException {
        // Set up the ChromeDriver
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.snapdeal.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[@class='catText' and contains(text(),'Men')]")).click();//Select Men's Fashion
        driver.findElement(By.xpath("(//span[text()='Sports Shoes'])[1]")).click(); //Select Sports Shoe
        
   //Find number of shoe count in the page
        List<WebElement> shoe_count = driver.findElements(By.xpath("//div[@id='products']//p"));
        ArrayList<String> list = new ArrayList<String>();
  //For Looping
        for(WebElement shoe: shoe_count) {
        	list.add(shoe.getText());
        }
        System.out.println("Shoe count: " + list.size());
        Thread.sleep(5000);
    //sort dropdown
        driver.findElement(By.xpath("//div[@class='sort-selected']")).click(); 
        Thread.sleep(3000);
        driver.findElement(By.xpath("//ul[@class='sort-value']//li[2]")).click(); //select low to high price
        
        Thread.sleep(3000);
        List<WebElement> low_to_high_price = driver.findElements(By.xpath("//span[contains(@id,'display-price')]")); //check the sort price
        ArrayList<String> price_list = new ArrayList<String>();
        
        for(WebElement price: low_to_high_price) {
        	String priceValue=price.getAttribute("data-price");
        	if (priceValue != null) { // Check if the attribute exists
                price_list.add(priceValue); // Add the price to the list
                //System.out.println(price_list);
                //System.out.println("Individual Price Value: "+priceValue);
            }
        }
    //Sort order checking
        List<String> sortlist = new ArrayList<String>(price_list);
        Collections.sort(sortlist);
        if(price_list.equals(sortlist)) {
        	System.out.println("Price is in sorting order:"+sortlist);
        }else {
        	System.out.println("Not in sorted order");
        }
        WebElement fromInput = driver.findElement(By.name("fromVal"));
     // Clear the input field 
        fromInput.clear();
     // Enter the input
        fromInput.sendKeys("300");
        
        WebElement ToInput = driver.findElement(By.name("toVal"));
        // Clear the input field 
        ToInput.clear();
        // Enter the input
        ToInput.sendKeys("500");
       
        
        driver.findElement(By.xpath("(//input[@name='toVal']//following::div)[1]")).click();
        Thread.sleep(5000);
    //Filter applied verification
        String verify_from = fromInput.getAttribute("value");
        if("300".equals(verify_from)) {
        	System.out.println("From filter is applied");
        }else {
        	System.out.println("From filter not applied");
        }
        String verify_to = ToInput.getAttribute("value");
        if("500".equals(verify_to)) {
        	System.out.println("To Filter is applied");
        }else {
        	System.out.println("To filter not applied");
        }
    //Select Color  
        Thread.sleep(3000);
        WebElement color = driver.findElement(By.xpath("(//button[text()='View More '])[1]"));
        Actions select_black = new Actions(driver);
        select_black.moveToElement(color).perform();
        Thread.sleep(2000);
        WebElement color_black=driver.findElement(By.xpath("//span[@class='filter-color-tile Black ']"));
        color_black.click();
    //Filter color check
        String color_verify = color.getAttribute("value");
        String color_text = driver.findElement(By.xpath("//a[text()=' Black']")).getText();
        if(color.equals(color_verify)) {
        	System.out.println("Filter color is verified");
        }else {
        	System.out.println("Color Filter is not verified");
        }
        Thread.sleep(5000);
        WebElement first_shoe = driver.findElement(By.xpath("(//div[@class='clearfix row-disc'])[1]"));
        Actions hover_first_shoe = new Actions(driver);
        hover_first_shoe.moveToElement(first_shoe).perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//div[@class='clearfix row-disc'])[1]/div")).click();
     //Price and Amount   
        WebElement price_discount = driver.findElement(By.xpath("//div[contains(@class,'pdp-e-i-PAY-l')]"));
        String amount_discount = price_discount.getText();
        System.out.println(amount_discount);
        
    //Snapshot		
		File scr = driver.getScreenshotAs(OutputType.FILE);
		File trg=new File("./Snapshot/SnapdealSnap1.png");
		FileUtils.copyFile(scr, trg);
		System.out.println("SnapDeal Assignment completed");
        
		
        
      
        
        
//    //Convert Sting to Int
//        ArrayList<Integer> price_list_value = new ArrayList<Integer>();
//        ArrayList<WebElement> product_in_range = new ArrayList<WebElement>();
//        
//        for (int i = 0; i < price_list.size(); i++) {
//            String price = price_list.get(i);
//            price = price.replaceAll("[^\\d]", ""); // Keep only digits
//            int priceInt = Integer.parseInt(price); //convert string to int value
//            price_list_value.add(priceInt); // converted value is added in Arrylist integer
//
//   // Check if the price is between 300 and 500 and store the corresponding WebElement (product)
//            if (priceInt >= 300 && priceInt <= 500) {
//                product_in_range.add(low_to_high_price.get(i)); // Store the product in the WebElement
//            }
//        }
//        if (!product_in_range.isEmpty()) {
//            WebElement selectedProduct = product_in_range.get(0); // Select the first product
//            selectedProduct.click(); // Click on the first product
//            System.out.println("Clicked on the product with price: " + price_list_value.get(product_in_range.indexOf(selectedProduct)));
//        } else {
//            System.out.println("No product found in the price range of 300 to 500.");
//        }
        
       
  
        	
        	
        }
           
 
    }
