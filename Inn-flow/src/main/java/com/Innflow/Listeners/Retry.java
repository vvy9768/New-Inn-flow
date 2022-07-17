package com.Innflow.Listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	private static int maxTry=3, count=0;
	//@Override
	public boolean retry(ITestResult result) {
		
     if (!result.isSuccess()) {                     
	 if (count < maxTry) {                          
		     count++;                               
	    result.setStatus(ITestResult.FAILURE);  
		    return true;                                 
	     } else {
	       	result.setStatus(ITestResult.FAILURE);  
		            }
	    } else {
		 result.setStatus(ITestResult.SUCCESS);      
		     }
		        return false;
		    }
}
