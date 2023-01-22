
public class ModelViewControllerConsole 
{
	public static void main(String[] args) 
	{
		connectToDatabase();
		disconnectFromDatabase();
	}
	
	public static void connectToDatabase() 
	{
		DatabaseUtilities.host = "DESKTOP-ET69OSR";
		DatabaseUtilities.databaseName = "Project";
		
		try 
		{
			DatabaseUtilities.getConnection();
			System.out.println("Connected!");
		}
		catch(Exception e) 
		{
			System.out.println("Exception occured : " + e);
			return;
		}		
	}
	
	public static void disconnectFromDatabase() 
	{
		try 
		{
			DatabaseUtilities.disconnect();
			System.out.println("Disconnected!");
		}
		catch(Exception e) 
		{
			System.out.println("Error disconnecting from database : " + e);
			return;
		}		
	}
}
