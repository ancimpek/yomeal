package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage{

	public SearchResultPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public List<WebElement> getSearchResults() {
		return this.driver.findElements(By.xpath("//*[@class='product-name']/a"));
	}

	public ArrayList<String> getMealNames() {
		ArrayList<String> mealNames = new ArrayList<String>();
		for (int i = 0; i < this.getSearchResults().size(); i++) {
			mealNames.add(this.getSearchResults().get(i).getText());
		}
		return mealNames;
	}

	public int getMealNumber() {
		return this.getSearchResults().size();
	}
}
	

