package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage{

	public MealPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}
	
	public WebElement getQuantity() {
		return driver.findElement(By.name("product_qty"));
	}
	
	public WebElement getAddToCart() {
		return driver.findElement(By.linkText("Add To Cart"));
	}
	
	public WebElement getFavourite() {
		return this.driver.findElement(By.id("item_119"));
	}

	public void addToFavourite() {
		this.getFavourite().click();
	}
	
	public void addMealToCart(int quantity) {
		String qty = Integer.toString(quantity);
		this.getQuantity().sendKeys(Keys.CONTROL, "a");
		this.getQuantity().sendKeys(Keys.DELETE);
		this.getQuantity().sendKeys(qty);
		js.executeScript("arguments[0].click()", this.getAddToCart());
	}

}
