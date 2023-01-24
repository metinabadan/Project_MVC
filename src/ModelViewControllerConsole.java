import java.util.*;
import java.sql.*;

public class ModelViewControllerConsole 
{
	public static void main(String[] args) throws Exception 
	{
		Connection connection = connectToDatabase();
		
		try 
		{
			Map<String, Controller> router = new HashMap<>();		
			router.put("MainMenu", new Controller(new MainMenuView(), new NopModel()));
			router.put("Apartment", new Controller(new ApartmentView(), new ApartmentModel()));
			router.put("Decision", new Controller(new DecisionView(), new DecisionModel()));
			router.put("Due", new Controller(new DueView(), new DueModel()));
			router.put("Expense", new Controller(new ExpenseView(), new ExpenseModel()));
			router.put("Meeting", new Controller(new MeetingView(), new MeetingModel()));
			router.put("Payment", new Controller(new PaymentView(), new PaymentModel()));
			router.put("Person", new Controller(new PersonView(), new PersonModel()));
			router.put("Report", new Controller(new ReportView(), new ReportModel()));
			router.put("Subscription", new Controller(new SubscriptionView(), new SubscriptionModel()));
			router.put("Vote", new Controller(new VoteView(), new VoteModel()));
			
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
			System.out.println("Program is ended...\n");
			connection.commit();
			System.out.println("Committed!!\n");
			disconnectFromDatabase();
		} 
		catch (Exception e) 
		{
			// Disconnect from database
			connection.rollback();
			System.out.println("Rollback occur!!\n");
			disconnectFromDatabase();
		}
		



		
	}
	
	public static Connection connectToDatabase() 
	{
		DatabaseUtilities.host = "DESKTOP-ET69OSR";
		DatabaseUtilities.databaseName = "LatestProject1";
		Connection connection = null;
		try 
		{
			connection = DatabaseUtilities.getConnection();
			System.out.println("Connected!\n");
		}
		catch(Exception e) 
		{
			System.out.println("Exception occured : \n" + e);
		}
		
		return connection;
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
