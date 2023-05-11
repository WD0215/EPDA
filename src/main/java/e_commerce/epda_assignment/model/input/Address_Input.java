package e_commerce.epda_assignment.model.input;
import e_commerce.epda_assignment.model.Address;
import lombok.Data;

@Data
public class Address_Input {
    private String id;
    private boolean isPrimary;
    private String userID;
    private String unit;
    private String city;
    private String street;
    private String state;
    private String country;
    private String zipcode;
    public Address toAddress(){
        Address location = new Address();
        location.setCity(city);
        location.setUserID(userID);
        location.setPrimary(isPrimary);
        location.setUnit(unit);
        location.setCountry(country);
        location.setStreet(street);
        location.setState(state);
        location.setZipcode(zipcode);
        return location;
    }
}
