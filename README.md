# Alfresco-Project
# Developed by Kalpesh Dixit

Description: 
* This project is built on selenium webdriver 3.141.59 version. 
* As it is a maven project, it will download all required external libraries from web. I have used testNG framework.
* In the root folder of project, there is testng.xml file. Essential parmeters like browser, url and credentials can be set through this file. 
* Given test scenarios are covered in TestADFDemo.java which is linked to the testng.xml file. 

Explanation: 
* I have used POM design in this project. Hence, all elements and methods related to a webpage are present in its respective class file.
* Common functionalities and init methods are kept in BaseClass file which is extended by all other class files.
* Test files are kept in seperate package and they are designed using testNG annotations.
* As this application is built on the top of angularjs, it was challenging to locate several webElements. I have located them by using customized css paths.
* List are used in many places to traverse through the collection of webelements. 
* Every step mentioned in assignment is followed by assert statements. 
* I have implemented this assignment in chrome and firefox browser.    
* console Logs are generated and saved in Mylogs.log file under logs folder. It can be used to trace an execution. 
* Default testNG reports can be used for reference.   

Prerequisites:
* As I have used maven project, there is no need to download and add external libraries in Build path.
* Chrome driver and firefox driver as placed in lib folder.
* Selenium version 3.141.59 
* jdk 1.8 and testng 6.14.3
* Chrome browser Version 81.0 in local system
* Firefox browser version 75.0 in local system