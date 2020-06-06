package jdbc.p1.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableResultSetOperations {

	private static Connection conn = null;

	static {
		try {
			String url = "jdbc:mysql://localhost:3306/world";
			String user = "root";
			String password = "Admin@123!";
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		// By default ResultSet is move forward only and not updatable.
		Statement stmt = conn.createStatement();
		
		// ResultSet will be in Read only mode.
		//Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		
		//ResultSet will be in Updatable only mode.
		//Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
		
		//If anything changes in database, it will be reflect to loaded result.
		//Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		ResultSet rs = stmt.executeQuery("select * from city");
		
		// Move to next row
		rs.first();
		System.out.println("first cityName: " + rs.getString("Name"));

		rs.next();
		System.out.println("next cityName: " + rs.getString("Name"));
		
		// Trying to move to previous row: It will generate exception with ResultSet.TYPE_FORWARD_ONLY
		rs.previous();
		System.out.println("previous cityName: " + rs.getString("Name"));
	}
}
