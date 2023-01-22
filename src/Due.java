
import java.sql.Date;

public class Due {
    
    private int apartmentNumber;
    private int dueID;
    private int amount;
    private Date date;

    public Due(int apartmentNumber, int amount, Date date) {
        this.apartmentNumber = apartmentNumber;
        this.amount = amount;
        this.date = date;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ApartmentNumber": return apartmentNumber;
            case "DueID": return dueID;
            case "Date": return date;
            case "Amount": return amount;
            default: return null;
        }
    }
    
    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
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

    public int getDueID() {
        return dueID;
    }

    @Override
    public String toString() {
        return "apartmentNumber=" + apartmentNumber + ", dueID=" + dueID + ", amount=" + amount + ", date=" + date;
    }
    
}
