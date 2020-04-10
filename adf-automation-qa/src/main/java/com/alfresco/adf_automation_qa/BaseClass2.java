package com.alfresco.adf_automation_qa;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass2 {
	
		public static WebDriver driver;
		public static Logger log;
		
		public void initBrowser(String browserName, String baseurl){
			
			log = LogManager.getLogger(BaseClass2.class);
			if(browserName.toLowerCase().equals("chrome")){
				log.info("Initializing Chrome Driver");
				System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
		        driver = new ChromeDriver();
		        log.info("Maximizing chrome browser");
		        driver.manage().window().maximize();
			}
			if(browserName.toLowerCase().equals("firefox")){
				System.setProperty("webdriver.chrome.driver", "./lib/geckodriver.exe");
		        driver = new ChromeDriver();
		        driver.manage().window().maximize();
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			log.info("Going on settings page...");
			//PropertyReader.getPropery("baseURL");
			driver.get(baseurl);
		}
		
		public boolean isElementDisplayed(WebElement element){
			log.info("Verifying if Element is displayed:"+element);
			try {
				if(element.isDisplayed()){
					log.info("Element displayed successfully");
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
			return false;
		}
		
		public void closeApp(){
			log.info("Closing the App");
			driver.quit();
		}
		
	}

