package Listeners;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	private static String path;
	private ExtentTest test;
	private static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter;

	public static ExtentReports createIntance() {
		path = getReportName();
		htmlReporter = new ExtentHtmlReporter(path);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setReportName("Automation Test Result");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.setSystemInfo("Inn-Flow", "Report by Virendra");
		extent.setSystemInfo("Browser", "Chrome");
		extent.attachReporter(htmlReporter);
		return extent;

	}

	private static String getReportName() {
		Date dt = new Date();
		String fileName = "AutomationReport_" + dt.toString().replace(":", "_").replace(" ", "_") + ".html";
		String directory = System.getProperty("user.dir") + "/Reports/";
		new File(directory).mkdir();
		path = directory + fileName;
		return path;

	}

	public void setExtentLogger(String TestCaseName) {
		test = extent.createTest(TestCaseName);
	}

	public void flushExtentsReport() {
		extent.flush();
	}

	public ExtentTest getExtentLogger() {

		return test;
	}

}
