package Log4jSample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SampleLog4jTest {

	private static Logger logger = LogManager.getLogger(SampleLog4jTest.class);
	
	public static void main(String[] args) {
		
		// Unless we configure Log4j we'll only see messages that are high or above error level.

		System.out.println("\n  Hello World.....  \n");
		
		logger.debug("This is a Debug Message");
		logger.info("This is an information Message");
		logger.error("This is an error Message");
		logger.warn("This is an Warning Message");
		logger.fatal("This is a fatal Message");
		logger.trace("This is Trace Message!!!");
		
		System.out.println("\n .....Finished Priniting \n");

	}

}
