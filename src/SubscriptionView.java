
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionView implements ViewInterface {

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
                int subscriptionID = resultSet.getInt("SubscriptionID");
                java.sql.Date startDate = resultSet.getDate("StartDate");
                java.sql.Date endDate = resultSet.getDate("EndDate");
                int amount = resultSet.getInt("Amount");
                int typeID = resultSet.getInt("TypeID");

                // Display values
                System.out.print("ApartmentNumber : " +apartmentNumber + "\t");
                System.out.print("SubscriptionID : " +subscriptionID + "\t");
                System.out.print("StartDate : " +startDate + "\t");
                System.out.print("EndDate : " +endDate + "\t");
                System.out.print("Amount : " +amount + "\t");
                System.out.print("TypeID : " +typeID);
                System.out.println(""); 
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
        Integer subscriptionID = getInteger("Subscription ID : ", true);
        java.sql.Date startDate = (java.sql.Date) getDate("Start Date : ", true);
        java.sql.Date endDate = (java.sql.Date) getDate("End Date : ", true);
        Integer amount = getInteger("Amount  : ", true);
        Integer typeID = getInteger("TypeID  : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (apartmentNumber != null) {
            whereParameters.put("ApartmentNumber", apartmentNumber);
        }
        if (subscriptionID != null) {
            whereParameters.put("SubscriptionID", subscriptionID);
        }
        if (startDate != null) {
            whereParameters.put("StartDate", startDate);
        }
        if (endDate != null) {
            whereParameters.put("EndDate", endDate);
        }
        if (amount != null) {
            whereParameters.put("Amount", amount);
        }
        if (typeID != null) {
            whereParameters.put("TypeID", typeID);
        }

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Subscription", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception 
    {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "ApartmentNumber, StartDate, EndDate, Amount, TypeID ");

        List<Object> rows = new ArrayList<>();
        Integer a,d,e;
        String b,c;
        do {
            System.out.println("Fields to insert:");
            a = getInteger("ApartmentNumber : ", true);
            b = getString("StartDate : ", true);
            c = getString("EndDate : ", true);
            d = getInteger("Amount : ", true);
            e = getInteger("TypeID : ", true);
            System.out.println();

            if (a != null && b != null && c != null && d != null && e != null) {
                rows.add(new Subscription(a, b, c , d, e));
            }
        } while (a != null && b != null && c != null && d != null && e != null);

        parameters.put("rows", rows);

        return new ViewData("Subscription", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        String name = getString("Name : ", true);
        String groupName = getString("Group Name : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (name != null) {
            updateParameters.put("Name", name);
        }
        if (groupName != null) {
            updateParameters.put("GroupName", groupName);
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Subscription", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Subscription", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Subscription View";
    }

}
