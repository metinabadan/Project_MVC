import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class ApartmentView implements ViewInterface {

	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception 
	{
		
		switch(operationName) 
		{
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
	
	ViewData selectOperation(ModelData modelData) throws Exception 
	{
		ResultSet resultSet = modelData.resultSet;
		
		if (resultSet != null) 
		{
			while (resultSet.next()) 
			{
				// Retrieve by column name
				int ApartmentNumber = resultSet.getInt("ApartmentNumber");
				String name = resultSet.getString("Name");
				String address = resultSet.getString("Address");
				
				// Display values
				System.out.print(ApartmentNumber + "\t");
				System.out.print(name + "\t");
				System.out.print(address + "\t");
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
		System.out.println("Filter conditions:");
		Integer apartmentNumber = getInteger("ApartmentNumber : ", true);
		String name = getString("Name : ", true);
		String address = getString("Address : ", true);
		
		Map<String, Object> whereParameters = new HashMap<>();
		if (apartmentNumber != null) whereParameters.put("ApartmentNumber", apartmentNumber);
		if (name != null) whereParameters.put("Name", name);
		if (address != null) whereParameters.put("Address", address);
		
		return whereParameters;
	}
	
	ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Apartment", "select", parameters);
	}

	ViewData insertGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fieldNames", "Name, Address");

		List<Object> rows = new ArrayList<>();
		
		String name, address;
		do
		{
			System.out.println("Fields to insert:");
			name = getString("Name : ", true);
			address = getString("Address : ", true);
			System.out.println();
					
			if (name != null && address != null) {
				rows.add(new Apartment(name, address));
			}
		}
		while (name != null && address != null);
		
		parameters.put("rows", rows);
		
		return new ViewData("Apartment", "insert", parameters);
	}

	ViewData updateGUI(ModelData modelData) throws Exception {
		System.out.println("Fields to update:");
		String name = getString("Name : ", true);
		String address = getString("Address : ", true);
		System.out.println();
		
		Map<String, Object> updateParameters = new HashMap<>();
		if (name != null) updateParameters.put("Name", name);
		if (address != null) updateParameters.put("Address", address);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("updateParameters", updateParameters);
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Apartment", "update", parameters);
	}

	ViewData deleteGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Apartment", "delete", parameters);
	}

	@Override
	public String toString() 
	{
		return "Apartment View";
	}		
}
