
import java.sql.Date;

public class Meeting {
    private int apartmentNumber;
    private int meetingID;
    private Date meetingDate;

    public Meeting(int apartmentNumber, Date meetingDate) {
        this.apartmentNumber = apartmentNumber;
        this.meetingDate = meetingDate;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ApartmentNumber": return apartmentNumber;
            case "MeetingID": return meetingID;
            case "MeetingDate": return meetingDate;
            default: return null;
        }
    }
    
    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public int getMeetingID() {
        return meetingID;
    }

    @Override
    public String toString() {
        return "apartmentNumber=" + apartmentNumber + ", meetingID=" + meetingID + ", meetingDate=" + meetingDate;
    }
    
    
}
