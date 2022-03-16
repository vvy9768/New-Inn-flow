package Listeners;




import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;




public class Listener implements ITestListener{
	   
	  
	  
	public void onTestStart(ITestResult result) { 
		System.out.println(result.getMethod().getMethodName()+" is started the test ");
	}  
	  
	public void onTestSuccess(ITestResult result) {  
	  
	System.out.println( result.getMethod().getMethodName()+"  is Passed  " );  
	
	}  
	  
	public void onTestFailure(ITestResult result) {  
		
	System.out.println(result.getMethod().getMethodName()+"  is Failed ");  
	
	}  
	  
	public void onTestSkipped(ITestResult result) {  
			  
		
	System.out.println(result.getMethod().getMethodName()+" is Skipped ");  
	}  
	  
	  
	public void testCaseStarted(ITestContext context) {  
	System.out.println(" Test case  is Started");
	}  
	  
	public void onFinish(ITestContext context) {
		
			
		System.out.println( "Test case is Finished ");
	} 
	
}
