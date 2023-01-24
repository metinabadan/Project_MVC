import java.util.*;

class MainMenuView implements ViewInterface {

	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		
		String tableName;
		Integer choiceMainMenu;
		do 
		{
			System.out.println("0. Apartment Table");
			System.out.println("1. Decision Table");
			System.out.println("2. Department Table");
			System.out.println("3. Due Table");
			System.out.println("4. Expense Table");
			System.out.println("5. Meeting Table");
			System.out.println("6. Payment Table");
			System.out.println("7. Person Table");
			System.out.println("8. Report Table");
			System.out.println("9. Subscription Table");
			System.out.println("10. Vote Table");
			System.out.println("-1. Quit");
			System.out.println();

			choiceMainMenu = getInteger("Enter your choice : ", false);
		} 
		while (choiceMainMenu == null || choiceMainMenu < -2 || choiceMainMenu > 11);
		
		Map<String, Object> userInput = new HashMap<>();
		userInput.put("mainMenuChoice", choiceMainMenu);
		
		switch (choiceMainMenu.intValue()) 
		{
			case 0: tableName = "Apartment"; break;
			case 1: tableName = "Decision";	break;
			case 2: tableName = "Department";	break;
			case 3: tableName = "Due";	break;
			case 4: tableName = "Expense";	break;
			case 5: tableName = "Meeting";	break;
			case 6: tableName = "Payment";	break;
			case 7: tableName = "Person";	break;
			case 8: tableName = "Report";	break;
			case 9: tableName = "Subscription";	break;
			case 10: tableName = "Vote";	break;
			default: return new ViewData(null, null);
		}
		
		Integer choice;
		do {
			System.out.println("1. Show all " + tableName);
			System.out.println("2. Show " + tableName);
			System.out.println("3. Add a " + tableName);
			System.out.println("4. Update a " + tableName);
			System.out.println("5. Delete a " + tableName);
			System.out.println("6. Quit");
			System.out.println();

			choice = getInteger("Enter your choice : ", false);
		} 
		while (choice == null || choice < 1 || choice > 6);
		
		userInput.put("mainMenuChoice", choice);
		
		switch (choice.intValue()) {
		case 1: operationName = "select";		break;
		case 2: operationName = "select.gui";	break;
		case 3: operationName = "insert.gui";	break;
		case 4: operationName = "update.gui";	break;
		case 5: operationName = "delete.gui";	break;
		default: return new ViewData(null, null);
		}
		
		return new ViewData(tableName, operationName, new HashMap<>());
	}

	@Override
	public String toString() {
		return "Main Menu View";
	}		
}