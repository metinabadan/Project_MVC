
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class DueView implements ViewInterface{

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
                int dueID = resultSet.getInt("DueID");
                Date date = resultSet.getDate("Date");
                int amount = resultSet.getInt("Amount");

                // Display values
                System.out.print(apartmentNumber + "\t");
                System.out.print(dueID + "\t");
                System.out.println(amount + "\t");
                System.out.print(date);
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
        Integer apartmentNumber = getInteger("Apartment Number : ", true);
        Integer subscriptionID = getInteger("Due ID : ", true);
        Date date = getDate("Date : ", true);
        Integer amount = getInteger("Amount  : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (apartmentNumber != null) {
            whereParameters.put("ApartmentNumber", apartmentNumber);
        }
        if (subscriptionID != null) {
            whereParameters.put("SubscriptionID", subscriptionID);
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

        return new ViewData("Due", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "Name, GroupName");

        List<Object> rows = new ArrayList<>();

        Integer a,b,d;
        String c;
        do {
            System.out.println("Fields to insert:");
            a = getInteger("ApartmentNumber : ", true);
            c = new SimpleDateFormat("yyyy-MM-dd").format(getDate("Date : ", true));
            d = getInteger("Amount : ", true);
            System.out.println();

            if (a != null && c != null && d != null) {
                rows.add(new Due(a, d, c));
            }
        } while (a != null && c != null && d != null);

        parameters.put("rows", rows);

        return new ViewData("Due", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer a = getInteger("ApartmentNumber : ", true);
        Integer b = getInteger("DueID : ", true);
        String c = new SimpleDateFormat("yyyy-MM-dd").format(getDate("Date : ", true));
        Integer d = getInteger("Amount : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (a != null) {
            updateParameters.put("ApartmentNumber", a);
        }
        if (b != null) {
            updateParameters.put("DueID", b);
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

        return new ViewData("Due", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Due", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Due View";
    }

    
}
