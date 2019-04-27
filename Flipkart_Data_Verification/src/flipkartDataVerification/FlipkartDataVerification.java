package flipkartDataVerification;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(flipkartDataVerification.ListenerTest.class)

public class FlipkartDataVerification {
	
	public static WebDriver driver;
	public static String chromeDriverExecutable = "C:\\Users\\Admin\\Desktop\\New folder\\chromedriver_win32\\chromedriver.exe";
	public static String url = "https://www.flipkart.com/";
	public static String inputSheetName = "Product_Details";
	public static String outputSheetName = "Retreived_Data";
	public static String inputExcelFile = "C:\\Users\\Admin\\Desktop\\New folder\\FilpkartDataVerification\\FlipkartDataVerification.xlsx";
	public static String outputExcelFile = "C:\\Users\\Admin\\Desktop\\New folder\\FilpkartDataVerification\\OutputData.xlsx";	
	public static String userName = "";//Please enter username
	public static String password = "";//Please enter password
	public static String className = "";
	public static String productName = "";
	public static String productPrice = "";
	public static String productRating = "";
	public static String packAge = "";
	public static ArrayList<String> productNamesList = new ArrayList<String>();
	public static ArrayList<String> individualProductList = new ArrayList<String>();
	public static ArrayList<ArrayList<String>> productDetailsList = new ArrayList<ArrayList<String>>();
	

	
@BeforeSuite
public static void initializeChromeDriver(){
	
	System.setProperty("webdriver.chrome.driver", chromeDriverExecutable);
	
	//Instantiate the web driver and load the page
	ChromeOptions options = new ChromeOptions();
	options.setExperimentalOption("useAutomationExtension", false);
	options.addArguments("disable-extensions");
	options.addArguments("start-maximized");
	driver =  new ChromeDriver(options);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.navigate().to(url);
}

@Test(priority = 0)
public void Login() throws Exception {
	//Calling a method to read the excel 
	readExcel();
	WebDriverWait wait = new WebDriverWait(driver, 40);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='_2zrpKA']")));
	
	driver.findElement(By.xpath("//input[@class='_2zrpKA']")).sendKeys(userName);
	if (driver.findElements(By.xpath("//span[text()='CONTINUE']")).size()>0) {
		driver.findElement(By.xpath("//span[text()='CONTINUE']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Login with Password']")).click();
		}
	
	driver.findElement(By.xpath("//input[@class='_2zrpKA _3v41xv']")).sendKeys(password);
	driver.findElement(By.xpath("//button[@class='_2AkmmA _1LctnI _7UHT_c']")).click();
	Thread.sleep(2000);
}

//Search a Product
@Test(priority = 1)
public void searchIDIPadPro() throws Exception{
	WebDriverWait wait = new WebDriverWait(driver, 40);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title='Search for products, brands and more']")));
	for (int i = 0; i < productNamesList.size(); i++) {
		if (productNamesList.get(i).equalsIgnoreCase("ipad pro")) {
			productName = productNamesList.get(i);
		}		
	}
	
	driver.findElement(By.xpath("//input[@title='Search for products, brands and more']")).clear();
	driver.findElement(By.xpath("//input[@title='Search for products, brands and more']")).sendKeys(productName);
	driver.findElement(By.xpath("//button[@class='vh79eN']")).click();
	Thread.sleep(3000);
	productName = driver.findElement(By.xpath("//div[@id='container']/div/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div/a[2]")).getText();
	productRating = driver.findElement(By.xpath("//div[@id='container']/div/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/span/div")).getText();
	productPrice = driver.findElement(By.xpath("//div[@id='container']/div/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div/a[3]/div/div")).getText();
	
	individualProductList.add(productName);
	individualProductList.add(productRating);
	individualProductList.add(productPrice);
	productDetailsList.add(individualProductList);
	individualProductList = new ArrayList<>();
}

//Search a Product
@Test(priority = 1)
public void searchIDIPhoneX() throws Exception{
	WebDriverWait wait = new WebDriverWait(driver, 40);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title='Search for products, brands and more']")));
	for (int i = 0; i < productNamesList.size(); i++) {
		if (productNamesList.get(i).equalsIgnoreCase("IPhone X")) {
			productName = productNamesList.get(i);
		}		
	}
	
	driver.findElement(By.xpath("//input[@title='Search for products, brands and more']")).sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
	driver.findElement(By.xpath("//input[@title='Search for products, brands and more']")).sendKeys(productName);
	driver.findElement(By.xpath("//button[@class='vh79eN']")).click();
	Thread.sleep(3000);
	productName = driver.findElement(By.xpath("//div[@id='container']/div/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/div/div/a[1]/div[2]/div[1]/div[1]")).getText();
	productRating = driver.findElement(By.xpath("//div[@id='container']/div/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/div/div/a[1]/div[2]/div[1]/div[2]/span[1]/div")).getText();
	productPrice = driver.findElement(By.xpath("//div[@id='container']/div/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/div/div/a[1]/div[2]/div[2]/div[1]/div/div[1]")).getText();
	
	individualProductList.add(productName);
	individualProductList.add(productRating);
	individualProductList.add(productPrice);
	productDetailsList.add(individualProductList);
	individualProductList = new ArrayList<>();
}

//Search a Product
@Test(priority = 1)
public void searchIDOnePlus6T(){
	WebDriverWait wait = new WebDriverWait(driver, 40);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@title='Search for products, brands and more']")));
	for (int i = 0; i < productNamesList.size(); i++) {
		if (productNamesList.get(i).equalsIgnoreCase("One Plus 6T")) {
			productName = productNamesList.get(i);
		}		
	}
	
	driver.findElement(By.xpath("//input[@title='Search for products, brands and more']")).sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
	driver.findElement(By.xpath("//input[@title='Search for products, brands and more']")).sendKeys(productName);
	driver.findElement(By.xpath("//button[@class='vh79eN']")).click();
	
	productName = driver.findElement(By.xpath("//div[@id='container']/div/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div/a[2]")).getText();
	productRating = driver.findElement(By.xpath("//div[@id='container']/div/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div/div[2]/span[1]/div")).getText();
	productPrice = driver.findElement(By.xpath("//div[@id='container']/div/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/div/a[3]/div/div[1]")).getText();
	
	individualProductList.add(productName);
	individualProductList.add(productRating);
	individualProductList.add(productPrice);
	productDetailsList.add(individualProductList);
	//Calling the write excel method
	
	writeExcel();
}

public void readExcel(){
	try{
		
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File(inputExcelFile)));
		XSSFSheet sheet = wb.getSheet(inputSheetName);
		Iterator<Row> i = sheet.iterator();
		while (i.hasNext()) {
			Row row = i.next();
			Iterator<Cell> j = row.cellIterator();
			while (j.hasNext()) {
				Cell cell = j.next();
				//Adding Product details to list
				productNamesList.add(cell.getStringCellValue());
				//System.out.println(cell.getStringCellValue());
			}
			
		}
		
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	
}

public void writeExcel(){
	try{
		FileInputStream fis = new FileInputStream(outputExcelFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(outputSheetName);
		int rowNum = 0;
		System.out.println(productDetailsList);
		for (int i = 0; i < productDetailsList.size(); i++) {
			Row row = sheet.createRow(rowNum++);
			int cellNum = 0;
			for (int j = 0; j < productDetailsList.get(i).size(); j++) {
				Cell cell = row.createCell(cellNum++);
				cell.setCellValue(cell.CELL_TYPE_STRING);
				cell.setCellValue(productDetailsList.get(i).get(j));
				//System.out.println(productDetailsList.get(i).get(j)); 
				
			}
		}
		FileOutputStream fos = new FileOutputStream(outputExcelFile);
		wb.write(fos);
		fos.close();
		
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	
}

@AfterSuite
public void closeWebDriver(){
	driver.quit();
}
}
