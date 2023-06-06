package inf300.domain;

/* 
 * Address.java - Stores an address.
 *
 ************************************************************************
 *
 *
 ************************************************************************/
import java.io.Serializable;
import java.util.Objects;

/**
 * <pre>
 * This is a Java class called "Address" that represents a physical address. It
 * contains the following attributes:
 *
 * id: an integer representing the unique identifier for the address.
 *
 * street1: a string representing the first line of the address, typically the
 * street name and number.
 *
 * street2: a string representing the second line of the address, typically
 * additional address information.
 *
 * city: a string representing the city where the address is located.
 *
 * state: a string representing the state where the address is located.
 *
 * zip: a string representing the postal code or zip code of the address.
 *
 * country: a Country object representing the country where the address is
 * located.
 *
 * latitude: a string representing the latitude of the address.
 *
 * longitude: a string representing the longitude of the address.
 *
 * buildingNumber: a string representing the building number of the address.
 *
 * The class provides methods to get the values of each attribute, as well as
 * methods to check for equality with another Address object and to calculate a
 * hash code for the object. The class also implements the Serializable
 * interface, which allows instances of the class to be serialized and
 * deserialized.
 * </pre>
 *
 * *<img src="./doc-files/Address.png" alt="Address">
 */
public class Address implements Serializable {

    private static final long serialVersionUID = 3980790290181121772L;

    private final int id;
    private final String street1;
    private final String street2;
    private final String city;
    private final String state;
    private final String zip;
    private final Country country;
    private final String latitude;
    private final String longitude;
    private final String buildingNumber;

    public Address(int id, String street1, String street2, String city, String state, String zip, Country country, String latitude, String longitude, String buildingNumber) {
        this.id = id;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.buildingNumber = buildingNumber;
    }

    public int getId() {
        return id;
    }

    public String getStreet1() {
        return street1;
    }

    public String getStreet2() {
        return street2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.street1, other.street1)) {
            return false;
        }
        if (!Objects.equals(this.street2, other.street2)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zip, other.zip)) {
            return false;
        }
        return Objects.equals(this.country, other.country);
    }



    @Override
    public int hashCode() {
        return street1.hashCode() + street2.hashCode() + city.hashCode()
                + state.hashCode() + zip.hashCode() + country.hashCode();
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", street1=" + street1 + ", street2=" + street2 + ", city=" + city + ", state=" + state + ", zip=" + zip + ", country=" + country + ", latitude=" + latitude + ", longitude=" + longitude + ", buildingNumber=" + buildingNumber + '}';
    }
    
    
}
