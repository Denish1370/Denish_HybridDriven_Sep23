package testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.ControlActions;
import pages.LoginPage;

public class LoginTest {
	
	@BeforeMethod
	public void setUp() {
		ControlActions.launchBrowser();
	}
	
	@Test
	public void verifyLogin() {
		LoginPage loginPage = new LoginPage();
		loginPage.login("denishsantoki74@gmail.com", "D@d10033517#");
		boolean flag = loginPage.isLoginSuccessFullyDisplayed();
		Assert.assertTrue(flag);
	}
	
	@AfterMethod
	public void tearDown() {
		ControlActions.closeBrowser();
	}
}
