
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportView implements ViewInterface{
    
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
                int reportID = resultSet.getInt("ReportID");
                int typeID = resultSet.getInt("TypeID");
;

                // Display values
                System.out.print("ApartmentNumber : " + apartmentNumber + "\t");
                System.out.print("ReportID : " + reportID + "\t");
                System.out.println("TypeID : " + typeID);
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
        Integer reportID = getInteger("Report ID : ", true);
        Integer typeID = getInteger("type ID: ", true);


        Map<String, Object> whereParameters = new HashMap<>();
        if (apartmentNumber != null) {
            whereParameters.put("ApartmentNumber", apartmentNumber);
        }
        if (reportID != null) {
            whereParameters.put("ReportID", reportID);
        }
        if (typeID != null) {
            whereParameters.put("TypeID", typeID);
        }

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Report", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "ApartmentNumber, TypeID");

        List<Object> rows = new ArrayList<>();

        Integer name, groupName;
        do {
            System.out.println("Fields to insert:");
            name = getInteger("ApartmentNumber : ", true);
            groupName = getInteger("TypeID : ", true);
            System.out.println();

            if (name != null && groupName != null) {
                rows.add(new Report(name, groupName));
            }
        } while (name != null && groupName != null);

        parameters.put("rows", rows);

        return new ViewData("Report", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer name = getInteger("ApartmentNumber : ", true);
        Integer groupName = getInteger("TypeID : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (name != null) {
            updateParameters.put("ApartmentNumber", name);
        }
        if (groupName != null) {
            updateParameters.put("TypeID", groupName);
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Report", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Report", "delete", parameters);
    }

    @Override
    public String toString() {
        return "Report View";
    }
}
