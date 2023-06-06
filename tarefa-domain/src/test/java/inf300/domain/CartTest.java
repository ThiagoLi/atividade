package inf300.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;

public class CartTest {

    Cart cart1;
    Date date1;

    ItemStock is1;
    TItem item1;
    Stock stock1;
    Address address1;
    Store store1;

    class TItem extends Item {
        public TItem(int id, String title) {
            super(id, title);
        }
    }

    @Before
    public void setUp() {
        date1 = new Date(123456l);
        cart1 = new Cart(1, date1);

        address1 = new Address(1, "", "", "", "", "", new Country(1, "Brasil", "BRL", 1), "", "", "");
        store1 = new Store(1, "Store 1", "19123321");
        item1 = new TItem(1, "item1");
        stock1 = new Stock(1, address1, store1);
        is1 = new ItemStock(1, item1, stock1, 100, 1);
    }

    @Test
    public void testClear() {
        cart1.clear();
        Assert.assertTrue(cart1.getLines().isEmpty());
    }

    @Test
    public void testIncreaseLineAdd() {
        cart1.increaseLine(is1, 10);
        Assert.assertEquals(900, cart1.subTotal(10), 0);
        Assert.assertEquals(74.25, cart1.tax(10), 0);
        Assert.assertEquals(13, cart1.shipCost(), 0);
        Assert.assertEquals(987.25, cart1.total(10), 0);
        Assert.assertTrue(cart1.getLines().size() > 0);
    }

    @Test
    public void testIncreaseLineRemove() {
        cart1.increaseLine(is1, 0);
        Assert.assertEquals(0, cart1.subTotal(10), 0);
        Assert.assertEquals(0, cart1.tax(10), 0);
        Assert.assertEquals(3, cart1.shipCost(), 0);
        Assert.assertEquals(3, cart1.total(10), 0);
        Assert.assertTrue(cart1.getLines().isEmpty());
    }

    @Test
    public void testChangeLineAdd() {
        cart1.changeLine(is1, 10);
        Assert.assertEquals(900, cart1.subTotal(10), 0);
        Assert.assertEquals(74.25, cart1.tax(10), 0);
        Assert.assertEquals(13, cart1.shipCost(), 0);
        Assert.assertEquals(987.25, cart1.total(10), 0);
        Assert.assertTrue(cart1.getLines().size() > 0);
    }

    @Test
    public void testChangeLineRemove() {
        cart1.changeLine(is1, 0);
        Assert.assertEquals(0, cart1.subTotal(10), 0);
        Assert.assertEquals(0, cart1.tax(10), 0);
        Assert.assertEquals(3, cart1.shipCost(), 0);
        Assert.assertEquals(3, cart1.total(10), 0);
        Assert.assertTrue(cart1.getLines().isEmpty());
    }


}
