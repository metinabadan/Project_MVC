
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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
                Date date = resultSet.getDate("Date");
                int type = resultSet.getInt("Type");
                String description = resultSet.getString("Description");
                String paymentID = resultSet.getString("PaymentID");
                String receipt = resultSet.getString("Receipt");
                // Display values
                System.out.print("ApartmentNumber : " +apartmentNumber + "\t");
                System.out.print("SSN : " +ssn + "\t");
                System.out.print("RoleID : " +roleID + "\t");
                System.out.print("Amount : " +amount + "\t");
                System.out.print("Date : " +date + "\t");
                System.out.print("Type : " +type + "\t");
                System.out.print("Description : " +description + "\t");
                System.out.print("PaymentID : " +paymentID + "\t");
                System.out.print("Receipt : " +receipt);
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
        Integer ssn = getInteger("SSN : ", true);
        Integer roleID = getInteger("RoleID : ", true);
        Integer amount = getInteger("Amount : ", true);
        String date = getString("Date  : ", true);
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
        parameters.put("fieldNames", "ApartmentNumber, SSN, RoleID, Amount, Date, Type, Description, Receipt");

        List<Object> rows = new ArrayList<>();
        Integer a,b,c,d;
        String f,e,g,h;
        do {
            System.out.println("Fields to insert:");
            a = getInteger("ApartmentNumber : ", true);
            b = getInteger("SSN : ", true);
            c = getInteger("RoleID : ", true);
            d = getInteger("Amount : ", true);
            e = getString("Date : ", true);
            f = getString("Type : ", true);
            g = getString("Description : ", true);
            h = getString("Receipt : ", true);
            
            System.out.println();

            if (a != null && b != null && c != null && d != null && e != null && g != null && h != null) {
                rows.add(new Payment(a, b, c, d, e,f ,g, h));
            }
        } while (a != null && b != null && c != null && d != null && e != null && g != null && h != null);

        parameters.put("rows", rows);

        return new ViewData("Payment", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer a = getInteger("ApartmentNumber : ", true);
        Integer b = getInteger("SSN : ", true);
        Integer c = getInteger("RoleID : ", true);
        Integer d = getInteger("Amount : ", true);
        String e = getString("Date : ", true);
        String f = getString("Type : ", true);
        String g = getString("Description : ", true);
        String h = getString("Receipt : ", true);
        
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (a != null) {
            updateParameters.put("ApartmentNumber", a);
        }
        if (b != null) {
            updateParameters.put("SSN", b);
        }
        if (c != null) {
            updateParameters.put("RoleID", c);
        }
        if (d != null) {
            updateParameters.put("Amount", d);
        }
        if (e != null) {
            updateParameters.put("Date", e);
        }
        if (f != null) {
            updateParameters.put("Type", f);
        }
        if (g != null) {
            updateParameters.put("Description", g);
        }
        if (h != null) {
            updateParameters.put("Receipt", h);
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
