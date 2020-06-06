package jdbc.p1.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SchemaOperations {

	private static Connection conn = null;

	static {
		try {
			String url = "jdbc:mysql://localhost:3306/";
			String user = "root";
			String password = "Admin@123!";
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Schema Names: " + getSchemaNames());
		createSchema("test1");
		System.out.println("Schema Names: " + getSchemaNames());
		deleteSchema("test1");
		System.out.println("Schema Names: " + getSchemaNames());
	}

	public static List<String> getSchemaNames() throws Exception {
		List<String> dbs = new ArrayList<>();
		Statement stmt = conn.createStatement();
		ResultSet schemaNames = stmt.executeQuery("show databases");
		while (schemaNames.next()) {
			dbs.add(schemaNames.getString(1));
		}
		return dbs;
	}

	public static void createSchema(String schemaName) throws Exception {
		if (!getSchemaNames().contains(schemaName)) {
			Statement stmt = conn.createStatement();
			stmt.execute("create database " + schemaName);
		}
	}

	public static void deleteSchema(String schemaName) throws Exception {
		Statement stmt = conn.createStatement();
		stmt.execute("drop database " + schemaName);
	}
}
