package inf300.domain;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderStateTest {
	@Test
    public void testEnumValues() {
        assertEquals(OrderState.NEW, OrderState.valueOf("NEW"));
        assertEquals(OrderState.PENDING_ACKNOWLEDGMENT_PAYMENT, OrderState.valueOf("PENDING_ACKNOWLEDGMENT_PAYMENT"));
        assertEquals(OrderState.PAID_OUT, OrderState.valueOf("PAID_OUT"));
        assertEquals(OrderState.PAYMENT_EXCEPTION, OrderState.valueOf("PAYMENT_EXCEPTION"));
        assertEquals(OrderState.SHIPPED, OrderState.valueOf("SHIPPED"));
        assertEquals(OrderState.CANCELLED, OrderState.valueOf("CANCELLED"));
        assertEquals(OrderState.DELIVERED, OrderState.valueOf("DELIVERED"));
    }

    @Test
    public void testGetInfo() {
        assertEquals("", OrderState.NEW.getInfo());
        assertEquals("", OrderState.PENDING_ACKNOWLEDGMENT_PAYMENT.getInfo());
        assertEquals("", OrderState.PAID_OUT.getInfo());
        assertEquals("", OrderState.PAYMENT_EXCEPTION.getInfo());
        assertEquals("", OrderState.SHIPPED.getInfo());
        assertEquals("", OrderState.CANCELLED.getInfo());
        assertEquals("", OrderState.DELIVERED.getInfo());
    }

    @Test
    public void testGetGroupState() {
        assertEquals("Order", OrderState.NEW.getGroupState());
        assertEquals("Order", OrderState.PENDING_ACKNOWLEDGMENT_PAYMENT.getGroupState());
        assertEquals("Order", OrderState.PAID_OUT.getGroupState());
        assertEquals("Order", OrderState.PAYMENT_EXCEPTION.getGroupState());
        assertEquals("", OrderState.SHIPPED.getGroupState());
        assertEquals("", OrderState.CANCELLED.getGroupState());
        assertEquals("", OrderState.DELIVERED.getGroupState());
    }

}
