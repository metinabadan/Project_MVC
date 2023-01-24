
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
                System.out.print("ApartmentNumber : " +apartmentNumber + "\t");
                System.out.print("VoteID : " +voteID + "\t");
                System.out.print("SSN : " +ssn + "\t");
                System.out.print("MeetingID : " +meetingID + "\t");
                System.out.print("DecisionID : " +decisionID + "\t");
                System.out.print("Vote : " +vote + "\t");
                System.out.print("RoleID : " +roleID);
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
        parameters.put("fieldNames", "ApartmentNumber, SSN, MeetingID, DecisionID, Vote, RoleID");

        List<Object> rows = new ArrayList<>();

        Integer a, b, c, d,f;
        String e;
        do {
            System.out.println("Fields to insert:");
            a = getInteger("ApartmentNumber : ", true);
            b = getInteger("SSN : ", true);
            c = getInteger("MeetingID : ", true);
            d = getInteger("DecisionID : ", true);
            e = getString("Vote : ", true);
            f = getInteger("RoleID : ", true);
            System.out.println();

            if (a != null && b != null && c != null && d != null && e != null && f != null) {
                rows.add(new Vote(a, b, c, d, e ,f));
            }
        } while (a != null && b != null && c != null && d != null && e != null && f != null);

        parameters.put("rows", rows);

        return new ViewData("Vote", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer a = getInteger("ApartmentNumber : ", true);
        Integer b = getInteger("SSN : ", true);
        Integer c = getInteger("MeetingID : ", true);
        Integer d = getInteger("DecisionID : ", true);
        String e = getString("Vote : ", true);
        Integer f = getInteger("RoleID : ", true);
        
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (a != null) {
            updateParameters.put("ApartmentNumber", a);
        }
        if (b != null) {
            updateParameters.put("SSN", b);
        }
        if (c != null) {
            updateParameters.put("MeetingID", c);
        }
        if (d != null) {
            updateParameters.put("DecisionID", d);
        }
        if (e != null) {
            updateParameters.put("Vote", e);
        }
        if (f != null) {
            updateParameters.put("RoleID", f);
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
