package inf300.domain;

import inf300.domain.exception.StockException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemStockTest {

    ItemStock is1;
    TItem item1;
    TItem item2;
    Stock stock1;
    Stock stock2;
    Address address1;
    Store store1;

    class TItem extends Item {
        public TItem(int id, String title) {
            super(id, title);
        }
    }


    @Before
    public void setUp() {
        address1 = new Address(1, "", "", "", "", "", new Country(1, "Brasil", "BRL", 1), "", "", "");
        store1 = new Store(1, "Store 1", "19123321");
        item1 = new TItem(1, "item1");
        item2 = new TItem(2, "item2");
        stock1 = new Stock(1, address1, store1);
        stock2 = new Stock(2, address1, store1);
        is1 = new ItemStock(1, item1, stock1, 100, 1);
    }

    @Test
    public void testGetQuantityOnHand() {
        Assert.assertEquals(1, is1.getQuantityOnHand());
    }

    @Test
    public void testAddQty() {
        try {
            is1.addQty(1);
            Assert.assertEquals(2, is1.getQuantityOnHand());
        } catch (StockException e) {
        }

        StockException thrown = Assert.assertThrows(
                StockException.class,
                () -> is1.addQty(-3));
        Assert.assertEquals(thrown.getMessage(), "Insufficient stock.");
    }

    @Test
    public void testGetItem() {
        Assert.assertEquals(item1, is1.getItem());
    }

    @Test
    public void testGetStock() {
        Assert.assertEquals(stock1, is1.getStock());
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(582, is1.hashCode());
    }

    @Test
    public void testEquals() {
        Assert.assertTrue(is1.equals(new ItemStock(1, item1, stock1, 100, 1)));
        Assert.assertFalse(is1.equals(null));
        Assert.assertFalse(is1.equals(new StockException("error")));
        Assert.assertFalse(is1.equals(new ItemStock(2, item1, stock1, 100, 1)));
        Assert.assertFalse(is1.equals(new ItemStock(1, item2, stock1, 100, 1)));
        Assert.assertFalse(is1.equals(new ItemStock(1, item1, stock2, 100, 1)));
        Assert.assertFalse(is1.equals(new ItemStock(1, item1, stock1, 101, 1)));
        Assert.assertFalse(is1.equals(new ItemStock(1, item1, stock1, 100, 2)));
    }


    @Test
    public void testToString() {
        Assert.assertEquals("ItemStock{id=1, item=Item{id=1, title=item1}, stock=Stock{id=1, address=Address{id=1, street1=, street2=, city=, state=, zip=, country=Country{id=1, name=Brasil, currency=BRL, exchange=1.0}, latitude=, longitude=, buildingNumber=}, store=Store{id=1, name=Store 1, phone=19123321}}, cost=100.0, qty=1}", is1.toString());
    }


}
