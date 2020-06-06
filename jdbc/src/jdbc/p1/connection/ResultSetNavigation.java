package jdbc.p1.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetNavigation {

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
		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("select * from city");

		// Move to first row
		rs.first();
		System.out.println("first cityName: " + rs.getString("Name"));

		// Move to last row
		rs.last();
		System.out.println("last cityName: " + rs.getString("Name"));

		// Move to absolute row
		rs.absolute(4079);
		System.out.println("absolute cityName: " + rs.getString("Name"));

		rs.absolute(2);
		System.out.println("absolute cityName: " + rs.getString("Name"));

		// Move to next row
		rs.next();
		System.out.println("next cityName: " + rs.getString("Name"));

		// Move to previous row
		rs.previous();
		System.out.println("previous cityName: " + rs.getString("Name"));
	}
}
