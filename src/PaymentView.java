
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentView implements ViewInterface{
    
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
                int ssn = resultSet.getInt("SSN");
                int roleID = resultSet.getInt("RoleID");
                int amount = resultSet.getInt("Amount");
                java.sql.Date date = resultSet.getDate("Date");
                int type = resultSet.getInt("Type");
                String description = resultSet.getString("Description");
                String paymentID = resultSet.getString("PaymentID");
                String receipt = resultSet.getString("Receipt");
                // Display values
                System.out.print(apartmentNumber + "\t");
                System.out.print(ssn + "\t");
                System.out.print(roleID + "\t");
                System.out.println(amount + "\t");
                System.out.println(date + "\t");
                System.out.println(type + "\t");
                System.out.println(description + "\t");
                System.out.println(paymentID + "\t");
                System.out.println(receipt);
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
        Integer ssn = getInteger("SSN : ", true);
        Integer roleID = getInteger("RoleID : ", true);
        Integer amount = getInteger("Amount : ", true);
        java.sql.Date date = (java.sql.Date) getDate("Date  : ", true);
        Integer type = getInteger("Type  : ", true);
        String description =  getString("Description : ", true);
        String paymentID = getString("PaymentID  : ", true);
        String receipt = getString("Receipt  : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (apartmentNumber != null) {
            whereParameters.put("ApartmentNumber", apartmentNumber);
        }
        if (ssn != null) {
            whereParameters.put("SSN", ssn);
        }
        if (roleID != null) {
            whereParameters.put("RoleID", roleID);
        }
        if (amount != null) {
            whereParameters.put("Amount", amount);
        }
        if (date != null) {
            whereParameters.put("Date", date);
        }
        if (type != null) {
            whereParameters.put("Type", type);
        }
        if (description != null) {
            whereParameters.put("Description", description);
        }
        if (paymentID != null) {
            whereParameters.put("PaymentID", paymentID);
        }
        if (receipt != null) {
            whereParameters.put("Receipt", receipt);
        }

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Payment", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "Name, GroupName");

        List<Object> rows = new ArrayList<>();

        String name, groupName;
        do {
            System.out.println("Fields to insert:");
            name = getString("Name : ", true);
            groupName = getString("Group Name : ", true);
            System.out.println();

            if (name != null && groupName != null) {
                rows.add(new Department(name, groupName));
            }
        } while (name != null && groupName != null);

        parameters.put("rows", rows);

        return new ViewData("Payment", "insert", parameters);
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

        return new ViewData("Payment", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Payment", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Payment View";
    }
}
