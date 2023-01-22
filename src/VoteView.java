
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteView implements ViewInterface{
    
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
                int voteID = resultSet.getInt("VoteID");
                int ssn = resultSet.getInt("SSN");
                int meetingID = resultSet.getInt("MeetingID");
                int decisionID = resultSet.getInt("DecisionID");
                String vote = resultSet.getString("Vote");
                int roleID = resultSet.getInt("RoleID");

                // Display values
                System.out.print(apartmentNumber + "\t");
                System.out.print(voteID + "\t");
                System.out.print(ssn + "\t");
                System.out.println(meetingID + "\t");
                System.out.println(decisionID + "\t");
                System.out.println(vote + "\t");
                System.out.println(roleID);
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
        Integer voteID = getInteger("VoteID : ", true);
        Integer ssn = getInteger("SSN : ", true);
        Integer meetingID = getInteger("MeetingID : ", true);
        Integer decisionID = getInteger("DecisionID  : ", true);
        String vote = getString("Vote  : ", true);
        Integer roleID = getInteger("RoleID  : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (apartmentNumber != null) {
            whereParameters.put("ApartmentNumber", apartmentNumber);
        }
        if (voteID != null) {
            whereParameters.put("VoteID", voteID);
        }
        if (ssn != null) {
            whereParameters.put("SSN", ssn);
        }
        if (meetingID != null) {
            whereParameters.put("MeetingID", meetingID);
        }
        if (decisionID != null) {
            whereParameters.put("DecisionID", decisionID);
        }
        if (vote != null) {
            whereParameters.put("Vote", vote);
        }
        if (roleID != null) {
            whereParameters.put("RoleID", roleID);
        }

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Vote", "select", parameters);
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

        return new ViewData("Vote", "insert", parameters);
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

        return new ViewData("Vote", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Vote", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Vote View";
    }
}
