package inf300.service.spec;

import inf300.domain.Address;
import inf300.domain.Customer;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

/**
 *
 * @author esoft
 */
public interface CustomerService extends Serializable {

     Customer createCustomer(String fname, String lname, String street1, String street2, String city, String state, String zip, String countryName, String latitude, String longitude, String buildingNumber, String phone, String email, double discount, Date birthdate, String data, long now);

    Customer createCustomer(String fname, String lname, Address address, String phone, String email, Date since, Date lastVisit, Date login, Date expiration, double discount, Date birthdate, String data);

    Customer getACustomerAnyCustomer(Random random);

    Customer getCustomer(int cId);

    Optional<Customer> getCustomer(String username);
    
}
