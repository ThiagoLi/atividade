package inf300.domain;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemTest {
	@Test
    public void testEquals() {
        Item item1 = new ConcreteItem(1, "Item 1");
        Item item2 = new ConcreteItem(1, "Item 1");
        Item item3 = new ConcreteItem(2, "Item 2");

        assertEquals(item1, item2); // Mesmo ID e Tilte
        assertNotEquals(item1, item3); // IDs diferentes
    }

    @Test
    public void testHashCode() {
        Item item1 = new ConcreteItem(1, "Item 1");
        Item item2 = new ConcreteItem(1, "Item 1");

        assertEquals(item1.hashCode(), item2.hashCode()); // Mesmo ID e Titulo
    }

    @Test
    public void testToString() {
        Item item = new ConcreteItem(1, "Item 1");

        String expectedToString = "Item{id=1, title=Item 1}";
        assertEquals(expectedToString, item.toString());
    }

   
    private class ConcreteItem extends Item {
        public ConcreteItem(int id, String title) {
            super(id, title);
        }
    }
}
