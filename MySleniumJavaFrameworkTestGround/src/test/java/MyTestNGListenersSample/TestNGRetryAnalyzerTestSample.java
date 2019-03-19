package MyTestNGListenersSample;

import org.testng.ITestResult;

public class TestNGRetryAnalyzerTestSample {
	
	  private int retryCount = 0;
	  private static final int maxRetryCount = 4;
	 
	  public boolean retry(ITestResult result) {
	    if (retryCount < maxRetryCount) {
	      retryCount++;
	      return true;
	    }
	    return false;
	  }

}
