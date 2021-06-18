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
		return driver.findElement(By.xpath("//*[@class='location-selector']/a"));
	}
	
	public WebElement getCloseElement() {
		return driver.findElement(By.xpath("//*[@id='location-popup']/div/div/div/div/a"));
	}
	
	public WebElement getKeyword() {
		return driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}
	
	public WebElement getLocationItem(String locationName) {
		return driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}
	
	public WebElement getLocationInput() {
		return driver.findElement(By.xpath("//*[@id='location_id']"));
	}
	
	public WebElement getSubmit() {
		return driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}
	
	public void openSelectLocation() {
		this.getSelectLocation().click();
	}
	
	public void setLocation(String locationName) {
		this.getKeyword().click();
		String dataValue = this.getLocationItem(locationName).getAttribute("data-value");
		js.executeScript("arguments[0].value=arguments[1]", this.getLocationInput(), dataValue);
		js.executeScript("arguments[0].click()", this.getSubmit());
	}
	
	public void closePopUpDialog() {
		this.getCloseElement().click();
	}
	

}
