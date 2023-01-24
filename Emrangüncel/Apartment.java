
class Apartment {

    private int ApartmentNumber;
    private String Name;
    private String Address;

    Apartment() {

    }

    Apartment(String Name, String Adress) {
        this.Name = Name;
        this.Address = Adress;
    }

    public String getAdress() {
        return Address;
    }

    public String getName() {
        return Name;
    }

    public int getApartmentNumber() {
        return ApartmentNumber;
    }

    public void setAdress(String address) {
        Address = address;
    }

    public void setName(String name) {
        Name = name;
    }

    public Object getByName(String attributeName) {
        switch (attributeName) {
            case "ApartmentNumber":
                return this.ApartmentNumber;
            case "Name":
                return this.Name;
            case "Address":
                return this.Address;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Apartment Number: " + this.ApartmentNumber + " ApartmentName: " + this.Name + " Adress: " + this.Address;
    }
}
