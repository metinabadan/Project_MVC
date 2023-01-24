
public class Vote {
    
    private int apartmentNumber;
    private int voteID;
    private int ssn;
    private int meetingID;
    private int decisionID;
    private String vote;
    private int roleID;

    public Vote(int apartmentNumber, int ssn, int meetingID, int decisionID, String vote, int roleID) {
        this.apartmentNumber = apartmentNumber;
        this.ssn = ssn;
        this.meetingID = meetingID;
        this.decisionID = decisionID;
        this.vote = vote;
        this.roleID = roleID;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ApartmentNumber": return apartmentNumber;
            case "VoteID": return voteID;
            case "SSN": return ssn;
            case "MeetingID": return meetingID;
            case "DecisionID": return decisionID;
            case "Vote": return vote;
            case "RoleID": return roleID;
            default: return null;
        }
    }
    
    @Override
    public String toString() {
        return "ApartmentNumber=" + apartmentNumber + ", voteID=" + voteID + ", ssn=" + ssn + ", meetingID=" + meetingID + ", decisionID=" + decisionID + ", vote=" + vote + ", roleID=" + roleID;
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

    public int getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(int meetingID) {
        this.meetingID = meetingID;
    }

    public int getDecisionID() {
        return decisionID;
    }

    public void setDecisionID(int decisionID) {
        this.decisionID = decisionID;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getVoteID() {
        return voteID;
    }
    
    
    
}
