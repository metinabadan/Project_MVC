
import java.util.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseView implements ViewInterface{

    @Override
    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		
		switch(operationName) {
		case "select": return selectOperation(modelData);	
		case "insert": return insertOperation(modelData);	
		case "update": return updateOperation(modelData);	
		case "delete": return deleteOperation(modelData);	
		case "select.gui": return selectGUI(modelData);
		case "insert.gui": return insertGUI(modelData);
		case "update.gui": return updateGUI(modelData);
		case "delete.gui": return deleteGUI(modelData);
		}
		
		return new ViewData("MainMenu", "");
    }
    
    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                int apartmentNumber = resultSet.getInt("ApartmentNumber");
                int expenseID = resultSet.getInt("ExpenseID");
                String description = resultSet.getString("Description");
                Date date = resultSet.getDate("Date");
                int amount = resultSet.getInt("Amount");

                // Display values
                System.out.print("ApartmentNumber : " + apartmentNumber + "\t");
                System.out.print("ExpenseID : " + expenseID + "\t");
                System.out.print("Description : " + description + "\t\t");
                System.out.print("Date : " + date + "\t\t");
                System.out.println("Amount : " + amount);
            }
            resultSet.close();
        }

        return new ViewData("MainMenu", "");
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        System.out.println("Number of inserted rows is " + modelData.recordCount);

        return new ViewData("MainMenu", "");
    }

    ViewData updateOperation(ModelData modelData) throws Exception {
        System.out.println("Number of updated rows is " + modelData.recordCount);

        return new ViewData("MainMenu", "");
    }

    ViewData deleteOperation(ModelData modelData) throws Exception {
        System.out.println("Number of deleted rows is " + modelData.recordCount);

        return new ViewData("MainMenu", "");
    }

    Map<String, Object> getWhereParameters() throws Exception {
        //BURDA DATE TYPECAST VAR SIKINTI CIKABILIR        
        Integer apartmentNumber = getInteger("ApartmentNumber : ", true);
        Integer expenseID = getInteger("ExpenseID : ", true);
        String description = getString("Description  : ", true);
        Date date = getDate("Date : ", true);
        Integer amount = getInteger("Amount  : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (apartmentNumber != null) {
            whereParameters.put("ApartmentNumber", apartmentNumber);
        }
        if (expenseID != null) {
            whereParameters.put("ExpenseID", expenseID);
        }
        if (description != null) {
            whereParameters.put("Description", description);
        }
        if (date != null) {
            whereParameters.put("Date", date);
        }
        if (amount != null) {
            whereParameters.put("Amount", amount);
        }

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Expense", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "ApartmentNumber, Description, Date, Amount ");

        List<Object> rows = new ArrayList<>();

        String a, b;
        Integer c, d;
        do {
            System.out.println("Fields to insert:");
            c = getInteger("ApartmentNumber : ", true);
            a = getString("Description : ", true);
            b = getString("Date : ", true);
            d = getInteger("Amount : ", true);
            System.out.println();

            if (a != null && b != null && c != null && d != null) {
                rows.add(new Expense(c, a, b, d));
            }
        } while (a != null && b != null && c != null && d != null);

        parameters.put("rows", rows);

        return new ViewData("Expense", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer a = getInteger("ApartmentNumber : ", true);
        String b = getString("Description : ", true);
        String c = getString("Date : ", true);
        Integer d = getInteger("Amount : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (a != null) {
            updateParameters.put("ApartmentNumber", a);
        }
        if (b != null) {
            updateParameters.put("Description", b);
        }
        if (c != null) {
            updateParameters.put("Date", c);
        }
        if (d != null) {
            updateParameters.put("Amount", d);
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Expense", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Expense", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Expense View";
    }
    
}
