
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class MeetingView implements ViewInterface{
    
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
                Date meetingDate = resultSet.getDate("MeetingDate");
                
                // Display values
                System.out.print("ApartmentNumber : " + apartmentNumber + "\t");
                System.out.print("MeetingID : " + meetingID + "\t");
                System.out.println("MeetingDate : " +meetingDate); 
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
        Date meetingDate = (java.sql.Date) getDate("MeetingDate : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (apartmentNumber != null) {
            whereParameters.put("ApartmentNumber", apartmentNumber);
        }
        if (meetingID != null) {
            whereParameters.put("MeetingID", meetingID);
        }
        if (meetingDate != null) {
            whereParameters.put("MeetingDate", meetingDate);
        }
        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Meeting", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "ApartmentNumber, MeetingDate");

        List<Object> rows = new ArrayList<>();
        Integer a,b;
        String c;
        do {
            System.out.println("Fields to insert:");
            a = getInteger("ApartmentNumber : ", true);
            c = getString("MeetingDate : ", true);
            System.out.println();

            if (a != null && c != null) {
                rows.add(new Meeting(a, c));
            }
        } while (a != null && c != null);

        parameters.put("rows", rows);

        return new ViewData("Meeting", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        String a = getString("ApartmentNumber : ", true);
        String b = getString("MeetingID : ", true);
        String c = getString("MeetingDate : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (a != null) {
            updateParameters.put("ApartmentNumber", a);
        }
        if (b != null) {
            updateParameters.put("MeetingID", b);
        }
        if (c != null) {
            updateParameters.put("MeetingDate", c);
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Meeting", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Meeting", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Meeting View";
    }
}
