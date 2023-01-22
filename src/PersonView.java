
import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonView implements ViewInterface{
    
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
                int ssn = resultSet.getInt("SSN");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                String phone = resultSet.getString("Phone");
                int roleID = resultSet.getInt("RoleID");
                int flatNumber = resultSet.getInt("FlatNumber");

                // Display values
                System.out.print(ssn + "\t");
                System.out.print(name + "\t");
                System.out.print(email + "\t");
                System.out.println(phone + "\t");
                System.out.println(roleID + "\t");
                System.out.println(flatNumber);
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
        //BURDA BIGINT YOK SIKINTI CIKABILIR        
        Integer ssn = getInteger("Apartment Number : ", true);
        String name = getString("Subscription ID : ", true);
        String email = getString("Start Date : ", true);
        String phone = getString("End Date : ", true);
        Integer roleID = getInteger("Amount  : ", true);
        Integer flatNumber = getInteger("TypeID  : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (ssn != null) {
            whereParameters.put("SSN", ssn);
        }
        if (name != null) {
            whereParameters.put("Name", name);
        }
        if (email != null) {
            whereParameters.put("Email", email);
        }
        if (phone != null) {
            whereParameters.put("Phone", phone);
        }
        if (roleID != null) {
            whereParameters.put("RoleID", roleID);
        }
        if (flatNumber != null) {
            whereParameters.put("FlatNumber", flatNumber);
        }

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Person", "select", parameters);
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

        return new ViewData("Person", "insert", parameters);
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

        return new ViewData("Person", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Person", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Person View";
    }
}
