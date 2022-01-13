package webUtills;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
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




public class WebUtills {


	private static final Logger logger = Logger.getLogger(WebUtills.class);	
	
private static WebUtills WbUtill;	
private static WebDriver driver;	
private static WebElement we;	
private static List<WebElement> listWe	;
public static   ExtentTest exTestLogger; // we could generate the logs in the report.
 // we set the path where our reports need to generate.
	
	
	
	
private WebUtills() {
	
}

public static WebUtills getObj() {
	if(WbUtill==null) {
		WbUtill=new WebUtills();
	}
	return WbUtill;
}

public static WebDriver getdriver() {
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
		System.setProperty("webdriver.chrome.driver", "jar/chromedriver.exe");
		driver=new ChromeDriver();
	}else if(browserName.equalsIgnoreCase("firefox")) {
		System.setProperty("webdriver.gecko.driver", "jar/geckodriver.exe");
	driver=new FirefoxDriver();
	}else if(browserName.equalsIgnoreCase("ms")) {
		System.setProperty("webdriver.edge.driver", "jar/msedgedriver.exe");
	 driver=new EdgeDriver();
	}else if(browserName.equalsIgnoreCase("ie")) {
		System.setProperty("webdriver.ie.driver", "jar/msedgedriver.exe");
		driver=new InternetExplorerDriver();
	}
	logger.info(browserName+" Browser has been launched successfully");
	exTestLogger.log(Status.INFO, browserName + " Browser is Launch sucessfully ");
	//driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS );
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

public void navigateTo(String url) {
	driver.navigate().to(url);
	exTestLogger.log(Status.INFO, url + " Navigated SuccesFully ");

}
public void get(String url) {
	driver.get(url);
	exTestLogger.log(Status.PASS, url+"  Navigated SuccesFully  ");
}
	
public void closeBrowser() {
	driver.close();
	exTestLogger.log(Status.INFO, "  closed browser SuccesFully  ");

}

public void quitBrowser() {
	driver.quit();
	exTestLogger.log(Status.INFO, "  Quit browser SuccesFully  ");

}
public void deleteAllCookies() {
	driver.manage().deleteAllCookies();
	exTestLogger.log(Status.INFO, "  deleteAllCookies of browser SuccesFully  ");

}

public String getTitle() {
	
	return driver.getTitle();
	 

}
public String getCurrentUrl() {
	
	return driver.getCurrentUrl();
}
public WebElement getElement(By by) {
	we= driver.findElement(by);
	
	return	we;
}

public List<WebElement> getElements(By by) {
	
		listWe=	driver.findElements(by);	
			return listWe;
}


public void click(By by) {
	we=driver.findElement(by);
	we.click();
}

public void sendKeys(By element,String input) {
	try {
		WebElement we = getElement(element);
		we.clear();
			we.sendKeys(input);
			exTestLogger.log(Status.INFO,
					"value ( " + input + " ) is inputed SuceessFully on " + element.toString());

		
	} catch (ElementNotInteractableException e) {
		exTestLogger.log(Status.FAIL,
				MarkupHelper.createLabel(element.toString() + " Element is not present ", ExtentColor.RED));
	
	}
	
}
public void getTagName() {
	we.getTagName();
}
public void getText() {
	we.getText();
}
public void maximize() {
	driver.manage().window().maximize();
	exTestLogger.log(Status.INFO, "Maximize the Window  is Applied sucessfully ");

}
//================================varify The Elements ====================================================//

public void verifyText(By element, String expectedText, String elementname) {
	String actualText = getElement(element).getText();
	if (actualText.equals(expectedText)) {
		exTestLogger.log(Status.PASS,
				MarkupHelper.createLabel(elementname + "  Text is present", ExtentColor.GREEN));

	} else {
		exTestLogger.log(Status.FAIL,
				MarkupHelper.createLabel(elementname + " Text is not present", ExtentColor.RED));

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
		exTestLogger.log(Status.PASS, MarkupHelper.createLabel(elementName + " is  visible", ExtentColor.GREEN));

	} else {
		exTestLogger.log(Status.FAIL, MarkupHelper.createLabel(elementName, ExtentColor.RED) + "is not visible ");

		try {
			Assert.assertEquals(actual, true);
		} catch (Throwable t) {
			exTestLogger.log(Status.FAIL, t);
		}

	}
}
public void verifyElement_IsSelected(By element, String elementname) {
	boolean actual = getElement(element).isSelected();
	if (actual == true) {
		exTestLogger.log(Status.PASS, MarkupHelper.createLabel(elementname + " is  Selected", ExtentColor.GREEN));

	} else {
		exTestLogger.log(Status.FAIL, MarkupHelper.createLabel(elementname + " is not Selected ", ExtentColor.RED));

		try {
			Assert.assertEquals(actual, true);
		} catch (Throwable t) {
			exTestLogger.log(Status.FAIL, t);
		}

	}
}

public void verifyElement_IsEnable(By element, String elementname) {
	boolean actual = getElement(element).isEnabled();
	if (actual == true) {
		exTestLogger.log(Status.PASS, MarkupHelper.createLabel(elementname + " is  enable", ExtentColor.GREEN));

	} else {
		exTestLogger.log(Status.FAIL, MarkupHelper.createLabel(elementname + " is not enable ", ExtentColor.RED));

		try {
			Assert.assertEquals(actual, true);
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
   	exTestLogger.log(Status.INFO, "Select By Value    performed successfully on " + value);

   	  }
public void selectByIndex(By by, int num) {
	we=driver.findElement(by);
    slc= new Select(we);
   		 slc.selectByIndex(num);
   		exTestLogger.log(Status.INFO, "Select By index  performed successfully on index : " + num);

   	  }
public void selectByVisibleText(By by, String text) {
	we=driver.findElement(by);
    slc= new Select(we);
   		 slc.selectByVisibleText(text);
   		exTestLogger.log(Status.INFO, "Select By Visible text    performed successfully on " + text);

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
 public void click_Act(By element) {
		act = new Actions(driver);
		act.click(getElement(element)).build().perform();
		exTestLogger.log(Status.INFO, "Action click performed successfully on " + element.toString());

	}

	public void actClickAndHold(By element) {
		act = new Actions(driver);
		act.clickAndHold(getElement(element)).build().perform();
		exTestLogger.log(Status.INFO, "Action Click  And Hold performed successfully on " + element.toString());

	}

	public void actDragAndDrop(By element, By target) {
		act = new Actions(driver);
		act.dragAndDrop(getElement(element), getElement(target)).build().perform();
		exTestLogger.log(Status.INFO, "Action drag And Drop   performed successfully on " + element.toString());

	}

	public void actDoubleClick(By element) {
		act = new Actions(driver);
		act.doubleClick(getElement(element)).build().perform();
		exTestLogger.log(Status.INFO, "Action DoubleClick   performed successfully on " + element.toString());

	}

	public void mouseHover(By element) {
		act = new Actions(driver);
		act.moveToElement(getElement(element)).build().perform();
		exTestLogger.log(Status.INFO, "Action mouseHover   performed successfully on " + element.toString());

	}

	public void actSendKeys(By element, String value) {
		act = new Actions(driver);
		act.sendKeys(getElement(element), value).build().perform();
		exTestLogger.log(Status.INFO, "Action SendKeys   performed successfully on " + element.toString());

	}
 
 
 
 //===========================WindowHandle==================================//
 public void getWindowHandleByTitle(String exp_title) {
	Set<String> windows=driver.getWindowHandles();
	for(String window:windows) {
		driver.switchTo().window(window);
		String act_title=driver.getTitle();
		if(act_title.equalsIgnoreCase(exp_title)) {
			exTestLogger.log(Status.INFO, exp_title+"  called  SuccesFully  ");

			break;
		}	
	}
	

 }
 public void getWindowHandleByUrl(String exp_url) {
		Set<String> windows=driver.getWindowHandles();
		for(String window:windows) {
			driver.switchTo().window(window);
			String act_url=driver.getCurrentUrl();
			if(act_url.startsWith(exp_url)) {
				exTestLogger.log(Status.INFO, exp_url+"  called  SuccesFully  ");
				break;
			}
		}
		exTestLogger.log(Status.INFO, " getWindowHandleByUrl called  SuccesFully  ");

	 }

	public void windowHandleOnlyOneByTitle(String exp_titles) {
		Set<String> wndw = driver.getWindowHandles();
		for (String window : wndw) {
			driver.switchTo().window(window);
			String title = driver.getTitle();
			if (!title.equalsIgnoreCase(exp_titles)) {
				exTestLogger.log(Status.INFO, exp_titles+"  called  SuccesFully  ");
				driver.close();
			}
		}
		exTestLogger.log(Status.INFO, " windowHandleOnlyOneByTitle called  SuccesFully  ");

	}
	public void windowHandleOnlyOneByUrl(String exp_url) {
		Set<String> wndw = driver.getWindowHandles();
		for (String window : wndw) {
			driver.switchTo().window(window);
			String act_url = driver.getCurrentUrl();
			if (!act_url.equalsIgnoreCase(exp_url)) {
				driver.close();
			}
		}exTestLogger.log(Status.INFO, " windowHandleOnlyOneByUrl called  SuccesFully  ");

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
	public void imlicitlyWait(int timeInSec) {
		driver.manage().timeouts().implicitlyWait(timeInSec, TimeUnit.SECONDS);
		exTestLogger.log(Status.INFO, " implicitlyWait is Applied sucessfully ");

	}
	
	public void pageLoadTimeOut(int time) {
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
	}
	public void HoldOn(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		exTestLogger.log(Status.INFO, " Thread.sleep  is Applied sucessfully ");

	}	
   public void  explicitlyWait(WebDriver driver,int timeOutInSeconds) {
		wait= new WebDriverWait(driver, timeOutInSeconds);
		exTestLogger.log(Status.INFO, " explicitlyWait is Applied sucessfully ");

	}

	public void waitUntillVisibleOfElement(By Element) {
		we=driver.findElement(Element);
		wait.until(ExpectedConditions.visibilityOf(we));
		exTestLogger.log(Status.INFO, " waitUntillVisibleOfElement is Applied sucessfully ");

	}
	
	public void waitUntillTextToBePresent(By Element,String text) {
		we=driver.findElement(Element);
		wait.until(ExpectedConditions.textToBePresentInElement(we, text));
		exTestLogger.log(Status.INFO, " waitUntillTextToBePresent is Applied sucessfully ");

	}
	
	public void waitUntillUrlToBePresent(String url) {
		
		wait.until(ExpectedConditions.urlContains(url));
		exTestLogger.log(Status.INFO, " waitUntillTextToBePresent is Applied sucessfully ");

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
		exTestLogger.log(Status.PASS, " takeSnapshot is Applied sucessfully ");

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
	public static ExtentReports initHtmlReport() {
		             getReportName();
		            System.out.println(path);
		       htmlReporter=new ExtentHtmlReporter(path);
		       htmlReporter.config().setEncoding("utf-8");
		       htmlReporter.config().setDocumentTitle("Automation Reports");
		       htmlReporter.config().setReportName("Automation Test Result");
		       htmlReporter.config().setTheme(Theme.STANDARD);
		       
		       extent=new ExtentReports();
		       extent.setSystemInfo("CompanyName", "Inn-flow");
		       extent.setSystemInfo("Emp", "Report by Virendra");
		       extent.setSystemInfo("Browser", "Chrome");
		       extent.setSystemInfo("Report", "ExtentReport(.html)");
		       extent.attachReporter(htmlReporter);
		       return extent;
	}

	public static String getReportName() {
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

	private Map<String ,String> testcaseDataMap;
	public void setTestCaseData(Map<String ,String> map) {
		 testcaseDataMap=map;
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
	public Map<String ,String> getTestCaseDataMap() {
		return testcaseDataMap;
	}

 
}