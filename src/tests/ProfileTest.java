package tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest{
	
	String firstName = "Joshua";
	String lastName = "Vannote";
	String address = "4396 Wildrose Lane";
	String phoneNumber = "313-689-3508";
	String zipCode = "48226";
	String country = "United States";
	String state = "Michigan";
	String city = "Detroit";

	String loginMsg = "Login Successfull";
	String loginMsgFail = "[ERROR] Unexpected login message";
	String setupMsg = "Setup Successful";
	String setupMsgFail = " [ERROR] Unexpected setup message";
	String logoutMsg = "Logout Successfull!";
	String logoutMsgFail = " [ERROR] Unexpected logout message";
	String imgMsg = "Profile Image Uploaded Successfully";
	String imgMsgFail = " [ERROR] Image Upload failed";
	String imgDelete = "Profile Image Deleted Successfully";
	String imgDeleteFail = "Profile Image Deleted Failed";

	@Test(priority = 0)
	public void editProfileTest() throws InterruptedException {
		
		driver.get(baseUrl + "guest-user/login-form");
		locationPopupPage.closePopUp();
		loginPage.login(email, password);
		Assert.assertTrue(notificationSystemPage.getMesssageTxt().contains(loginMsg), loginMsgFail);
		
		driver.get(baseUrl + "member/profile"); 
		profilePage.setProfileInfo(firstName, lastName, address, phoneNumber, zipCode, country, state, city);
		Assert.assertTrue(notificationSystemPage.getMesssageTxt().contains(setupMsg), setupMsgFail);
		notificationSystemPage.waitUntilMessageDisappears();
		
		authPage.logout();
		Assert.assertTrue(notificationSystemPage.getMesssageTxt().contains(logoutMsg), logoutMsgFail);	
	}
	
	@Test(priority = 1)
	public void changeImgTest() throws IOException, InterruptedException {
		
		driver.get(baseUrl + "guest-user/login-form");
		locationPopupPage.closePopUp();
		loginPage.login(email, password);
		Assert.assertTrue(notificationSystemPage.getMesssageTxt().contains(loginMsg), loginMsgFail);
		
		driver.get(baseUrl + "member/profile");
		String imgPath = new File("C:\\Users\\Zoran\\Desktop\\yomeal\\yomeal\\img\\image.jpg").getCanonicalPath();
		profilePage.uploadImg(imgPath);
		Assert.assertTrue(notificationSystemPage.getMesssageTxt().contains(imgMsg), imgMsgFail);
		notificationSystemPage.waitUntilMessageDisappears();
		
		profilePage.removeImg();
		Assert.assertTrue(notificationSystemPage.getMesssageTxt().contains(imgDelete), imgDeleteFail);
		notificationSystemPage.waitUntilMessageDisappears();
		
		authPage.logout();
		Assert.assertTrue(notificationSystemPage.getMesssageTxt().contains(logoutMsg), logoutMsgFail);
		notificationSystemPage.waitUntilMessageDisappears();
	}

}
