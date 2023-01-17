import java.sql.*;
import java.util.*;

public class DatabaseUtilities 
{
	private static Connection connection = null;
	public static String host;
	public static String databaseName;
	
	public static Connection getConnection() throws SQLException 
	{
		if (connection == null) 
		{
			String conUrl =   "jdbc:sqlserver://" + host + ";"
							+ "databaseName=" + databaseName + ";"
							+ "integratedSecurity=true";

			connection = DriverManager.getConnection(conUrl);
		}
		
		return connection;
	}
	
	public static void disconnect() throws SQLException
	{
		if (connection == null) 
		{
			connection.close();
		}
	}
}
