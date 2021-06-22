package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage{
	
	public Actions action;

	public ProfilePage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
		this.action = new Actions (driver);
	}
	
	public WebElement getFirstName(){
		return driver.findElement(By.name("user_first_name"));
	}
	
	public WebElement getLasttName(){
		return driver.findElement(By.name("user_last_name"));
	}
	
	public WebElement getAddress(){
		return driver.findElement(By.name("user_address"));
	}
	
	public WebElement getPhoneNumber(){
		return driver.findElement(By.name("user_phone"));
	}
	
	public WebElement getZipCode(){
		return driver.findElement(By.name("user_zip"));
	}
	
	public Select getCountry() {
		Select selectCountry = new Select (driver.findElement(By.id("user_country_id")));
		return selectCountry;
	} 
	
	public Select getState() {
		Select selectState = new Select (driver.findElement(By.id("user_state_id")));
		return selectState;
	} 
	
	public Select getCity() {
		Select selectCity = new Select (driver.findElement(By.id("user_city")));
		return selectCity;
	} 
	
	public WebElement getSaveBtn() {
		return driver.findElement(By.name("btn_submit"));
	}
	
	public WebElement getAvatar() {
		return driver.findElement(By.className("avatar"));
	}
	
	public WebElement getUploadBtn() {
		return driver.findElement(By.xpath("//*[@title='Uplaod']"));
	} 
	
	public WebElement getUploadFile() {
		return driver.findElement(By.xpath("//input[@type='file']"));
	}
	
	public WebElement getRemoveBtn() {
		return driver.findElement(By.xpath("//*[@title='Remove']"));
	}
	
	public void clickOnUploadBtn() {
		js.executeScript("arguments[0].click();", this.getUploadBtn());
	}
	
	public void uploadImg(String path) throws InterruptedException {
		action.moveToElement(getAvatar()).build().perform();
		Thread.sleep(500);
		this.getUploadBtn().click();
		this.getUploadFile().sendKeys(path);
	}
	
	public void removeImg() {
		action.moveToElement(getAvatar());
		js.executeScript("arguments[0].click();", this.getRemoveBtn());
	}
	
	public void updateProfileInfo(
			String firstName,
			String lastName,
			String address,
			String phoneNumber,
			String zipCode,
			String country,
			String state,
			String city) throws InterruptedException {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);
		this.getLasttName().clear();
		this.getLasttName().sendKeys(lastName);
		this.getAddress().clear();
		this.getAddress().sendKeys(address);
		this.getPhoneNumber().clear();
		this.getPhoneNumber().sendKeys(phoneNumber);
		this.getZipCode().clear();
		this.getZipCode().sendKeys(zipCode);
		this.getCountry().selectByVisibleText(country);
		Thread.sleep(500);
		this.getState().selectByVisibleText(state);
		Thread.sleep(500);
		this.getCity().selectByVisibleText(city);
		Thread.sleep(500);
		this.getSaveBtn().click();
		Thread.sleep(500);
	}
}
