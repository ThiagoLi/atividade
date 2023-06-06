package inf300.domain;

import inf300.domain.exception.StockException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class CartLineTest {

    CartLine cartLine1;

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
        address1 = new Address(1, "", "", "", "", "", new Country(1, "Brasil", "BRL", 1), "", "", "");
        store1 = new Store(1, "Store 1", "19123321");
        item1 = new TItem(1, "item1");
        stock1 = new Stock(1, address1, store1);
        is1 = new ItemStock(1, item1, stock1, 100, 1);

        cartLine1 = new CartLine(1, is1);
    }

    @Test
    public void testHashcode() {
        Assert.assertEquals(7, cartLine1.hashCode());
    }

    @Test
    public void testEquals() {
        Assert.assertTrue(cartLine1.equals(new CartLine(1, is1)));
        Assert.assertTrue(cartLine1.equals(cartLine1));
        Assert.assertFalse(cartLine1.equals(new CartLine(2, is1)));
        Assert.assertFalse(cartLine1.equals(new CartLine(1, new ItemStock(2, item1,stock1, 123, 1))));
        Assert.assertFalse(cartLine1.equals(null));
        Assert.assertFalse(cartLine1.equals(new StockException("Error")));
    }

    @Test
    public void testToString() {
        Assert.assertEquals("CartLine{qty=1, itemStock=ItemStock{id=1, item=Item{id=1, title=item1}, stock=Stock{id=1, address=Address{id=1, street1=, street2=, city=, state=, zip=, country=Country{id=1, name=Brasil, currency=BRL, exchange=1.0}, latitude=, longitude=, buildingNumber=}, store=Store{id=1, name=Store 1, phone=19123321}}, cost=100.0, qty=1}}", cartLine1.toString());
    }

}
