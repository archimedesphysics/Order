package com.weborder;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {


			
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"/home/physics/Documents/selenium dependencies/drivers/chromedriver");
		WebDriver driver= new ChromeDriver();
		
//		Open browser
//		Go to url http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx (Links to an external site.)Links to an external site.

		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
//		Login using username Tester  and password test 
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
//		Click on Order --submit
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		driver.findElement(By.linkText("Order")).click();
		String a= String.valueOf(randomNum());
//		Enter a random quantity between 1 and 100 -id ctl00$MainContent$fmwOrder$txtQuantity
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),a);
		
		
//		Enter Customer Name: John <middle_name> Smith. Instead of <middle_name> your code should enter a random string every time.
		//-
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("John " + randomIdentifier() + " Smith" );
		
//		Enter street: 123 Any st -id 
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("123 Any st" );
		
		
//		Enter City: Anytown -id ctl00$MainContent$fmwOrder$TextBox3
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Anytown" );
		
//		Enter State: Virginia id- ctl00$MainContent$fmwOrder$TextBox4
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("Virginia" );
		
		
//		Enter a random 5 digit number to the zip code field -id ctl00$MainContent$fmwOrder$TextBox5
		
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(zipCode() );
		
//		Select any card type. Every time your code should select a different type.
		final int cardType= getRandomInteger(1,3); 
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@name='ctl00$MainContent$fmwOrder$cardList'])["+cardType+"]")).click();
		
//		Enter any card number. If you selected Visa, card number should start with 4. If you selected Master, card number should start with 5. If you selected American Express, card number should start with 3. New card number should be auto generated every time you run the test. Card numbers should be 16 digits for Visa and Master, 15 for American Express.
		// -ctl00$MainContent$fmwOrder$TextBox6
		switch(cardType) {
		case 1: driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("4"+randomCard(16) ); break; 
		case 2: driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("5"+randomCard(16)  ); break;
		case 3: driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("3"+randomCard(15)  ); break;
		}
			
		
		//		Enter any valid expiration date - id
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("05/23" );
		
//		Click on Process
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		
//		Verify than the page contains text New order has been successfully added.
//		 if( driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder > tbody > tr > td > div > strong")).isDisplayed()){
//				System.out.println("Verified");
//		}else{
//				System.out.println("unVerified");
//		}
	    
	  //*[@id="ctl00_MainContent_fmwOrder"]/tbody/tr/td/div/strong
	    
	}
	
		public static int randomNum() {
			
			Random rn = new Random();
			int random = rn.nextInt(100);
			return  random; 
			
		
			
		}
		
		public static String zipCode() {
			
			Random rn = new Random();
			int random = rn.nextInt(10);
			int randomfirst=rn.nextInt(9)+1; 
			String zipcode=""+randomfirst+random+random+random+random; 
			return  zipcode; 
			
		
			
		}	
		public static String randomCard(int num) {
			
			Random rn = new Random();
			String card=""; 
			for (int i = 0; i < num-1; i++) {
				int random = rn.nextInt(10); 
			    card=card+random;  
				
			}
			
			return  card; 
			
		
			
		}
		

		public static String randomIdentifier() {
			
			final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			Random rn = new Random();
			int random1 = rn.nextInt(24);
			
		    
		    return lexicon.charAt(random1)+"";
		}
		
		private static int getRandomInteger(int min, int max){
			
		    return ThreadLocalRandom.current().nextInt(min, max+1);
		}
	
	
}
