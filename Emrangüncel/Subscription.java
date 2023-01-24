
import java.sql.Date;

public class Subscription {

    private int apartmentNumber;
    private int subscriptionID;
    private Date startDate;
    private Date endDate;
    private int amount;
    private int typeID;

    public Subscription(int ApartmentNumber, Date StartDate, Date EndDate, int Amount, int TypeID) {
        this.apartmentNumber = ApartmentNumber;
        this.startDate = StartDate;
        this.endDate = EndDate;
        this.amount = Amount;
        this.typeID = TypeID;
    }

    @Override
    public String toString() {
        return "apartmentNumber=" + apartmentNumber + ", subscriptionID=" + subscriptionID + ", startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount + ", typeID=" + typeID;
    }

    public int getSubscriptionID() {
        return subscriptionID;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ApartmentNumber": return apartmentNumber;
            case "SubscriptionID": return subscriptionID;
            case "StartDate": return startDate;
            case "EndDate": return endDate;
            case "Amount": return amount;
            case "TypeID": return typeID;
            default: return null;
        }
    }

}
