package ErrorHandlingExceptionSample;


public class ExcptionHandlingTestSample {
	
	
	public static void main(String[] args) {
	
		// Example #02 where you simply throws exception in the function naming and not affect the code
		// body rather handling it outside where its been used, here in this case it's calling from main.
		
		try {
			myThrowableErrorHandlingDemo();  // calling function
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Example #01 where you write your whole code in main or any other block for that matter
		// without throwing exception in the function name.
		
		try {
			
			System.out.println("Hello World....");
			
			int count = 1/0;
			
			System.out.println("Test Completed");
			
		}
		
		catch (Exception ex) {
			
			System.out.println("it's Inside catch block");
			
			System.out.println("Error Message :   " +ex.getMessage());
			
			System.out.println("Caused Message :   " +ex.getCause());
			
			System.out.println("Showing Stack   :"  +ex.fillInStackTrace());
			
		}
		
		finally {
			
			System.out.println("Finally Block Always Runs no matter what the code flow is in a try catch block");
			System.out.println("It's inside 'Finally' block");
			
		}
				
		
	}
	
	// called from main function to demonstrate exception being thrown and handled.
	public static void myThrowableErrorHandlingDemo() throws Exception{
		
		System.out.println("Hello World....");
		
		int count = 1/0;
		
		System.out.println("Lets throw a deleiberate wxcepttion here, if needed!");
		
		throw new Exception("Not a vaild Operatoin.");
		
		//System.out.println("Test Completed");
		
		
		
	}

}