package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage{

	public LoginPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public WebElement getUsername() {
		return driver.findElement(By.xpath("//*[@name='username']"));
	}
	
	public WebElement getPassword() {
		return driver.findElement(By.xpath("//*[@name='password']"));
	}
	
	public WebElement getLoginBtn() {
		return driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}
	
	public void login(String username, String password) {
		this.getUsername().clear();
		this.getUsername().sendKeys(username);
		this.getPassword().clear();
		this.getPassword().sendKeys(password);
		this.getLoginBtn().click();
	}
}
