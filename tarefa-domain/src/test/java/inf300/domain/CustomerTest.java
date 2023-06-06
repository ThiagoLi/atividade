package inf300.domain;

import inf300.domain.exception.StockException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class CustomerTest {

    private Date date1;
    private Date date2;
    private Address address1;
    private Address address2;
    private Customer customer1;
    private Order order1;

    @Before
    public void setUp() {
        date1 = new Date(123456l);
        date2 = new Date(123410l);
        address1 = new Address(1, "", "", "", "", "", new Country(1, "Brasil", "BRL", 1), "", "", "");
        address2 = new Address(2, "", "", "", "", "", new Country(1, "Brasil", "BRL", 1), "", "", "");
        customer1 = new Customer(1, "thiagolima", "pass", "Thiago", "Lima", "12992220987", "thiago.mail@live.com", date1, date1, date1, date1, 0, 100, 1, date1, "", address1);
        order1 = new Order(1, customer1, date1,  new Cart(1, date1), "", ShipType.MAIL, date1, OrderState.DELIVERED, address1, address1, null);
    }

    @Test
    public void testHash() {
        Assert.assertEquals(146, customer1.hashCode());
    }

    @Test
    public void testGetMostRecentOrder() {
        customer1.logOrder(order1);
        Assert.assertEquals(order1, customer1.getMostRecentOrder());
    }


    @Test
    public void testGetDiscount() {
        Assert.assertEquals(0, customer1.getDiscount(), 0);
    }

    @Test
    public void testGetSince() {
        Assert.assertEquals(date1, customer1.getSince());
    }

    @Test
    public void testGetLastVisit() {
        Assert.assertEquals(date1, customer1.getLastVisit());
    }

    @Test
    public void testGetLogin() {
        Assert.assertEquals(date1, customer1.getLogin());
    }

    @Test
    public void testGetExpiration() {
        Assert.assertEquals(date1, customer1.getExpiration());
    }

    @Test
    public void testGetBalance() {
        Assert.assertEquals(100, customer1.getBalance(), 0);
    }

    @Test
    public void testGetYtdPmt() {
        Assert.assertEquals(1, customer1.getYtdPmt(), 0);
    }

    @Test
    public void testGetBirthdate() {
        Assert.assertEquals(date1, customer1.getBirthdate());
    }

    @Test
    public void testGetData() {
        Assert.assertEquals("", customer1.getData());
    }

    @Test
    public void testEquals() {
        Assert.assertTrue(customer1.equals(new Customer(1, "thiagolima", "pass", "Thiago", "Lima", "12992220987", "thiago.mail@live.com", date1, date1, date1, date1, 0, 100, 1, date1, "", address1)));
        Assert.assertFalse(customer1.equals(null));
        Assert.assertFalse(customer1.equals(new StockException("error")));
        Assert.assertFalse(customer1.equals(new Customer(2, "thiagolima", "pass", "Thiago", "Lima", "12992220987", "thiago.mail@live.com", date1, date1, date1, date1, 0, 100, 1, date1, "", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima2", "pass", "Thiago", "Lima", "12992220987", "thiago.mail@live.com", date1, date1, date1, date1, 0, 100, 1, date1, "", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima", "pass2", "Thiago", "Lima", "12992220987", "thiago.mail@live.com", date1, date1, date1, date1, 0, 100, 1, date1, "", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima", "pass", "Thiago2", "Lima", "12992220987", "thiago.mail@live.com", date1, date1, date1, date1, 0, 100, 1, date1, "", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima", "pass", "Thiago", "Lima2", "12992220987", "thiago.mail@live.com", date1, date1, date1, date1, 0, 100, 1, date1, "", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima", "pass", "Thiago", "Lima", "19999999992", "thiago.mail@live.com", date1, date1, date1, date1, 0, 100, 1, date1, "", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima", "pass", "Thiago", "Lima", "12992220987", "thiago2@gmail.com", date1, date1, date1, date1, 0, 100, 1, date1, "", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima", "pass", "Thiago", "Lima", "12992220987", "thiago.mail@live.com", date2, date1, date1, date1, 0, 100, 1, date1, "", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima", "pass", "Thiago", "Lima", "12992220987", "thiago.mail@live.com", date1, date2, date1, date1, 0, 100, 1, date1, "", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima", "pass", "Thiago", "Lima", "12992220987", "thiago.mail@live.com", date1, date1, date2, date1, 0, 100, 1, date1, "", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima", "pass", "Thiago", "Lima", "12992220987", "thiago.mail@live.com", date1, date1, date1, date2, 0, 100, 1, date1, "", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima", "pass", "Thiago", "Lima", "12992220987", "thiago.mail@live.com", date1, date1, date1, date1, 1, 100, 1, date1, "", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima", "pass", "Thiago", "Lima", "12992220987", "thiago.mail@live.com", date1, date1, date1, date1, 0, 102, 1, date1, "", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima", "pass", "Thiago", "Lima", "12992220987", "thiago.mail@live.com", date1, date1, date1, date1, 0, 100, 2, date1, "", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima", "pass", "Thiago", "Lima", "12992220987", "thiago.mail@live.com", date1, date1, date1, date1, 0, 100, 1, date2, "", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima", "pass", "Thiago", "Lima", "12992220987", "thiago.mail@live.com", date1, date1, date1, date1, 0, 100, 1, date1, "2", address1)));
        Assert.assertFalse(customer1.equals(new Customer(1, "thiagolima", "pass", "Thiago", "Lima", "12992220987", "thiago.mail@live.com", date1, date1, date1, date1, 0, 100, 1, date1, "", address2)));
    }

    @Test
    public void testToString() {
        Assert.assertEquals("Customer{id=1, uname=thiagolima, passwd=pass, fname=Thiago, lname=Lima, phone=12992220987, email=thiago.mail@live.com, since=Wed Dec 31 21:02:03 BRT 1969, lastVisit=Wed Dec 31 21:02:03 BRT 1969, login=Wed Dec 31 21:02:03 BRT 1969, expiration=Wed Dec 31 21:02:03 BRT 1969, discount=0.0, balance=100.0, ytdPmt=1.0, birthdate=Wed Dec 31 21:02:03 BRT 1969, data=, address=Address{id=1, street1=, street2=, city=, state=, zip=, country=Country{id=1, name=Brasil, currency=BRL, exchange=1.0}, latitude=, longitude=, buildingNumber=}}", customer1.toString());
    }
}
