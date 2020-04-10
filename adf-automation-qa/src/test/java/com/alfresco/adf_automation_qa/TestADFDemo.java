package com.alfresco.adf_automation_qa;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestADFDemo {
	@Parameters({"browserName","url"})
	@BeforeTest
	public void init(String browser, String baseurl) {
		BaseClass2 base = new BaseClass2();
		base.initBrowser(browser, baseurl);
	}

	@Parameters({"userName","password"})
	@Test(priority = 1)
	public void loginInECM(String userName, String password) throws InterruptedException {
		SettingPage setting = new SettingPage();
		setting.selectESM();
		LoginPage login = new LoginPage();
		Assert.assertEquals(login.isAlfrescoLogoDisplayed(), true, "ESM selected successfully");
		login.login(userName, password);
		HomePage home = new HomePage();
		Assert.assertEquals(home.isHomePageLogoDisplayed(), true, "Login successfully");
	}


	@Test(priority = 3)
	public void createFolder() throws InterruptedException {
		HomePage home = new HomePage();
		Assert.assertEquals(home.createNewFolder("Content Services","kalpesh-dixit-ness"), true, "Folder is created Successfully");
	}

	@Test(priority = 4)
	public void createDuplicateFolder() throws InterruptedException {
		HomePage home = new HomePage();
		Assert.assertEquals(home.createDuplicateFolder("kalpesh-dixit-ness"), true,
				"Error message: There's already a folder with this name. Try a different name.");
	}

	@Test(priority = 4)
	public void deleteFolder() throws InterruptedException {
		HomePage home = new HomePage();
		Assert.assertEquals(home.deleteFolder("kalpesh-dixit-ness"), true,
				"Message Displayed: kalpesh-dixit-ness deleted");
	}
	
	@AfterTest
	public void quit(){
		BaseClass2 base = new BaseClass2();
		base.closeApp();
	}


}
