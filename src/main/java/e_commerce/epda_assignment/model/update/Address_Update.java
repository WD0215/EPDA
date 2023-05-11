package e_commerce.epda_assignment.model.update;

import e_commerce.epda_assignment.model.Address;
import lombok.Data;

@Data
public class Address_Update {
    private String addressID;
    private boolean isPrimary;
    private String userID;
    private String unit;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;

    public Address toAddress(Address address){
        address.setPrimary(isPrimary);
        address.setUnit(unit);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setZipcode(zipcode);
        return address;
    }
}
