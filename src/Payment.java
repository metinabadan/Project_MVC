
import java.sql.Date;

public class Payment {
    private int paymentID;
    private int apartmentNumber;
    private int ssn;
    private int roleID;
    private int amount;
    private Date date;
    private String type;
    private String description;
    private String receipt;

    public Payment(int apartmentNumber, int ssn, int roleID, int amount, Date date, String type, String description, String receipt) {
        this.apartmentNumber = apartmentNumber;
        this.ssn = ssn;
        this.roleID = roleID;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.description = description;
        this.receipt = receipt;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ApartmentNumber": return apartmentNumber;
            case "SSN": return ssn;
            case "RoleID": return roleID;
            case "Amount": return amount;
            case "Date": return date;
            case "Type": return type;
            case "Description": return description;
            case "PaymentID": return paymentID;
            case "Receipt": return receipt;
            default: return null;
        }
    }
    
    public int getPaymentID() {
        return paymentID;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
    
    
}
