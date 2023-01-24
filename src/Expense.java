
import java.sql.Date;

public class Expense {
    private int apartmentNumber;
    private int expenseID;
    private String description;
    private String date;
    private int amount;

    public Expense(int apartmentNumber, String description, String date, int amount) {
        this.apartmentNumber = apartmentNumber;
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    public int getExpenseID() {
        return expenseID;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ApartmentNumber": return apartmentNumber;
            case "ExpenseID": return expenseID;
            case "Description": return description;
            case "Date": return date;
            case "Amount": return amount;
            default: return null;
        }
    }
    
    @Override
    public String toString() {
        return "apartmentNumber=" + apartmentNumber + ", expenseID=" + expenseID + ", description=" + description + ", date=" + date + ", amount=" + amount;
    }
    
    
}
