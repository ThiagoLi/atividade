package inf300.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockTest {
	private Stock stock;

    @Before
    public void setUp() {
        // Criar instâncias de Address, Store e Stock para usar nos testes
        Country country = new Country(1, "Country", "Currency", 1.0);
        Address address = new Address(1, "Street 1", "Street 2", "City", "State", "Zip", country, "Latitude", "Longitude", "Building Number");
        Store store = new Store(1, "Store", "Phone");
        stock = new Stock(1, address, store);
    }

    @Test
    public void testGetId() {
        int expectedId = 1;
        int actualId = stock.getId();
        Assert.assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetAddress() {
        Address expectedAddress = new Address(1, "Street 1", "Street 2", "City", "State", "Zip", new Country(1, "Country", "Currency", 1.0), "Latitude", "Longitude", "Building Number");
        Address actualAddress = stock.getAddress();
        Assert.assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void testEquals() {
        Stock sameStock = new Stock(1, new Address(1, "Street 1", "Street 2", "City", "State", "Zip", new Country(1, "Country", "Currency", 1.0), "Latitude", "Longitude", "Building Number"), new Store(1, "Store", "Phone"));
        Stock differentStock = new Stock(2, new Address(2, "Different Street 1", "Different Street 2", "Different City", "Different State", "Different Zip", new Country(2, "Different Country", "Different Currency", 2.0), "Different Latitude", "Different Longitude", "Different Building Number"), new Store(2, "Different Store", "Different Phone"));

        Assert.assertEquals(stock, sameStock);
        Assert.assertNotEquals(stock, differentStock);
    }
}
