package utils;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.alfresco.adf_automation_qa.BaseClass2;

public class Utils extends BaseClass2{
	
	public static void waitForVisibility(WebElement element){
		log.info("Waiting for visibility of Element:"+element);
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitForClickability(WebElement element){
		log.info("Waiting for visibility of Element:"+element);
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitForList(List<WebElement> list){
		log.info("Waiting for visibility of List:"+list);
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
	}
	
	public static void waitforSeconds(int seconds) throws InterruptedException{
		log.info("Wait for Sec:"+seconds);
		Thread.sleep(seconds*1000);
	}

}
