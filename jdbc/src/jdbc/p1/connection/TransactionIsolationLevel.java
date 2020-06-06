package jdbc.p1.connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionIsolationLevel {
	private static Connection conn = null;

	static {
		try {
			String url = "jdbc:mysql://localhost:3306/login";
			String user = "root";
			String password = "Admin@123!";
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		/*
		 * Isolation Levels Constants 
		 * 1. JDBC_TRANSACTION_NONE: A constant indicating
		 * that JDBC driver does not support transactions.
		 * 
		 * 2. TRANSACTION_READ_UNCOMMITTED: A constant indicating that dirty reads,
		 * non-repeatable reads and phantom reads can occur.
		 * 
		 * 3. TRANSACTION_READ_COMMITTED: A constant indicating that dirty reads are
		 * prevented; non-repeatable reads and phantom reads can occur.
		 * 
		 * 4. TRANSACTION_REPEATABLE_READ: A constant indicating that dirty reads and
		 * non-repeatable reads are prevented; phantom reads can occur.
		 * 
		 * 5. TRANSACTION_SERIALIZABLE: A constant indicating that dirty reads,
		 * non-repeatable reads and phantom reads are prevented.
		 */

		try {

			// Getting default one
			System.out.println(conn.getTransactionIsolation());

			// Setting level for no dirty-reads, no non-repeatable reads and no phantom-reads.
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			System.out.println(conn.getTransactionIsolation());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
