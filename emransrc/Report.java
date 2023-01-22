
public class Report {
    private int apartmentNumber;
    private int reportID;
    private int typeID;

    public Report(int apartmentNumber, int typeID) {
        this.apartmentNumber = apartmentNumber;
        this.typeID = typeID;
    }
    
    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ApartmentNumber": return apartmentNumber;
            case "ReportID": return reportID;
            case "TypeID": return typeID;
            default: return null;
        }
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public int getReportID() {
        return reportID;
    }

    @Override
    public String toString() {
        return "apartmentNumber=" + apartmentNumber + ", reportID=" + reportID + ", typeID=" + typeID;
    }
    
    
}
