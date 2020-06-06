package jdbc.p1.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionPooling {

	static BasicDataSource dataSource = null;

	static {
		dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/login");
		dataSource.setUsername("root");
		dataSource.setPassword("Admin@123!");
		dataSource.setInitialSize(10);
	}

	public static void main(String[] args) {
		try {
			System.out.println(dataSource.getInitialSize());
			System.out.println(dataSource.getMaxActive());
			System.out.println(dataSource.getMaxIdle());
			System.out.println(dataSource.getPassword());

			Connection conn = dataSource.getConnection();
			System.out.println(conn.getMetaData().getDatabaseProductName());
			System.out.println(conn.getMetaData().getDatabaseProductVersion());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
