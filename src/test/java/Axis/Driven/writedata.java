package Axis.Driven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class writedata {

	WebDriver driver;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFCell cell;
	
	
	@Test
	public void fblogin() throws IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dikshant\\Documents\\Manipal\\chromedriver-win64\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		
		File src = new File("C:\\Users\\Dikshant\\Desktop\\AXIS\\Java\\Driven\\TestData.xlsx");
	
		FileInputStream fis = new FileInputStream(src);
		
		
		workbook = new XSSFWorkbook(fis);
		
		
		sheet = workbook.getSheetAt(0);
		
		for (int i = 1; i<= sheet.getLastRowNum();i++ ) {
		
		//import 
		
		cell = sheet.getRow(i).getCell(0);
		driver.findElement(By.xpath("//input[@name = 'email']")).clear();
		driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys(cell.getStringCellValue());

		// import the data for the password 

		cell = sheet.getRow(i).getCell(1);
		driver.findElement(By.xpath("//input[@id = 'pass']")).clear();
		driver.findElement(By.xpath("//input[@id = 'pass']")).sendKeys(cell.getStringCellValue());
		String title = driver.getTitle();
		
		System.out.println(title);
		
		FileOutputStream fos = new FileOutputStream(src);
		
		sheet.getRow(i).createCell(2).setCellValue(title);
		
		workbook.write(fos);
		fos.close();

		}
		
	}
	
	
	
	
}
