package inf300.service.impl;

import inf300.domain.Address;
import inf300.domain.Country;
import inf300.service.spec.AddressService;
import inf300.service.spec.CountryService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * AddressServiceImpl provides methods to retrieve addresses based on different
 * criteria, create new addresses, and ensure that only one instance of the
 * AddressService class is available (singleton pattern).
 *
 * @author esoft
 */
public final class AddressServiceImpl implements AddressService {

    private static AddressService instance;

    private final List<Address> addressById;
    private final Map<Address, Address> addressByAll;
    private final CountryService countryService;

    public synchronized static AddressService getInstance(CountryService countryService) {
        if (instance == null) {
            instance = new AddressServiceImpl(countryService);
        }
        return instance;
    }

    private AddressServiceImpl(CountryService countryService) {
        this(new ArrayList<Address>(), new HashMap<Address, Address>(), countryService);
    }

    private AddressServiceImpl(List<Address> addressById, Map<Address, Address> addressByAll, CountryService countryService) {
        this.addressById = addressById;
        this.addressByAll = addressByAll;
        this.countryService = countryService;
    }

    @Override
    public Address alwaysGetAddress(String street1, String street2,
            String city, String state, String zip, String countryName,
            String latitude, String longitude, String buildingNumber) {
        Country country = countryService.alwaysGetCountry(countryName);
        Address key = new Address(0, street1, street2, city, state, zip, country,
                latitude, longitude, buildingNumber);
        Address address = addressByAll.get(key);
        if (address == null) {
            address = createAddress(street1, street2, city, state, zip,
                    country, latitude, longitude, buildingNumber);
        }
        return address;
    }

    @Override
    public Address getAnAddressAnyAddress(Random random) {
        return addressById.get(random.nextInt(addressById.size()));
    }

    @Override
    public synchronized Address createAddress(String street1, String street2,
            String city, String state, String zip, Country country,
            String latitude, String longitude, String buildingNumber) {
        int id = addressById.size();
        Address address = new Address(id, street1, street2, city, state, zip, country, latitude, longitude, buildingNumber);
        addressById.add(address);
        addressByAll.put(address, address);
        return address;
    }

}
