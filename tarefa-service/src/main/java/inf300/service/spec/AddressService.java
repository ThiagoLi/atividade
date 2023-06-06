package inf300.service.spec;

import inf300.domain.Address;
import inf300.domain.Country;
import java.util.Random;

/**
 *
 * @author esoft
 */
public interface AddressService {

    Address alwaysGetAddress(String street1, String street2, String city, String state, String zip, String countryName, String latitude, String longitude, String buildingNumber);

    Address createAddress(String street1, String street2, String city, String state, String zip, Country country, String latitude, String longitude, String buildingNumber);

    Address getAnAddressAnyAddress(Random random);

}
