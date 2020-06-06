package jdbc.p1.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class TransactionOperations {

	private static Connection conn = null;

	static {
		try {
			String url = "jdbc:mysql://localhost:3306/login";
			String user = "root";
			String password = "Admin@123!";
			conn = DriverManager.getConnection(url, user, password);
			
			//By default auto commit is true, so no need to write explicitly this line.
			//When explicitly performing commit/rollback mechanism, then don't write this line.
			
			//conn.setAutoCommit(true);
			
			//When explicitly performing commit/rollback mechanism, then write this line.
			conn.setAutoCommit(false);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		PreparedStatement pStmt;
		try {
			
			String goodQuery = "insert into users values (?,?,?,?,?,?)";
			String badQuery = "insert into users values (?,?,?,?,?,?,?)";
			
			pStmt = conn.prepareStatement(goodQuery);
			pStmt.setString(1, UUID.randomUUID().toString());
			pStmt.setString(2, "Maryland");
			pStmt.setString(3, "Maryland@gmail.com");
			pStmt.setString(4, "SecretMarylandPassword");
			pStmt.setString(5, new Date().toString());
			pStmt.setString(6, "DEVELOPER");
			pStmt.executeUpdate();
			
			pStmt = conn.prepareStatement(badQuery);
			pStmt.setString(1, UUID.randomUUID().toString());
			pStmt.setString(2, "Yousuf");
			pStmt.setString(3, "Yousuf@gmail.com");
			pStmt.setString(4, "SecretYousufPassword");
			pStmt.setString(5, new Date().toString());
			pStmt.setString(6, "DEVELOPER");
			
			//Uncomment to see rollback in action
			//pStmt.executeUpdate();
			
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
