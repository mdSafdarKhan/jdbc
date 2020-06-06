package jdbc.p1.connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MetaDataOperations {
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
		DatabaseMetaData databaseMetaData = conn.getMetaData();
		System.out.println("===== DatabaseMetaData =====");
		System.out.println("databaseMetaData.getDriverName(): " + databaseMetaData.getDriverName());
		System.out.println("databaseMetaData.getDriverVersion(): " + databaseMetaData.getDriverVersion());
		System.out.println("databaseMetaData.getUserName(): " + databaseMetaData.getUserName());
		System.out.println("databaseMetaData.getDatabaseProductName(): " + databaseMetaData.getDatabaseProductName());
		System.out.println("databaseMetaData.getDatabaseProductVersion(): " + databaseMetaData.getDatabaseProductVersion());
	
		ResultSet rs = conn.createStatement().executeQuery("select * from city");
		ResultSetMetaData resultSetMetaData = rs.getMetaData();
		System.out.println("===== ResultSetMetaData =====");
		System.out.println("resultSetMetaData.getColumnName(1): " + resultSetMetaData.getColumnName(1));
		System.out.println("resultSetMetaData.getColumnName(2): " + resultSetMetaData.getColumnName(2));
		System.out.println("resultSetMetaData.getColumnName(3): " + resultSetMetaData.getColumnName(3));
		
		System.out.println("resultSetMetaData.getColumnType(1): " + resultSetMetaData.getColumnType(1));
		System.out.println("resultSetMetaData.getColumnType(2): " + resultSetMetaData.getColumnType(2));
		System.out.println("resultSetMetaData.getColumnType(3): " + resultSetMetaData.getColumnType(3));
		
		System.out.println("resultSetMetaData.getColumnCount(): " + resultSetMetaData.getColumnCount());
	}
}
