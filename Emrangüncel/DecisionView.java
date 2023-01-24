
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecisionView implements ViewInterface{
    
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
                int meetingID = resultSet.getInt("MeetingID");
                int decisionID = resultSet.getInt("DecisionID");
                String description = resultSet.getString("Description");
                String isApproved = resultSet.getString("IsApproved");
                String isRequireFullVoting = resultSet.getString("IsRequireFullVoting");

                // Display values
                System.out.print(apartmentNumber + "\t");
                System.out.print(meetingID + "\t");
                System.out.print(decisionID + "\t");
                System.out.println(description + "\t");
                System.out.println(isApproved + "\t");
                System.out.println(isRequireFullVoting);
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
        Integer meetingID = getInteger("MeetingID : ", true);
        Integer decisionID = getInteger("DecisionID : ", true);
        String description = getString("Description : ", true);
        String isApproved = getString("IsApproved  : ", true);
        String isRequireFullVoting = getString("IsRequireFullVoting  : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (apartmentNumber != null) {
            whereParameters.put("ApartmentNumber", apartmentNumber);
        }
        if (meetingID != null) {
            whereParameters.put("MeetingID", meetingID);
        }
        if (decisionID != null) {
            whereParameters.put("DecisionID", decisionID);
        }
        if (description != null) {
            whereParameters.put("Description", description);
        }
        if (isApproved != null) {
            whereParameters.put("IsApproved", isApproved);
        }
        if (isRequireFullVoting != null) {
            whereParameters.put("IsRequireFullVoting", isRequireFullVoting);
        }

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Decision", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "ApartmentNumber, MeetingID, Description, IsApproved, IsRequireFullVoting");

        List<Object> rows = new ArrayList<>();
        Integer a,b;
        String c,bb,cc;
        do {
            System.out.println("Fields to insert:");
            a = getInteger("ApartmentNumber : ", true);
            b = getInteger("MeetingID : ", true);
            c = getString("Description : ", true);
            bb = getString("IsApproved : ", true);
            cc = getString("IsRequireFullVoting : ", true);
            System.out.println();

            if (a != null && b != null && c != null && bb != null && cc != null) {
                rows.add(new Decision(a, b, c, bb, cc));
            }
        } while (a != null && b != null && c != null && bb != null && cc != null);

        parameters.put("rows", rows);

        return new ViewData("Decision", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        String a = getString("ApartmentNumber : ", true);
        String b = getString("MeetingID : ", true);
        String c = getString("Description : ", true);
        String d = getString("IsApproved : ", true);
        String e = getString("IsRequireFullVoting : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (a != null) {
            updateParameters.put("ApartmentNumber", a);
        }
        if (b != null) {
            updateParameters.put("MeetingID", b);
        }
        if (c != null) {
            updateParameters.put("Description", c);
        }
        if (d != null) {
            updateParameters.put("IsApproved", d);
        }
        if (e != null) {
            updateParameters.put("IsRequireFullVoting", e);
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Decision", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Decision", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Decision View";
    }
}
