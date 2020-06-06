package jdbc.p1.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Date;
import java.util.UUID;

public class SavePointOperations {
	private static Connection conn = null;

	static {
		try {
			String url = "jdbc:mysql://localhost:3306/login";
			String user = "root";
			String password = "Admin@123!";
			conn = DriverManager.getConnection(url, user, password);
			
			// This is necessary if we are performing our own custom commit/rollback mechanism.
			conn.setAutoCommit(false); 

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		PreparedStatement pStmt = null;
		Savepoint savePoint1 = null;

		//Good query going to commit
		try {
			String goodQuery = "insert into users values (?,?,?,?,?,?)";
			pStmt = conn.prepareStatement(goodQuery);
			pStmt.setString(1, UUID.randomUUID().toString());
			pStmt.setString(2, "Maryland1");
			pStmt.setString(3, "Maryland@gmail.com");
			pStmt.setString(4, "SecretMarylandPassword");
			pStmt.setString(5, new Date().toString());
			pStmt.setString(6, "DEVELOPER");
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error in good code!");
		}

		// Saving above code to be not executing. Above code will execute if anything happen in below code.
		try {
			savePoint1 = conn.setSavepoint("SAVEPOINT-1");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error in setting save point!");
		}

		try {
			String badQuery = "insert into users values (?,?,?,?,?,?,?)";
			pStmt = conn.prepareStatement(badQuery);
			pStmt.setString(1, UUID.randomUUID().toString());
			pStmt.setString(2, "Yousuf");
			pStmt.setString(3, "Yousuf@gmail.com");
			pStmt.setString(4, "SecretYousufPassword");
			pStmt.setString(5, new Date().toString());
			pStmt.setString(6, "DEVELOPER");
			
			//This code will generate exception. It is going to be the reason for triggering save-point mechanism.
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error in bad code!");
			try {
				conn.rollback(savePoint1);
				System.err.println("Bad code roll backed!");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		try {
			conn.commit();
			System.out.println("commit done!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
