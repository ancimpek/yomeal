package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends BasicTest {
	
	public String locationName = "City Center - Albany";
	String msgNumberErr = "[ERROR] Number of meals does not match";
	String msgNameErr = "[ERROR] Name of the meal does not match";

	@Test(priority = 0)
	public void searchResults() throws InterruptedException, IOException {

		driver.get(baseUrl + "meals");
		locationPopupPage.setLocation(locationName);
		
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Meal Search Results");
		
		Thread.sleep(5000);

		for (int i = 1; i < 7; i++) {
			String url = sheet.getRow(i).getCell(1).getStringCellValue();
			driver.get(url);

			String location = sheet.getRow(i).getCell(0).getStringCellValue();
			locationPopupPage.openPopUp();
			locationPopupPage.setLocation(location);
			Thread.sleep(2000);

			int numberOfResults = (int) sheet.getRow(i).getCell(2).getNumericCellValue();
			int pageNumberResults = searchResultPage.getMealNumber();
			Thread.sleep(2000);
			
			Assert.assertEquals(pageNumberResults, numberOfResults, msgNumberErr);

			for (int j = 3; j < numberOfResults + 3; j++) {
				String mealName = sheet.getRow(i).getCell(j).getStringCellValue();
				String pageMealName = searchResultPage.getMealNames().get(j-3);

				SoftAssert softAssert = new SoftAssert();
				softAssert.assertTrue(pageMealName.contains(mealName), msgNameErr);
			}

		}
		
		workbook.close();
	}
}