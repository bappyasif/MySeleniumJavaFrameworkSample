package tutorial.edureka.example.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumTestNGDatabaseTesting {

	// STep 1: Setting DB
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String JDBC_URL = "jdbc:mysql//localhost/testDB";

	// DB Credentials
	static final String root_USER = "root";
	static final String root_PASS = "rootPASS";

	static Connection db_Connection;
	private static Statement db_Statement;

	@BeforeTest
	public void settingEnvironment() {

		try {

			String dbClass = "com.mysql.cj.jdbc.Driver";
			Class.forName(dbClass).newInstance();

			System.out.println("Connecting Database");
			db_Connection = DriverManager.getConnection(JDBC_URL, root_USER, root_PASS);

			System.out.println("Create Statement");
			db_Statement = db_Connection.createStatement();

		} catch (Exception ex) {
			// TODO: handle exception
		}

	}

	@Test
	public void CommencingTest() {

		try {
			String sql_Statement;
			sql_Statement = "Select id, first, last, age From test_DB";
			ResultSet resultSet = db_Statement.executeQuery(sql_Statement);

			while (resultSet.next()) {

				// Retrieving By Column Name
				int userID = resultSet.getInt("id");
				int userAge = resultSet.getInt("age");

				String firstName = resultSet.getNString("first");
				String lastName = resultSet.getNString("last");

				// Displaying Values
				System.out.println("ID Found : " + userID);
				System.out.println("Age Found : " + userAge);
				System.out.println("First NAme : " + firstName);
				System.out.println("Last NAme : " + lastName);

							}
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

	}
	
	@AfterTest
	public void tearDown() throws SQLException {
		
		if (db_Connection!= null) {
			
			db_Statement.close();
			db_Connection.close();

			System.out.println("Done Testing");

		}
		
	}

}
