package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage{

	public LocationPopupPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}
	
	public WebElement getSelectLocation() {
		return driver.findElement(By.xpath("//*[@class='location-selector']"));
	}
	
	public WebElement getCloseElement() {
		return this.driver.findElement(By.xpath("//*[@class='close-btn close-btn-white']"));
	}
	
	public WebElement getKeyword() {
		return driver.findElement(By.id("locality_keyword"));
	}
	
	public WebElement getLocationItem(String locationName) {
		return driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}
	
	public WebElement getLocationInput() {
		return this.driver.findElement(By.id("location_id"));
	}
	
	public WebElement getSubmit() {
		return this.driver.findElement(By.name("btn_submit"));
	}
	
	public void getLocationForm() {
		this.getSelectLocation().click();
	}
	
	public void openPopUp() {
		this.getSelectLocation().click();
	}
	
	public void setLocation(String locationName) throws InterruptedException {
		this.getKeyword().click();
		Thread.sleep(500);
		String dataValue = this.getLocationItem(locationName).getAttribute("data-value");
		Thread.sleep(500);
		js.executeScript("arguments[0].value=arguments[1]", this.getLocationInput(), dataValue);
		Thread.sleep(500);
		js.executeScript("arguments[0].click()", this.getSubmit());
	}
	
	public void closePopUp() {
		this.getCloseElement().click();
	}
	

}
