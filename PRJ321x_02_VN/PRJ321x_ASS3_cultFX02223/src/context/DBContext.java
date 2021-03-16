package context;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBContext {
	public Connection getConnection() {
		String connectionUrl = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName="
				+ dbName;
		if(instance == null || instance.trim().isEmpty()) {
			connectionUrl = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName="
					+ dbName;
		}
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(connectionUrl, userID, password);
			return connection;
		}
		
		catch (SQLException e) {
			// Handle any errors that may have occurred.
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private final String serverName = "localhost";
	private final String dbName = "ShoppingDB";
	private final String portNumber = "1433";
	private final String instance = ""; // LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INTANCE
	private final String userID = "sa";
	private final String password = "Password789";

	private DBContext() {
		
	}
	
	private static DBContext intance;
	
	public static DBContext getIntance() {
		if(intance == null) {
			intance = createIntanceSynchoized();
		}
		return intance;
	}
	
	private static synchronized DBContext createIntanceSynchoized() {
		if(intance == null) {
			intance = new DBContext();
		}
		return intance;
	}
}
