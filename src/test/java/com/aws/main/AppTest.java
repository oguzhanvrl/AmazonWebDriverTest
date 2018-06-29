package com.aws.main;

import java.util.List;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest{
		
	public WebDriver driver;
	public int listCount=0;
	public WebElement myList;
	public String[] productAddList = new String[] {"IPhone 8", "Amazon Kindle"}; // oluşturulan listeye eklenecek örnek ürünler
	
	public String newListName = "github"; //---- yeni oluşturulacak liste adını değiştirebilirsiniz.
	
	@BeforeTest
	public void Start(){
		System.setProperty("webdriver.chrome.driver", "C:/Users/MONSTER/Desktop/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Test Başladı ...");
	}
	

	
	@BeforeMethod
	public void BeforeMethod(){
		 System.out.println("Öncelik sırasına göre > Sıradaki adıma geçildi ...");
	}
	
	@AfterMethod
	public void AfterMethod(){
		System.out.println("Öncelik sırasına göre > Sıradaki adımda işlemler tamamlandı !");
	}
	
	
	
	

	@Test(priority=1)
	public void getAmazonWS() throws InterruptedException {	 
		try {
			  driver.get("http://www.amazon.com/");  
			  System.out.println("Test1:Amazon.com 'a gidildi.");
			  Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		 	  	  
	}
	
	@Test(priority=2,dependsOnMethods={"getAmazonWS"})
	public void Register() throws InterruptedException{
		try {			
			WebElement element = driver.findElement(By.xpath("//*[@id='nav-link-accountList']"));		   
		    Actions action = new Actions(driver);
		    action.moveToElement(element).build().perform();
			
			driver.findElement(By.xpath("//*[@id='nav-flyout-ya-newCust']/a")).click();
			System.out.println("Test2:Kayıt Ol 'a gidildi.");
			Thread.sleep(100);
			
			WebElement name =driver.findElement(By.xpath("//*[@id='ap_customer_name']"));
			name.sendKeys("oguzhanvarol");

			WebElement mail =driver.findElement(By.xpath("//*[@id='ap_email']"));
			mail.sendKeys("oguzhannvarol@gmail.com");
			
			WebElement pass =driver.findElement(By.xpath("//*[@id='ap_password']"));
			pass.sendKeys("123456789");
			
			WebElement rePass =driver.findElement(By.xpath("//*[@id='ap_password_check']"));
			rePass.sendKeys("123456789");
			
			driver.findElement(By.xpath("//*[@id='continue']")).click();
			
			Thread.sleep(100);
			driver.findElement(By.xpath("//*[@id='authportal-main-section']/div[2]/div/div[2]/div[1]/div[1]/a")).click();
			System.out.println("Test2:Kullanıcı bilgi girişi tamamlandı.");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	//Aslında KUllanıcı Kayıt ol kısmından girişyapa geçerken şöyle yapılabilir,
	//kullanıcının kayıt olmak istediği email sistemde var ise
	//Amazon API ile kontrol işlemi yapılır ve -> Zaten hesap mevcut diiyp girişyap test metotuda geçilebilir.
	
	@Test(priority=3,dependsOnMethods={"Register"})
	public void Login() throws InterruptedException{
		try {
			
			WebElement mail =driver.findElement(By.xpath("//*[@id='ap_email']"));
			mail.sendKeys("oguzhannvarol@gmail.com"); // Amazon kullancı adı girişi
			driver.findElement(By.xpath("//*[@id='continue']")).click();
			
			WebElement pass =driver.findElement(By.xpath("//*[@id='ap_password']"));
			pass.sendKeys("123456789"); // Amazon şifre girişi
			driver.findElement(By.xpath("//*[@id='signInSubmit']")).click();
			
			System.out.println("Test3:Başarıyla Giriş Yapıldı.");	
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Test(priority=4,dependsOnMethods={"Login"})
	public void CreateList() throws InterruptedException{
		try {
			
		    WebElement element = driver.findElement(By.xpath("//*[@id='nav-link-accountList']"));		   
	        Actions action = new Actions(driver);
	        action.moveToElement(element).build().perform();
	 
	        driver.findElement(By.xpath("//*[@id='nav-al-wishlist']/a[1]")).click();
		
	        WebElement listName =driver.findElement(By.xpath("//*[@id='WLNEW_list_name']"));
	        //listCount++;
	        listName.sendKeys(newListName);  // Shopping List atarLabs
	        Thread.sleep(500);
	        driver.findElement(By.xpath("//*[@id='WLNEW_privacy_public']")).click();
	        Thread.sleep(100);
	        driver.findElement(By.xpath("//*[@id='a-popover-content-3']/form/div[2]/span[3]/span/span/input")).click();  
	        System.out.println("Test4:Başarıyla Yeni Liste Oluşturuldu...");
	        driver.get("http://www.amazon.com/"); 
			
		} catch (Exception e) {
			// TODO: handle exception
		}
			
	      
	}
	
	
	
    // @Parameters("_product")
	// public void ProductAddList(String _product)  
	//Parametre olarak ürün ismi gönderilip tekbir method da listemize birden fazla ürün ekleyebilirdik
	
	
	@Test(priority=5,dependsOnMethods={"CreateList"})
	public void ProductAddList() throws InterruptedException{
		
		try {
			
			  System.out.println("Test5: 2 ürün için listeye ekleme işlemi başladı");
			  for (String _product : productAddList) {
				  
				  WebElement searchBox = driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']"));
				  //searchBox.clear();
				  searchBox.sendKeys(_product); //_product
				  Thread.sleep(100);
				  searchBox.submit(); 
				  driver.findElement(By.xpath("//*[@id='result_6']")).click();
				  driver.findElement(By.xpath("//*[@id='result_6']/div/div/div/div[2]/div[1]/div[1]/a")).click();
				  driver.findElement(By.xpath("//*[@id='wishListDropDown']/span")).click();
				  Thread.sleep(100);
				  WebElement shopItem= driver.findElement(By.xpath("//*[@id='atwl-popover-inner']/ul")); //*[@id="a-popover-content-10"]
				  List<WebElement> shoppingList=shopItem.findElements(By.tagName("li"));
				  Thread.sleep(100);
				  System.out.println("Test5:---> Amazon Listeniz :");
				  for (WebElement li : shoppingList) {			 
					  
					  //System.out.println("-> "+li.findElement(By.tagName("span")).getText()); AMAZON ALIŞVERİŞ LİSTELERİNİZ
					  
					  if( li.findElement(By.tagName("span")).getText().startsWith("Shopping List"+newListName)){
						   myList = li.findElement(By.tagName("span"));				   
						   System.out.println("Test5:--> "+myList.getText());
					   } 
					  
					 // Assert.assertTrue((li.findElement(By.tagName("span")).getText().startsWith("Shopping List 12son"))!=false,"asd");
					  //if yerine Assert ile kontrol yapabiliriz
					  				      
				  }		
				  Thread.sleep(100);
				  myList.click();
				  driver.get("http://www.amazon.com/");  
				  System.out.println(_product + "Listeye Eklendi");			
			}
			  System.out.println("Test5:------> Ürünler Amazon Alışveriş Listenize Eklendi ...");
			  driver.get("http://www.amazon.com/"); 
			  Thread.sleep(100);
			  
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	@After
	public void Disconnect(){
		driver.quit();
		System.out.println("Test Sonlandı ! ");
	}
	
}
