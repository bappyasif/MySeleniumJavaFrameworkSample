package SampleConfigPropertiesTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import AnotherAttemptInTestNGSample.MyTestNGSamepleWithPropertiesFile;

public class ConfigPropertiesFileTest {

	// Setting few Class variable so that all the functions can have access to them.
	// Getting Project Path from System
	static String projectPath = System.getProperty("user.dir");
	// Step #1 Create an object of Properties Class
	static Properties properties = new Properties();

	// Java needs its Main function to run from.
	public static void main(String[] args) {
		
		// Calling Functions
		getPropertiesFromFile();
		setPropertiesIntoFile();
		getPropertiesFromFile();

	}

	public static void getPropertiesFromFile() {

		try {
			
			// Step #2 Create an object of InputStream Class which will create FileInputStream Class object.
			InputStream inStream = new FileInputStream(projectPath + "\\src\\test\\java\\SampleConfigPropertiesTest\\config.properties");
			
			// Step #3 Load value from Properties
			properties.load(inStream);
			
			// Step #4 Get values from properties file entry/'i'es and save it into a String to avoid redundancy.
			String valueKey = properties.getProperty("browser");
			
			// Lets print it out on the console
			System.out.println(valueKey);  
			
			// Lets call the variable from my TestNg test Class and set it with browser value from earlier.
			MyTestNGSamepleWithPropertiesFile.browserPlatform = valueKey;
		
		} catch (Exception ex) {
			
			// lets get some console printing going for understanding what's causing the exception
			System.out.println("Exception Message goes here:  " +ex.getMessage());
			System.out.println("Cause Message goes here:  " +ex.getCause());
			
			// Throw entire error stack on the console for pin point the problem
			System.out.println("Stack Trace Message is : "); ex.printStackTrace();
		}
	}
	
	
	public static void setPropertiesIntoFile() {
		
		// Step #1 properties is already being placed at the top of the Class as an variable 
		// so that we can use it without creating another variable of the same Class
		
		try {
			
			// Step #2 create an object of OutputStream Class which will be writing into a FileOutPutStream object.
			OutputStream outStream = new FileOutputStream(projectPath + "\\\\src\\\\test\\\\java\\\\SampleConfigPropertiesTest\\\\config.properties");
			
			// Step #3 set the value to be included into properties file. It can be any key value pair entry.
			properties.setProperty("browser", "InternetExplorer");
			properties.setProperty("TestResult", "pass");
			// Step #4 in order to actually writing into the properties file you need to store that value.
			try {
				properties.store(outStream, "New Value");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
