package vn.com.quanlynhanvien.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import vn.com.quanlynhanvien.entity.Employee;

public class DataBaseUtils {

	// Private constructor to prevent instantiation
	private DataBaseUtils() {
	}

	// Database configuration details
	private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/human_resource_management";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1234";
	private static final String MAX_POOL = "250";

	// Connection and properties objects
	private static Connection connection;
	private static Properties properties;

	/**
	 * Sets up database properties if they are not already configured.
	 *
	 * @return Properties object containing database connection properties
	 */
	private static Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
			properties.setProperty("user", USERNAME);
			properties.setProperty("password", PASSWORD);
			properties.setProperty("MaxPooledStatements", MAX_POOL);
		}
		return properties;
	}

	/**
	 * Establishes a connection to the database if one does not already exist.
	 *
	 * @return Connection object to interact with the database
	 */
	public static Connection connect() {
		if (connection == null) {
			try {
				Class.forName(DATABASE_DRIVER); // Load database driver
				connection = DriverManager.getConnection(DATABASE_URL, getProperties());
				System.out.println("Successful connection to the database!");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	/**
	 * Closes the existing database connection if it is open.
	 */
	public static void disconnect() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Executes an SQL statement using a Statement object and returns the number of
	 * affected rows. This method is suitable for static SQL statements without
	 * parameters.
	 *
	 * @param SQLstatement The SQL statement to execute (e.g., insert, update, or
	 *                     delete).
	 * @return The number of rows affected by the SQL statement, or -1 if an error
	 *         occurs.
	 */
	public static int execute(String SQLstatement) {
		Statement stmt;
		try {
			// Create a statement object to send SQL queries to the database
			stmt = connection.createStatement();

			// Execute the SQL statement and return the number of affected rows
			return stmt.executeUpdate(SQLstatement);
		} catch (SQLException e) {
			// Print the SQL exception if an error occurs
			e.printStackTrace();
		}
		// Return -1 if an exception occurs or if execution fails
		return -1;
	}

	/**
	 * Executes an SQL update using a PreparedStatement with parameters and returns
	 * the number of affected rows. This method is ideal for parameterized queries
	 * and prevents SQL injection.
	 *
	 * @param SQLstatement The SQL statement to execute with placeholders for
	 *                     parameters.
	 * @param emp          An Employee object used to set the parameters in the
	 *                     prepared statement.
	 * @return The number of rows affected by the SQL update, or -1 if an error
	 *         occurs.
	 */
	public static int executeUpdate(String SQLstatement, Employee emp) {
		PreparedStatement stmt;
		try {
			// Prepare the SQL statement with placeholders for parameters
			stmt = connection.prepareStatement(SQLstatement);

			// Set parameters for the prepared statement here (e.g., stmt.setString(1,
			// emp.getName());)
			// Uncomment and modify the line below to set parameters as needed
			// stmt.setString(1, "value1");

			// Execute the SQL update and return the number of affected rows
			return stmt.executeUpdate();
		} catch (SQLException e) {
			// Print the SQL exception if an error occurs
			e.printStackTrace();
		}
		// Return -1 if an exception occurs or if execution fails
		return -1;
	}
	
	/**
	 * Execute a query and return the number of rows affected.
	 * @param query The SQL query to execute.
	 * @return The number of rows affected or fetched.
	 */
	public static int fetchId(String query) throws SQLException {
		if (connection == null || connection.isClosed()) {
			connect();
		}
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
			if (rs.next()) {
				return rs.getInt(1);
			}
		}
		return 0;
	}
}
