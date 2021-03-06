package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage {

	public NotificationSystemPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public WebElement getMessageElement() {
		return driver.findElement(By.xpath(
				"//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}

	public String getMesssageTxt() {
		return this.getMessageElement().getText();
	}

	public void waitUntilMessageDisappears() {
		WebDriverWait waiter = new WebDriverWait(driver, 30);
		waiter.until(ExpectedConditions.attributeContains(this.getMessageElement(), "style",
				"display: none;"));
	}
}