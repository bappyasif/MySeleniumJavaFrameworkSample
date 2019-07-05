package tutorial.edureka.example.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SeleniumFarmeworkDatabaseTesting {
	// STep 1:  Setting DB
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String JDBC_URL = "jdbc:mysql//localhost/testDB";
	
	// DB Credentials
	static final String root_USER = "root";
	static final String root_PASS = "rootPASS";
	
	public static void ExecutingDatabase() throws ClassNotFoundException, SQLException {
		
		Connection db_Connection;
		Statement db_Statement;
		
		// STEP 2: Register DB
		String dbClass = "com.mysql.cj.jdbc.Driver";
		Class.forName(dbClass);
		
		// Step 3: Open Connection
		System.out.println("Connecting Database");
		db_Connection =  DriverManager.getConnection(JDBC_URL, root_USER, root_PASS);
		
		// Step 4: Query Execution
		System.out.println("Create Statement");
		db_Statement = db_Connection.createStatement();
		
		String sql_Statement;
		sql_Statement = "Select id, first, last, age From test_DB";
		ResultSet resultSet = db_Statement.executeQuery(sql_Statement);
		
		while(resultSet.next()){
			
			// Retrieving  By Column Name
			int userID = resultSet.getInt("id");
			int userAge = resultSet.getInt("age");
			
			String firstName = resultSet.getNString("first");
			String lastName = resultSet.getNString("last");
			
			// Displaying Values
			System.out.println("ID Found : " +userID);
			System.out.println("Age Found : " +userAge);
			System.out.println("First NAme : " +firstName);
			System.out.println("Last NAme : " +lastName);
			
			// Step 6: Cleaning Environment
			resultSet.close();
			db_Statement.close();
			db_Connection.close();
			
			System.out.println("Done Testing");
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		SeleniumFarmeworkDatabaseTesting created_Obejct = new SeleniumFarmeworkDatabaseTesting();
		created_Obejct.ExecutingDatabase();
		
	}

}
