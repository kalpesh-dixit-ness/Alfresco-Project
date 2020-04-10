package com.alfresco.adf_automation_qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utils;


public class LoginPage extends BaseClass2{
	
	@FindBy(id = "username")
	private WebElement txtUserName;

	@FindBy(id = "password")
	private WebElement txtPassword;
	
	@FindBy(id = "login-button")
	private WebElement bttnLogin;
	
	@FindBy(id = "adf-login-img-logo")
	private WebElement logoAlfresco;

	

	LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public boolean isAlfrescoLogoDisplayed(){
		log.info("Verifying if Alfresco logo is dislayed");
		Utils.waitForClickability(logoAlfresco);
		return isElementDisplayed(logoAlfresco);
	}
		
	public void login(String userName, String password) throws InterruptedException {
		log.info("Entering Username:"+userName);
		try {
			Utils.waitForClickability(txtUserName);
			txtUserName.sendKeys(userName);
			log.info("Entering Password:"+password);
			txtPassword.sendKeys(password);
			log.info("Clicking Login Button");
			Utils.waitForClickability(bttnLogin);
			bttnLogin.click();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

}
