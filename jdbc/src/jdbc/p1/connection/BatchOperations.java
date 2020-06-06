package jdbc.p1.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class BatchOperations {
	private static Connection conn = null;

	static {
		try {
			String url = "jdbc:mysql://localhost:3306/login";
			String user = "root";
			String password = "Admin@123!";
			conn = DriverManager.getConnection(url, user, password);

			// This is necessary while executing multiple batch operation in one
			// transaction.
			// So that we can do custom commit/roll-back.
			conn.setAutoCommit(false);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PreparedStatement pStmt;
		try {

			String insertQuery = "insert into users values (?,?,?,?,?,?)";

			pStmt = conn.prepareStatement(insertQuery);
			pStmt.setString(1, UUID.randomUUID().toString());
			pStmt.setString(2, "Subhan");
			pStmt.setString(3, "Subhan@gmail.com");
			pStmt.setString(4, "SecretSubhanPassword");
			pStmt.setString(5, new Date().toString());
			pStmt.setString(6, "DEVELOPER");

			// First batch query
			pStmt.addBatch();
			
			// You have to comment below line. No need to create statement again. One time is sufficient.
			//pStmt = conn.prepareStatement(insertQuery);
			
			pStmt.setString(1, UUID.randomUUID().toString());
			pStmt.setString(2, "Shoaib");
			pStmt.setString(3, "Shoaib@gmail.com");
			pStmt.setString(4, "SecretShoaibPassword");
			pStmt.setString(5, new Date().toString());
			pStmt.setString(6, "DEVELOPER");

			// Second batch query
			pStmt.addBatch();
			
			// Finally executing all statements at once.
			int count[] = pStmt.executeBatch();
			System.out.println(count.length);
			
			conn.commit();
			System.out.println("commit done!");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
				System.out.println("rollbacked!");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
