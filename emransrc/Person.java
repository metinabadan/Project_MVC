
import java.math.BigInteger;

public class Person{
    private BigInteger ssn;
    private String name;
    private String email;
    private String phone;
    private int roleID;
    private int flatNumber;

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "SSN": return ssn;
            case "Name": return name;
            case "Email": return email;
            case "Phone": return phone;
            case "RoleID": return roleID;
            case "FlatNumber": return flatNumber;
            default: return null;
        }
    }
    
    public Person(String name, String email, String phone, int roleID, int flatNumber) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.roleID = roleID;
        this.flatNumber = flatNumber;
    }
    
    public BigInteger getSsn() {
        return ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }
    
    
    
}
