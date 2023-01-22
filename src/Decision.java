
public class Decision {
    private int apartmentNumber;
    private int meetingID;
    private int decisionID;
    private String description;
    private String isApproved;
    private String isRequireFullVoting;

    public Decision(int apartmentNumber, int meetingID, String description, String isApproved, String isRequireFullVoting) {
        this.apartmentNumber = apartmentNumber;
        this.meetingID = meetingID;
        this.description = description;
        this.isApproved = isApproved;
        this.isRequireFullVoting = isRequireFullVoting;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ApartmentNumber": return apartmentNumber;
            case "MeetingID": return meetingID;
            case "DecisionID": return decisionID;
            case "Description": return description;
            case "IsApproved": return isApproved;
            case "IsRequireFullVoting": return isRequireFullVoting;
            default: return null;
        }
    }

    @Override
    public String toString() {
        return "apartmentNumber=" + apartmentNumber + ", meetingID=" + meetingID + ", decisionID=" + decisionID + ", description=" + description + ", isApproved=" + isApproved + ", isRequireFullVoting=" + isRequireFullVoting;
    }
    
    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public int getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(int meetingID) {
        this.meetingID = meetingID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    public String getIsRequireFullVoting() {
        return isRequireFullVoting;
    }

    public void setIsRequireFullVoting(String isRequireFullVoting) {
        this.isRequireFullVoting = isRequireFullVoting;
    }

    public int getDecisionID() {
        return decisionID;
    }
    
}
