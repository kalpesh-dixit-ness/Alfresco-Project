package com.alfresco.adf_automation_qa;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Utils;


public class HomePage extends BaseClass2{
	@FindBy(className = "mat-button-wrapper")
	private List<WebElement> listToolbarButtons;

	@FindBy(id = "adf-folder-name-input")
	private WebElement txtFolderName;

	@FindBy(id = "adf-folder-create-button")
	private WebElement bttnAddFolder;

	@FindBy(css = "adf-datatable-row.adf-datatable-row")
	private List<WebElement> listFolders;
	
	@FindBy(css = ".app-sidenav-linklist span.mat-line")
	private List<WebElement> listSideMenuBar;
		
	@FindBy(css = ".mat-simple-snackbar.ng-star-inserted>span")
	private WebElement flashMsg;
	
	@FindBy(id = "adf-folder-cancel-button")
	private WebElement bttnCancel;
	
	@FindBy(id = "adf-userinfo-ecm-name-display")
	private WebElement logoGuestUser;
	
	
	

	
	HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public void clickSideMenuButton(String name){
		log.info("Clicking on Side MEnu Button"+name);
		Utils.waitForList(listSideMenuBar);
		for(int i=0;i<listSideMenuBar.size();i++){
			String buttonName = listSideMenuBar.get(i).getText();
			if(buttonName.trim().equals(name)){
				log.info("Clicking Button: "+name);
				listSideMenuBar.get(i).click();
				break;
			}
		}
	}
	
	public boolean isHomePageLogoDisplayed(){
		log.info("Verifying Guest User Logo on Home Page");
		Utils.waitForClickability(logoGuestUser);
		return isElementDisplayed(logoGuestUser);
	}
			
	public boolean createNewFolder(String sideButtonName, String folderName) throws InterruptedException {
		log.info("Going to Files Page");
		Utils.waitforSeconds(3);
		//driver.get("http://qaexercise.envalfresco.com/files");
		clickSideMenuButton(sideButtonName);
		createFolder(folderName);	
		//Assert New Folder Creation
		Utils.waitForList(driver.findElements(By.cssSelector("adf-datatable-row.adf-datatable-row")));
		Utils.waitforSeconds(2);
		List<WebElement> folders = driver.findElements(By.cssSelector("adf-datatable-row.adf-datatable-row"));
		for(int i=1; i<folders.size(); i++){
			String fName = folders.get(i).findElement(By.cssSelector(".adf-datatable-content-cell>span")).getText();
			if(fName.equals(folderName)){
				return true;
			}
							
		}
		return false;
	}
	
	public boolean createDuplicateFolder(String folderName) throws InterruptedException{
		createFolder(folderName);
		Utils.waitforSeconds(2);
		try {
			if(flashMsg.isDisplayed()){
				String msg = flashMsg.getText();
				String expected = "There's already a folder with this name. Try a different name.";
				if(msg.trim().equals(expected)){
				Utils.waitForClickability(bttnCancel);
				bttnCancel.click();
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return false;
	}
	
	public boolean deleteFolder(String folderName) throws InterruptedException{
		log.info("Deleting Folder:"+folderName);
		List<WebElement> folders = driver.findElements(By.cssSelector("adf-datatable-row.adf-datatable-row"));
		for(int i=1; i<folders.size(); i++){
			log.info("Number of Folders:"+folders.size());
			String fName = folders.get(i).findElement(By.cssSelector(".adf-datatable-content-cell>span")).getText();
			if(fName.equals(folderName)){
				try {
					String rowNo =Integer.toString(i-1);
					folders.get(i).findElement(By.cssSelector("div>#action_menu_right_"+rowNo)).click();
					WebElement btnDelete = driver.findElement(By.cssSelector("button.mat-menu-item.ng-star-inserted:nth-child(5)"));
					Utils.waitForClickability(btnDelete);
					log.info("Clicking on Delete Button");
					btnDelete.click();
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}

				Utils.waitforSeconds(2);
				try {
					if(flashMsg.isDisplayed()){
						String msg = flashMsg.getText();
						String expected = folderName+" deleted";
						if(msg.trim().equals(expected)){
							return true;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e.getMessage());
				}
				
			}
		
	}
		return false;
	}
	
	public void createFolder(String folderName) throws InterruptedException{
		log.info("Creating New Folder: "+folderName);
		Utils.waitforSeconds(4);
		for(int i=0; i<listToolbarButtons.size();i++){
			String text = listToolbarButtons.get(i).getText();
			if(text.equals("create_new_folder")){
				listToolbarButtons.get(i).click();
				break;
			}
		}
		log.info("Entering Folder Name:"+folderName);
		try {
			Utils.waitForVisibility(txtFolderName);
			txtFolderName.sendKeys(folderName);
			Utils.waitForClickability(bttnAddFolder);
			bttnAddFolder.click();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

	}
}
