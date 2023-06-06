package inf300.service.impl;

import inf300.domain.Address;
import inf300.domain.Customer;
import inf300.service.spec.AddressService;
import inf300.service.spec.CustomerService;
import inf300.util.RandomUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * CustomerServiceImpl class implements the CustomerService interface and
 * provides methods for customer retrieval and creation. It leverages data
 * structures to efficiently store and retrieve customer objects, and it depends
 * on the AddressService interface for address-related functionality.
 *
 * @author esoft
 */
public final class CustomerServiceImpl implements CustomerService {

    private static CustomerService instance;

    private final List<Customer> customersById;
    private final Map<String, Customer> customersByUsername;
    private final AddressService addressService;

    /**
     * The class implements a singleton pattern to ensure that only one instance
     * of CustomerServiceImpl is created. The instance variable holds the
     * singleton instance, and the getInstance() method returns that instance.
     * 
     * 
     * Obs: AddressService Dependency: The class has a dependency on the
     * AddressService interface, which is passed as a parameter to the
     * getInstance() method or the constructor. This dependency is stored in the
     * addressService variable.
     *
     * @param addressService
     * @return
     */
    public synchronized static CustomerService getInstance(AddressService addressService) {
        if (instance == null) {
            instance = new CustomerServiceImpl(addressService);
        }
        return instance;
    }

    private CustomerServiceImpl(AddressService addressService) {
        this(new ArrayList<>(), new HashMap<>(), addressService);

    }

    private CustomerServiceImpl(List<Customer> customersById, Map<String, Customer> customersByUsername, AddressService addressService) {
        this.customersById = customersById;
        this.customersByUsername = customersByUsername;
        this.addressService = addressService;
    }

    @Override
    public Customer getCustomer(int cId) {
        return (cId >= customersById.size()) ? null : customersById.get(cId);
    }

    @Override
    public Optional<Customer> getCustomer(String username) {
        return Optional.ofNullable(customersByUsername.get(username));
    }

    @Override
    public Customer getACustomerAnyCustomer(Random random) {
        return customersById.get(random.nextInt(customersById.size()));
    }

    @Override
    public Customer createCustomer(String fname, String lname, String street1,
            String street2, String city, String state, String zip,
            String countryName, String latitude, String longitude,
            String buildingNumber, String phone, String email, double discount,
            Date birthdate, String data, long now) {
        Address address = addressService.alwaysGetAddress(street1, street2, city, state, zip,
                countryName, latitude, longitude, buildingNumber);
        return createCustomer(fname, lname, address, phone, email,
                new Date(now), new Date(now), new Date(now),
                new Date(now + 7200000 /* 2 hours */), discount, birthdate,
                data);
    }

    @Override
    public synchronized Customer createCustomer(String fname, String lname, Address address,
            String phone, String email, Date since, Date lastVisit,
            Date login, Date expiration, double discount, Date birthdate,
            String data) {
        int id = customersById.size();
        String uname = RandomUtil.DigSyl(id, 0);
        Customer customer = new Customer(id, uname, uname.toLowerCase(), fname,
                lname, phone, email, since, lastVisit, login, expiration,
                discount, 0, 0, birthdate, data, address);
        customersById.add(customer);
        customersByUsername.put(uname, customer);
        return customer;
    }

}
