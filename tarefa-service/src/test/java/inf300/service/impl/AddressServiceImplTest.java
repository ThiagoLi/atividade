package inf300.service.impl;

import inf300.domain.Address;
import inf300.domain.Country;
import inf300.service.impl.AddressServiceImpl;
import inf300.service.spec.AddressService;
import inf300.service.spec.CountryService;
import java.util.Random;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressServiceImplTest {

    private AddressService addressService;
    private CountryService countryService;

    @Before
    public void setup() {
        // Create a mock implementation of CountryService for testing
        countryService = new MockCountryService();
        // Create an instance of AddressServiceImpl for testing
        addressService = AddressServiceImpl.getInstance(countryService);
    }

    @Test
    public void testCreateAddress() {
        // Create a new address
        Address address = addressService.createAddress("123 Main St", "", "Anytown", "CA", "12345",
                new Country(1, "United States", "USD", 1.0), "", "", "");

        // Verify that the address was created successfully
        Assert.assertNotNull(address);
        Assert.assertEquals("123 Main St", address.getStreet1());
        Assert.assertEquals("", address.getStreet2());
        Assert.assertEquals("Anytown", address.getCity());
        Assert.assertEquals("CA", address.getState());
        Assert.assertEquals("12345", address.getZip());
    }

    @Test
    public void testAlwaysGetAddress() {
        // Create a new address
        Address address = addressService.alwaysGetAddress("123 Main St", "", "Anytown", "CA", "12345",
                "United States", "", "", "");

        // Verify that the address was retrieved or created successfully
        Assert.assertNotNull(address);
        Assert.assertEquals("123 Main St", address.getStreet1());
        Assert.assertEquals("", address.getStreet2());
        Assert.assertEquals("Anytown", address.getCity());
        Assert.assertEquals("CA", address.getState());
        Assert.assertEquals("12345", address.getZip());
    }

    // Define a mock implementation of CountryService for testing
    private class MockCountryService implements CountryService {
        @Override
        public Country alwaysGetCountry(String countryName) {
            // Return a mock country object based on the countryName
            return new Country(1, "United States", "USD", 1.0);
        }

        @Override
        public Country createCountry(String name, String currency, double exchange) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Country getACountryAnyCountry(Random random) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Country getCountryById(int cId) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public Country getCountryByName(String countryName) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public int getCountCountries() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
