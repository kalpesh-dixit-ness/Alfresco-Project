package com.alfresco.adf_automation_qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utils;


public class SettingPage extends BaseClass2 {
	@FindBy(id = "adf-provider-selector")
	private WebElement dropdown;

	@FindBy(id = "mat-option-1")
	private WebElement ecm;
	
	@FindBy(id = "host-button")
	private WebElement bttnApply;
	
	SettingPage(){
		PageFactory.initElements(driver, this);
	}
	
	public void selectESM() throws InterruptedException{
		
		log.info("Clicking on Provider dropdown");
		try {
			Utils.waitForVisibility(dropdown);
			dropdown.click();
			log.info("Selecting value : ECM");
			ecm.click();
			log.info("Clicking on Apply button");
			Utils.waitForClickability(bttnApply);
			bttnApply.click();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

}
