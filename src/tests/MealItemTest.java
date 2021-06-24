package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest{
	
	public int quantity = 4;
	public String msgLocation1 = "The Following Errors Occurred:";
	public String msgLocation2 = "Please Select Location";
	public String msgLocationErr = "[ERROR] No message shown";
	public String locationName = "City Center - Albany";
	public String msgCart = "Meal Added To Cart";
	public String msgCartErr = "[ERROR] Adding to cart was unsuccessful";
	public String msgLogin = "Please login first!";
	public String msgLoginErr = "[ERROR] Login message is not displayed";
	public String msgFavorite = "Product has been added to your favorites.";
	public String msgFavoriteErr = "[ERROR] Product has not been added to your favorites.";
	public String msgRemoveMeals =  "All meals removed from Cart successfully";
	public String msgRemoveMealsErr = "[ERROR] Meals have not been removed";


	@Test(priority = 0)
	public void addMealToCartTest() throws InterruptedException {
		
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closePopUp();
		mealPage.addMealToCart(quantity);
		
		Thread.sleep(500);
		
		Assert.assertTrue(notificationSystemPage.getMesssageTxt().contains(msgLocation1), msgLocationErr);
		Assert.assertTrue(notificationSystemPage.getMesssageTxt().contains(msgLocation2), msgLocationErr);
		notificationSystemPage.waitUntilMessageDisappears();
		
		locationPopupPage.openPopUp();
		locationPopupPage.setLocation(locationName);
		
		Thread.sleep(500);
		
		mealPage.addMealToCart(quantity);
		Assert.assertTrue(notificationSystemPage.getMesssageTxt().contains(msgCart), msgCartErr);
	}
	
	@Test(priority = 1)
	public void addMealToFavorite() throws InterruptedException {
		
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closePopUp();
		mealPage.addToFavourite();
		Assert.assertTrue(notificationSystemPage.getMesssageTxt().contains(msgLogin), msgLoginErr);
		
		driver.get(baseUrl + "guest-user/login-form");
		loginPage.login(email, password);
		
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPage.addToFavourite();
		
		Thread.sleep(500);
		
		Assert.assertTrue(notificationSystemPage.getMesssageTxt().contains(msgFavorite), msgFavoriteErr);
		notificationSystemPage.waitUntilMessageDisappears();
	}
	
	@Test(priority = 2)
	public void clearCartTest() throws InterruptedException, IOException {
		
		driver.get(baseUrl + "meals");
		locationPopupPage.setLocation(locationName);
		
		File file = new File("data/Data.xlsx");	
		FileInputStream fis = new FileInputStream(file);	
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Meals");
		
		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			String mealUrl = sheet.getRow(i).getCell(0).getStringCellValue();
			int quantity = (int) sheet.getRow(i).getCell(1).getNumericCellValue();
			
			driver.get(mealUrl);
			mealPage.addMealToCart(quantity);
			
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(notificationSystemPage.getMesssageTxt().contains(msgCart), msgCartErr);
		}
		
		cartSummaryPage.cartClearAll();
		Assert.assertTrue(notificationSystemPage.getMesssageTxt().contains(msgRemoveMeals), msgRemoveMealsErr);
		
	}
	
	
	
}
