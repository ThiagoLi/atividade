package inf300.domain;
import org.junit.Test;
import static org.junit.Assert.*;

public class StoreTest {
	@Test
    public void testEquals() {
        Store store1 = new Store(1, "Lojinha 1", "15992204313");
        Store store2 = new Store(1, "Lojinha 2", "01234567890");
        Store store3 = new Store(2, "Lojinha 3", "123456789");

        assertEquals(store1, store2); 
        assertNotEquals(store1, store3); 
    }

    @Test
    public void testHashCode() {
        Store store1 = new Store(1, "Lojinha 1", "15992204313");
        Store store2 = new Store(1, "Lojinha 2", "01234567890");

        assertEquals(store1.hashCode(), store2.hashCode()); 
    }

    @Test
    public void testToString() {
        Store store = new Store(1, "Lojinha 1", "15992204313");

        String expectedToString = "Store{id=1, name=Lojinha 1, phone=15992204313}";
        assertEquals(expectedToString, store.toString());
    }

}
