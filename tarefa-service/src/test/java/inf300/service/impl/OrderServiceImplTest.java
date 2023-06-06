package inf300.service.impl;

import inf300.domain.Address;
import inf300.domain.Author;
import inf300.domain.Backing;
import inf300.domain.Book;
import inf300.domain.CCTransaction;
import inf300.domain.Cart;
import inf300.domain.Country;
import inf300.domain.CreditCard;
import inf300.domain.Customer;
import inf300.domain.ItemStock;
import inf300.domain.Order;
import inf300.domain.OrderState;
import inf300.domain.ShipType;
import inf300.domain.Stock;
import inf300.domain.Store;
import inf300.domain.Subject;
import inf300.service.spec.BookService;
import inf300.service.spec.OrderService;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderServiceImplTest {

    private OrderService orderService;
    private BookService bookService;

    @Before
    public void setup() {
        // implementation of BookService 
        bookService = BookServiceImpl.getInstance();
        // Create an instance of OrderServiceImpl for testing
        orderService = OrderServiceImpl.getInstance(bookService);
    }

    @Test
    public void testCreateOrder() {
        // Create a new order
        Country country = new Country(1, "USA", "DOL", 1.0);
        Address address = new Address(1, "123 Main St", "", "Anytown", "CA", "12345", country, "", "", "");
        Book book = createBook();
        ItemStock itemStock = createItemStock(address, book);
        CCTransaction cc = createCCTransaction();

        Date date = new Date(0); // Create a mock date object
        
        String comment = "Test order";
        ShipType shipType = ShipType.AIR; // Create a mock ship type
        Date shipDate = new Date(0); // Create a mock ship date
        OrderState status = OrderState.DELIVERED; // Create a mock order state
        Cart cart = new Cart(1, new Date(0)); // Create a mock cart object
        int cartQty = 5000;
        cart.changeLine(itemStock, cartQty);
        Country countryCustomer = new Country(2, "USA", "DOL", 1.0);
        Address billingAddress = new Address(2, "123 Main St", "", "Anytown", "CA", "12345", countryCustomer, "", "", "");
        Address shippingAddress = new Address(2, "123 Main St", "", "Anytown", "CA", "12345", countryCustomer, "", "", "");
        Customer customer = new Customer(1, "jdoe", "password", "John", "Doe", "555-1234", "jdoe@example.com",
                new Date(2022, 1, 1), new Date(2022, 2, 1), null, null, 0.0, 0.0, 0.0,
                new Date(2000, 1, 1), "data", address);
        int countOrder = orderService.getCountOrders();
        Order order = orderService.createOrder(customer, date, cart, comment, shipType, shipDate, status, billingAddress, shippingAddress, cc);
        // Verify that the order was created successfully
        assertNotNull(order);
        assertEquals(date, order.getDate());
        assertEquals(shipType, order.getShipType());
        assertEquals(shipDate, order.getShipDate());
        assertEquals(status, order.getStatus());
        assertEquals(billingAddress, order.getBillingAddress());
        assertEquals(shippingAddress, order.getShippingAddress());
        assertEquals(customer, order.getCustomer());
        assertEquals(itemStock.getId(), order.getLines().get(0).getItemStock().getId());
        assertEquals(cartQty, order.getLines().get(0).getQty());
        assertEquals(itemStock.getItem().getId(), order.getLines().get(0).getItemStock().getItem().getId());
        assertEquals(cc, order.getCC());
        assertEquals(countOrder, order.getId());
        //assertEquals(book.getAuthor(), orderService.getTopAuthorBySubject(Subject.ARTS, 1).get(0).getAuthor());
        //assertEquals(cartQty, orderService.getTopAuthorBySubject(Subject.ARTS, 1).get(0).getCount());
    }

    private ItemStock createItemStock(Address address, Book book) {
        // Create an example ItemStock with 10 quantity and $5 cost
        ItemStock itemStock = new ItemStock(0, book, new Stock(0, address, new Store(1, "ABC Store", "123-456-7890")), 5.0, 10);
        return itemStock;
    }

    private Book createBook() {
        Author author = new Author("fname", "mname", "lname", new Date(0), "bio");
        Book book = bookService.createBook("Test Book", new Date(0), "Test Publisher", Subject.ARTS,
                "Test Description", null, null, 10.0, null, "Test ISBN",
                100, Backing.PAPERBACK, "Test Dimensions", author);
        return book;
    }

    private Book createBook(Author author, Subject subject) {

        Book book = bookService.createBook("Test Book", new Date(), "Test Publisher", subject,
                "Test Description", null, null, 10.0, null, "Test ISBN",
                100, Backing.PAPERBACK, "Test Dimensions", author);
        return book;
    }

    private CCTransaction createCCTransaction() {
        CreditCard type = CreditCard.VISA;
        long num = 1234567890123456L;
        String name = "John Doe";
        Date expire = new Date(0);
        String authId = "12345";
        double amount = 100.0;
        Date date1 = new Date(0);
        Country country2 = new Country(2, "France", "EUR", 1.0);
        CCTransaction cc = new CCTransaction(type, num, name, expire, authId, amount, date1, country2);
        return cc;
    }

}
