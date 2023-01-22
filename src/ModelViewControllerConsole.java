import java.util.*;

public class ModelViewControllerConsole 
{
	public static void main(String[] args) throws Exception 
	{
		connectToDatabase();

		Map<String, Controller> router = new HashMap<>();		
		router.put("MainMenu", new Controller(new MainMenuView(), new NopModel()));
		router.put("Apartment", new Controller(new ApartmentView(), new ApartmentModel()));
		
		ViewData viewData = new ViewData("MainMenu", "");		
		do {
			// Model, View, and Controller
			Controller controller = router.get(viewData.functionName);
			ModelData modelData = controller.executeModel(viewData);
			viewData = controller.getView(modelData, viewData.functionName, viewData.operationName);
			
			System.out.println();
			System.out.println("-------------------------------------------------");
			System.out.println();
		}
		while (viewData.functionName != null);
		
		System.out.println();
		System.out.println();
		System.out.println("Program is ended...");


		// Disconnect from database
		disconnectFromDatabase();
	}
	
	public static void connectToDatabase() 
	{
		DatabaseUtilities.host = "DESKTOP-ET69OSR";
		DatabaseUtilities.databaseName = "LatestProject";
		
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
