package webUtills;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;




public class WebUtills {


private static final Logger logger = Logger.getLogger(WebUtills.class);	
	
private static WebUtills WbUtill;	
private static WebDriver driver;	
private static WebElement we;	
private static List<WebElement> listWe	;
public static   ExtentTest exTestLogger; 
	
	
	
	
private WebUtills() {
	
}

public static WebUtills getObj() {
	if(WbUtill==null) {
		WbUtill=new WebUtills();
	}
	return WbUtill;
}

public  WebDriver getdriver() {

	if (driver==null) {
	System.setProperty("webdriver.chrome.driver", "jar/chromedriver.exe");
     driver=new ChromeDriver();
     }else {
     	return driver;
    }
    return driver;
   }







public void launchBrowser(String browserName) {
	
	if(browserName.equalsIgnoreCase("chrome")){
		//System.setProperty("webdriver.chrome.driver", "jar/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		logger.info(browserName+" Browser has been launched successfully");	
		exTestLogger.log(Status.INFO, String.format(browserName+" Browser has been launched successfully"));

 	}else if(browserName.equalsIgnoreCase("firefox")) {
 		WebDriverManager.firefoxdriver().setup();
	driver=new FirefoxDriver();
	logger.info(browserName+" Browser has been launched successfully");	
	exTestLogger.log(Status.INFO, String.format(browserName+" Browser has been launched successfully"));

	}else if(browserName.equalsIgnoreCase("ms")) {
		WebDriverManager.edgedriver().setup();
	 driver=new EdgeDriver();
	 logger.info(browserName+" Browser has been launched successfully");	
	 exTestLogger.log(Status.INFO, String.format(browserName+" Browser has been launched successfully"));
	
	}else if(browserName.equalsIgnoreCase("ie")) {
		WebDriverManager.iedriver().setup();
		driver=new InternetExplorerDriver();
		logger.info(browserName+" Browser has been launched successfully");	//driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS );
		exTestLogger.log(Status.INFO, String.format(browserName+" Browser has been launched successfully"));

	}
	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

public String getAtributeValue(By by, String attributeName) {
	        we=getElement(by);
	      return  we.getAttribute(attributeName);
}

public void navigateTo(String url) {
	driver.navigate().to(url);
	exTestLogger.log(Status.INFO, String.format("Navigated on "+ url));

}
public void get(String url) {
	driver.get(url);
	exTestLogger.log(Status.INFO, String.format("Navigated on :"+ url) );
}
	
public void closeBrowser() {
	driver.close();
	exTestLogger.log(Status.INFO, String.format("Browser closed ") );

}

public void quitBrowser() {
	driver.quit();
	exTestLogger.log(Status.INFO, String.format("Browser closed " ) );

}
public void deleteAllCookies() {
	driver.manage().deleteAllCookies();
    
}

public String getTitle() {
	
	return driver.getTitle();
	 

}
public String getCurrentUrl() {
	
	return driver.getCurrentUrl();
}
public WebElement getElement(By we2) {
	we= driver.findElement(we2);
	
	return	we;
}

public List<WebElement> getElements(By by) {
	
		listWe=	driver.findElements(by);	
			return listWe;
}


public void click(By by, String elemtName) {
	we=driver.findElement(by);
	we.click();
	exTestLogger.log(Status.PASS, String.format(elemtName +" is clicked successfully  ",ExtentColor.GREEN) );
	
}

public void sendKeys(By element,String input) {

		WebElement we = getElement(element);
		we.clear();
			we.sendKeys(input);
	
			exTestLogger.log(Status.PASS, String.format(input +" is successfully filled ",ExtentColor.GREEN) );
		
}
public void getTagName() {
	we.getTagName();
}
public String  getText(By by) {
	we=getElement(by);
	return we.getText();
}
public void maximize() {
	driver.manage().window().maximize();
	
}
//================================varify The Elements ====================================================//

public void verifyText(By element, String expectedText) {
	String actualText;
	
	 actualText = getElement(element).getText();

	if (actualText.equals(expectedText)) {
		exTestLogger.log(Status.PASS,
				MarkupHelper.createLabel(String.format(expectedText+" Text  is Matched"), ExtentColor.GREEN));

	} else {
		exTestLogger.log(Status.FAIL,
				MarkupHelper.createLabel(String.format(expectedText+" is text not Matched"), ExtentColor.RED));

		try {
			Assert.assertEquals(actualText, expectedText);
		} catch (Throwable t) {
		
			exTestLogger.log(Status.FAIL, t);

		}
	}
}
public void verifyTextByContains(By element, String expectedText) {
	String actualText;
	
	 actualText = getElement(element).getText();

	if (actualText.contains(expectedText)) {
		exTestLogger.log(Status.PASS,
				MarkupHelper.createLabel(String.format(expectedText+" Text  is Matched"), ExtentColor.GREEN));

	} else {
		exTestLogger.log(Status.FAIL,
				MarkupHelper.createLabel(String.format(expectedText+" is text not Matched"), ExtentColor.RED));

		try {
			Assert.assertEquals(actualText, expectedText);
		} catch (Throwable t) {
		
			exTestLogger.log(Status.FAIL, t);

		}
	}
}
public void verifyElement_IsVisible(By element,String elementName) {
	boolean actual = getElement(element).isDisplayed();
	
	if (actual == true) {
		exTestLogger.log(Status.PASS, MarkupHelper.createLabel(String.format(elementName)+" is visible", ExtentColor.GREEN));

	} else {
		exTestLogger.log(Status.FAIL, MarkupHelper.createLabel(String.format(elementName)+" is not visible" ,ExtentColor.RED) );

		try {
			Assert.assertEquals(actual, true);
		} catch (Throwable e) {
			exTestLogger.log(Status.FAIL, e);
		}

	}
}
public void verifyElement_IsSelected(By element, String elementName) {
	boolean actual = getElement(element).isSelected();
	if (actual == true) {
		exTestLogger.log(Status.PASS, MarkupHelper.createLabel(String.format(elementName )+" is  Selected", ExtentColor.GREEN));

	} else {
		exTestLogger.log(Status.FAIL, MarkupHelper.createLabel(String.format(elementName)+" is not Selected", ExtentColor.RED));

		try {
			Assert.assertEquals(actual, true);
		} catch (Throwable t) {
			exTestLogger.log(Status.FAIL, t);
		}

	}
}

public void verifyElement_IsEnable(WebElement we2, String elementName) {
	boolean actual = we2.isEnabled();
	
	if (actual == true) {
		exTestLogger.log(Status.PASS, MarkupHelper.createLabel(String.format(elementName)+" is Enable", ExtentColor.GREEN));

	} else {
		exTestLogger.log(Status.FAIL, MarkupHelper.createLabel(String.format(elementName)+ " is  Disable", ExtentColor.RED));

		try {
			Assert.assertEquals(actual, true);
		} catch (Throwable t) {
			exTestLogger.log(Status.FAIL, t);
		}

	}
}
	public void verifyElement_IsEnable(By we2, String elementName) {
		boolean actual = getElement(we2).isEnabled();
		
		if (actual == true) {
			exTestLogger.log(Status.PASS, MarkupHelper.createLabel(String.format(elementName)+" is Enable", ExtentColor.GREEN));

		} else {
			exTestLogger.log(Status.FAIL, MarkupHelper.createLabel(String.format(elementName)+ " is  Disable", ExtentColor.RED));

			try {
				Assert.assertEquals(actual, true);
			} catch (Throwable t) {
				exTestLogger.log(Status.FAIL, t);
			}

		}
	
}
public void verifyTextByElement(By element, String exp_text) {
	String act_text = getElement(element).getText();
	if (act_text.equalsIgnoreCase(exp_text)) {
		exTestLogger.log(Status.PASS, MarkupHelper.createLabel(String.format(exp_text)+" text is matched ", ExtentColor.GREEN));

	} else {
		exTestLogger.log(Status.FAIL, MarkupHelper.createLabel(String.format(exp_text)+ " text is  not matched ", ExtentColor.RED));

		try {
			Assert.assertEquals(act_text, exp_text);
		} catch (Throwable t) {
			exTestLogger.log(Status.FAIL, t);
		}

	}
}
public void verifyUrlByElement(String exp_text) {
	String act_text = driver.getCurrentUrl();
	if (act_text.equalsIgnoreCase(exp_text)) {
		exTestLogger.log(Status.PASS, MarkupHelper.createLabel(String.format(exp_text)+" url is matched ", ExtentColor.GREEN));

	} else {
		exTestLogger.log(Status.FAIL, MarkupHelper.createLabel(String.format(exp_text)+ " url is not matched ", ExtentColor.RED));

		try {
			Assert.assertEquals(act_text, exp_text);
		} catch (Throwable t) {
			exTestLogger.log(Status.FAIL, t);
		}

	}
}
	public void verifyText(String act_text , String exp_text) {
	
		if (act_text.equalsIgnoreCase(exp_text)) {
			exTestLogger.log(Status.PASS, MarkupHelper.createLabel(String.format(exp_text)+" text is matched as per test case ", ExtentColor.GREEN));

		} else {
			exTestLogger.log(Status.FAIL, MarkupHelper.createLabel(String.format(exp_text)+ "text  is  not matched as per test case  ", ExtentColor.RED));

			try {
				Assert.assertEquals(act_text, exp_text);
			} catch (Throwable t) {
				exTestLogger.log(Status.FAIL, t);
			}

		}
	
}

//====================================Select====================================//
Select slc;
public void selectByValue(By by, String value) {
	we=driver.findElement(by);
    slc= new Select(we);
   	slc.selectByValue(value);
   	exTestLogger.log(Status.PASS, String.format(value+" is slected from dropdown ",ExtentColor.BLUE) );

   	  }
public void selectByIndex(By by, int num) {
	we=driver.findElement(by);
    slc= new Select(we);
    slc.selectByIndex(num);
    exTestLogger.log(Status.PASS, String.format(num+"th no index is slected from dropdown ",ExtentColor.BLUE) );

   	  }
public void selectByVisibleText(By by, String text) {
	we=driver.findElement(by);
    slc= new Select(we);
    slc.selectByVisibleText(text);
    exTestLogger.log(Status.PASS, String.format(text+" is slected from dropdown ",ExtentColor.BLUE) );
		
   	  }

 public List<WebElement> getOptions(By by) {
	we= driver.findElement(by);
	 slc= new Select(we);
	 return slc.getOptions();	 
 }
 public void selectAllOptions(By by) {
	  we= driver.findElement(by);
	 Select se = new Select(we);
	 List<WebElement> listWe=se.getAllSelectedOptions();
     for(WebElement list:listWe) {
     	System.out.println(list.getText());
     }
	}
 
 //=======================================Actions============================//
 
 static Actions act;
 public Actions click_Act(WebDriver driver) {
	act =new Actions(driver);
	act.click();
	buildPerform();
	return act;
	 
 }
 
 
 public static void buildPerform() {
	 act.build().perform();
 }
 public void click_Act(By element,String elmtName) {
		act = new Actions(driver);
		act.click(getElement(element)).build().perform();
		exTestLogger.log(Status.PASS, String.format(elmtName+" is clicked on successfully ",ExtentColor.BLUE) );

	}

	public void actClickAndHold(By element,String elmtName) {
		act = new Actions(driver);
		act.clickAndHold(getElement(element)).build().perform();
		exTestLogger.log(Status.PASS, String.format(elmtName+" is clicked and hold on successfully ",ExtentColor.BLUE) );

	}

	public void actDragAndDrop(By element, By target) {
		act = new Actions(driver);
		act.dragAndDrop(getElement(element), getElement(target)).build().perform();
		
	}

	public void actDoubleClick(By element) {
		act = new Actions(driver);
		act.doubleClick(getElement(element)).build().perform();
	
	}

	public void mouseOver(By element,String elementName) {
		act = new Actions(driver);
		act.moveToElement(getElement(element)).build().perform();
		
		
		exTestLogger.info( MarkupHelper.createLabel("mouse over to "+elementName, ExtentColor.BLUE));
		
	}

	public void actSendKeys(By element, String value) {
		act = new Actions(driver);
		act.sendKeys(getElement(element), value).build().perform();
		
	}
 
 
 
 //===========================WindowHandle==================================//
 public void getWindowHandleByTitle(String exp_title) {
	Set<String> windows=driver.getWindowHandles();
	for(String window:windows) {
		driver.switchTo().window(window);
		String act_title=driver.getTitle();
		if(act_title.equalsIgnoreCase(exp_title)) {
			exTestLogger.log(Status.PASS, String.format("Navigate on page "+exp_title));
			
			break;
		}	
	}
	

 }
 public void getWindowHandleByUrl(String exp_url) {
	 
		Set<String> windows=driver.getWindowHandles();
		for(String window:windows) {
			driver.switchTo().window(window);
			String act_url=driver.getCurrentUrl();
			if(act_url.startsWith(exp_url)) 
			{
				exTestLogger.log(Status.PASS, String.format("Navigate on Url :"+ exp_url));
				break;
			}
		}
	
	 }

	public void windowHandleOnlyOneByTitle(String exp_title) {
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			driver.switchTo().window(window);
			String title = driver.getTitle();
			if (!title.equalsIgnoreCase(exp_title)) {
				exTestLogger.log(Status.PASS, String.format("Navigate on page "+ exp_title));
					driver.close();
			}
		}
		
	}
	public void windowHandleOnlyOneByUrl(String exp_url) {
		Set<String> wndw = driver.getWindowHandles();
		for (String window : wndw) {
			driver.switchTo().window(window);
			String act_url = driver.getCurrentUrl();
			if (!act_url.equalsIgnoreCase(exp_url)) {
				driver.close();
			}
		}
	}
	//=================================Alert============================================//
	public void alertAccept() {
		driver.switchTo().alert().accept();

	}

	public void alertDeny() {
		driver.switchTo().alert().dismiss();

	}
	public void alertSendMassage(String key) {
		driver.switchTo().alert().sendKeys(key);
	}
	public void alertGetMassage(){
		System.out.println(driver.switchTo().alert().getText());
	}
//=====================================wait=================================================//
	WebDriverWait wait;
	public void imlecitlyWait(int timeInSec) {
		driver.manage().timeouts().implicitlyWait(timeInSec, TimeUnit.SECONDS);
		
	}
	
	public void pageLoadTimeOut(int time) {
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
	}
	public void HoldOn(int timeInSec) {
		try {
			Thread.sleep(timeInSec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}	
   public void  explicitlyWait(WebDriver driver,int timeOutInSeconds) {
		wait= new WebDriverWait(driver, timeOutInSeconds);
		
	}

	public void waitUntillVisibleOfElement(int i, By element) {
		explicitlyWait(driver, i);
		wait.until(ExpectedConditions.visibilityOf(getElement(element)));
	
	}
	
	public void waitUntillTextToBePresent(By Element,String text) {
		we=driver.findElement(Element);
		wait.until(ExpectedConditions.textToBePresentInElement(we, text));
		
	}
	
	public void waitUntillUrlToBePresent(String url) {
		
		wait.until(ExpectedConditions.urlContains(url));
	
	}
	//=====================================SnapShot======================================================//
	public String takeSnapshot(WebDriver driver,String  string) {
		SimpleDateFormat timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss");
		Date date = new Date();
		String dat = timestamp.format(date);
		String da = dat.replace("/", "-");
		String d = da.replace(":", "_");

		TakesScreenshot tcc = (TakesScreenshot) driver;
		File srcFile = tcc.getScreenshotAs(OutputType.FILE);
		File destFile = new File("ScreenShot/" + string + " "+d + ".png");
		String path = destFile.getAbsolutePath();

		try {
			Files.copy(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		exTestLogger.log(Status.FAIL, " takeSnapshot is Applied sucessfully ");

		return path;
	
	}
	
	//====================================Properties File====================================================//
	String filePath="src/main/resources/PropFiles/Config.properties";
	File file;
	FileInputStream fis;
	Properties propObj;
	public void loadConfig(String filePath) {
		this.filePath=filePath;
		file=new File(filePath);
		
		try {
			fis=new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		propObj=new Properties();
		try {
			propObj.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
		
	
	public String getPropVal( String keyname) {
		if (propObj == null) {
			loadConfig(filePath);
		}

		String propVal = propObj.getProperty(keyname);
		return propVal;
	}

	public void setProp(String key, String value) {
		if (propObj == null) {
			loadConfig(filePath);
		}
		propObj.setProperty(key, value);
	}
	//====================================javaScript====================================================//
	
	public void jsClick(By element) {
		JavascriptExecutor js = (JavascriptExecutor) getdriver();
		js.executeScript("arguments[0].click()", element);
		exTestLogger.log(Status.PASS, " jsClick is Applied sucessfully ");

	}



	public void jsScrollDown(int cordinte) {
		JavascriptExecutor jse = (JavascriptExecutor) getdriver();
		jse.executeScript("window.scrollTo(0,\"" + cordinte + "\")");
		exTestLogger.log(Status.PASS, " jsScrollDown is Applied sucessfully ");

	}

	public void jsScrollUp(int cordinte) {
		JavascriptExecutor jse = (JavascriptExecutor) getdriver();
		jse.executeScript("window.scrollTo(\"" + cordinte + "\",0");
		exTestLogger.log(Status.PASS, " jsScrollUp is Applied sucessfully ");

	}


	//==============================================================================================//
	

	// -----------------Date And Time-----------------\\
	public Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();

	}

	public String getSimpleDateTimeFormate() {
		SimpleDateFormat timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss");
		Date date = new Date();
		String dat = timestamp.format(date);
		return dat;
	}
	public String getSimpleDateWithoutTime() {
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 Date dateWithoutTime = null;
	 try {
			dateWithoutTime = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return dateWithoutTime.toString();
	}
	
	//================================extentReport========================================//
	private static String path;
		
	private static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter;
	
	public  ExtentReports initHtmlReport() {
		           getReportName();
		       htmlReporter=new ExtentHtmlReporter(path);
		       
		       htmlReporter.config().setEncoding("utf-8");
		       htmlReporter.config().setDocumentTitle("Automation Reports");
		       htmlReporter.config().setReportName("Automation Test Result");
		       htmlReporter.config().setTheme(Theme.DARK);
		         
		       extent=new ExtentReports();
		       extent.setSystemInfo("CompanyName", "Inn-flow");
		       extent.setSystemInfo("Emp", "Report by Virendra");
		       extent.setSystemInfo("Browser", "Chrome");
		       extent.setSystemInfo("Report", "ExtentReport(.html)");
		       extent.attachReporter(htmlReporter);
		  
		       return extent;
		       
	}

	
	public  String getReportName() {
		 String directory;
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Date dateWithoutTime = null;
		try {
			dateWithoutTime = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		  String fileName="AutomationReport_"+dateWithoutTime.toString().replace(":", "_").replace(" ", "_")+".html";
		  directory= System.getProperty("user.dir")+"/Reports/"; 
		  new File(directory).mkdir();
		  path=directory+fileName;
		  
	 return path;
		
	}
	
	public void setExtentLogger(String TestCaseName) {
		exTestLogger = extent.createTest(TestCaseName);
	}
	
	public void flushExtentsReport() {
		extent.flush();
	}

	public ExtentTest getExtentLogger() {
		
		return exTestLogger;
	}

	
	
	
	public String [] getKey(String key) {
		String strArr[] = null;
		String value;
		if(key=="EHID") {
			 value=testcaseDataMap.get(key);
			strArr =value.split(",");
			return strArr;
		}else {
			 value=testcaseDataMap.get(key);
			 strArr=value.split(",");
			 return strArr;
		}
		
	}
//==============================Setter and Getter method================================================//	
	
	
	
	private Map<String ,String> testcaseDataMap;
	public void setTestCaseData(Map<String ,String> map) {
		 testcaseDataMap=map;
	}
	ArrayList<String>testcaseDataList;
	public ArrayList<String> getTestCaseDataArrayList() {
		return testcaseDataList;
	} 
	public void setTestCaseList(ArrayList<String> list) {   
		testcaseDataList=list;
	}
	
	public Map<String ,String> getTestCaseDataMap() {
		return testcaseDataMap;
	}
	
	String text;
   public void setText(String text) {
	  this.text=text;
   }
   public String  getText() {	 
	 return text;
   }
	
    
 
 
	public String getExData(String str) {
	     String data=testcaseDataMap.get(str);
	     return data;
    }
	
	//=========================Random Class========================================//
	
	public String getRandomMobileNumber() {
		 Random rand = new Random();
		    int num1, num2, num3;
		    num1 = rand.nextInt (900) + 100;
		    num2 = rand.nextInt (643) + 100;
		    num3 = rand.nextInt (9000) + 1000;
      	 System.out.println(num1+""+num2+""+num3);
  return  (num1+""+num2+""+num3);
	}
   
	public String getRandomString() {
	
		 String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567"
			 		+ "890abcdefghijklmnopqrstuvwxyz";

		    StringBuilder sb = new StringBuilder();
		    Random random = new Random();
		    int length = 5;
		    for(int i = 0; i < length; i++) {
		      int index = random.nextInt(alphabet.length());
                 char randomChar = alphabet.charAt(index);
		      sb.append(randomChar);
		    }

		    String randomString = sb.toString();
		    System.out.println("Random String is: " + randomString);
		    return randomString;
	}

	public void selectRandomDataFromDD(By noOfDays_list) {
		// TODO Auto-generated method stub
		
	}

	public void sendKeysByKeyBoard(By brow_lk, Keys enter) {
		// TODO Auto-generated method stub
		
	}

	public String getRandomStringOnLength(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	
 
}