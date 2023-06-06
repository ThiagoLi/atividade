package inf300.domain;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShipTypeTest {
	@Test
    public void testEnumValues() {
        assertEquals(ShipType.AIR, ShipType.valueOf("AIR"));
        assertEquals(ShipType.UPS, ShipType.valueOf("UPS"));
        assertEquals(ShipType.FEDEX, ShipType.valueOf("FEDEX"));
        assertEquals(ShipType.SHIP, ShipType.valueOf("SHIP"));
        assertEquals(ShipType.COURIER, ShipType.valueOf("COURIER"));
        assertEquals(ShipType.MAIL, ShipType.valueOf("MAIL"));
    }

    @Test
    public void testToString() {
        assertEquals("AIR", ShipType.AIR.toString());
        assertEquals("UPS", ShipType.UPS.toString());
        assertEquals("FEDEX", ShipType.FEDEX.toString());
        assertEquals("SHIP", ShipType.SHIP.toString());
        assertEquals("COURIER", ShipType.COURIER.toString());
        assertEquals("MAIL", ShipType.MAIL.toString());
    }
}
