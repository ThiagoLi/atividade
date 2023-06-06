package inf300.facade.impl;

import inf300.facade.spec.BookMarketFacade;
import inf300.domain.Address;
import inf300.domain.Author;
import inf300.domain.Backing;
import inf300.domain.Book;
import inf300.domain.Cart;
import inf300.domain.CartLine;
import inf300.domain.Country;
import inf300.domain.Customer;
import inf300.domain.Order;
import inf300.domain.OrderState;
import inf300.domain.Subject;
import inf300.service.impl.AddressServiceImpl;
import inf300.service.impl.AuthorServiceImpl;
import inf300.service.impl.BookServiceImpl;
import inf300.service.impl.CartServiceImpl;
import inf300.service.impl.CountryServiceImpl;
import inf300.service.impl.CustomerServiceImpl;
import inf300.service.impl.ItemStockServiceImpl;
import inf300.service.impl.OrderServiceImpl;
import inf300.service.impl.StockServiceImpl;
import inf300.service.impl.StoreServiceImpl;
import inf300.service.spec.AddressService;
import inf300.service.spec.AuthorService;
import inf300.service.spec.BookService;
import inf300.service.spec.CartService;
import inf300.service.spec.CountryService;
import inf300.service.spec.CustomerService;
import inf300.service.spec.ItemStockService;
import inf300.service.spec.OrderService;
import inf300.service.spec.StockService;
import inf300.service.spec.StoreService;
import inf300.util.Populator;
import inf300.util.PopulatorImpl;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.BeforeClass;


public class BookMarketFacadeImplTest {

    private static BookMarketFacade facade;

    @BeforeClass
    public static void setUp() {
        CountryService countryService = CountryServiceImpl.getInstance();
        AddressService addressService = AddressServiceImpl.getInstance(countryService);
        ItemStockService itemStockService = ItemStockServiceImpl.getInstance();
        CartService cartService = CartServiceImpl.getInstance(itemStockService);
        BookService bookService = BookServiceImpl.getInstance();
        CustomerService customerService = CustomerServiceImpl.getInstance(addressService);
        AuthorService authorService = AuthorServiceImpl.getInstance();
        StockService stockService = StockServiceImpl.getInstance();
        OrderService orderService = OrderServiceImpl.getInstance(bookService);
        StoreService storeService = StoreServiceImpl.getInstance();
        facade = BookMarketFacadeImpl.getInstance(addressService,
                cartService, countryService,
                bookService, customerService,
                authorService, stockService,
                itemStockService, orderService,
                storeService);
        Populator populator = new PopulatorImpl(addressService,
                cartService, countryService,
                bookService, customerService,
                authorService, stockService,
                itemStockService, orderService,
                storeService);
        long seed = 0;
        long now = System.currentTimeMillis();
        int items = 10;
        int customers = 10;
        int addresses = 10;
        int authors = 10;
        int orders = 10;
        int stores = 3;
        int stocks = 10;
        populator.populate(seed, now, items, customers, addresses, authors, orders, stores, stocks);
    }

    @AfterClass
    public static void tearDown() {
        BookMarketFacadeImpl.clear();
    }


    @Test
    public void testGetBooksByTitle() {

        Random random = new Random(0);
        // Create some test books
        Book book1 = facade.createBook("Book 1", new Date(), "Publisher", Subject.MYSTERY, "Description", "Thumbnail", "Image", 10.00, new Date(), "1234567890", 100, Backing.HARDBACK, "Dimensions", AuthorServiceImpl.getInstance().getAnAuthorAnyAuthor(random));
        Book book2 = facade.createBook("Book 2", new Date(), "Publisher", Subject.MYSTERY, "Description", "Thumbnail", "Image", 15.00, new Date(), "0987654321", 200, Backing.PAPERBACK, "Dimensions", AuthorServiceImpl.getInstance().getAnAuthorAnyAuthor(random));
        Book book3 = facade.createBook("Book 3", new Date(), "Publisher", Subject.ROMANCE, "Description", "Thumbnail", "Image", 20.00, new Date(), "111122223333", 300, Backing.AUDIO, "Dimensions", AuthorServiceImpl.getInstance().getAnAuthorAnyAuthor(random));

        // Test getBooksByTitle method
        List<Book> books = facade.getBooksByTitle("Book 1");
        assertEquals(1, books.size());
        assertEquals(book1, books.get(0));

        books = facade.getBooksByTitle("Book 2");
        assertEquals(1, books.size());
        assertEquals(book2, books.get(0));

        books = facade.getBooksByTitle("Book");
        assertEquals(3, books.size());
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book2));
        assertTrue(books.contains(book3));

        books = facade.getBooksByTitle("Non-existent Book");
        assertEquals(0, books.size());
    }

    @Test
    public void testCreateCountry() {
        Country country = facade.createCountry("United States", "USD", 1.0);
        assertNotNull(country);
        assertEquals("United States", country.getName());
        assertEquals("USD", country.getCurrency());
        assertEquals(1.0, country.getExchange(), 0.1);
    }

    @Test
    public void testCreateAddress() {
        Country country = facade.createCountry("United States", "USD", 1.0);
        Address address = facade.createAddress("123 Main St", "123 Main St", "Anytown", "CA", "12345", country, "37.7749° N", "122.4194° W", "123");
        assertNotNull(address);
        assertEquals("123 Main St", address.getStreet1());
        assertEquals("123 Main St", address.getStreet2());
        assertEquals("Anytown", address.getCity());
        assertEquals("CA", address.getState());
        assertEquals("12345", address.getZip());
        assertEquals(country, address.getCountry());
        assertEquals("37.7749° N", address.getLatitude());
        assertEquals("122.4194° W", address.getLongitude());
        assertEquals("123", address.getBuildingNumber());
    }

    @Test
    public void testCreateCustomer() {
        Country country = facade.createCountry("United States", "USD", 1.0);
        Address address = facade.createAddress("123 Main St", "123 Main St", "Anytown", "CA", "12345", country, "37.7749° N", "122.4194° W", "123");
        Customer customer = facade.createCustomer("John", "Doe", address, "555-1234", "johndoe@example.com", new Date(), new Date(), new Date(), new Date(), 0.1, new Date(), null);
        assertNotNull(customer);
        assertEquals("John", customer.getFname());
        assertEquals("Doe", customer.getLname());
        assertEquals(address, customer.getAddress());
        assertEquals("555-1234", customer.getPhone());
        assertEquals("johndoe@example.com", customer.getEmail());
        assertEquals(0.1, customer.getDiscount(), 0.1);
    }

    @Test
    public void testCreateAuthor() {
        Author author = facade.createAuthor("J.K.", null, "Rowling", new Date(), "Author of the Harry Potter series");
        assertNotNull(author);
        assertEquals("J.K.", author.getFname());
        assertNull(author.getMname());
        assertEquals("Rowling", author.getLname());
        assertEquals("Author of the Harry Potter series", author.getBio());
    }

    @Test
    public void testCreateBook() {
        // create a new author
        Author author = facade.createAuthor("Jane", null, "Doe", null, null);

        // create a new book
        Book book = facade.createBook("The Book", null, null, null, null,
                null, null, 9.99, null, "1234567890", 200, Backing.PAPERBACK,
                "5.5x8.5", author);

        // check that the book was created and has the expected attributes
        assertNotNull(book);
        assertEquals("The Book", book.getTitle());
        assertEquals(9.99, book.getSrp(), 0.001);
//        assertEquals(4.99, book.getCost(), 0.001);
        assertEquals("1234567890", book.getIsbn());
        assertEquals(200, book.getPage());
        assertEquals(Backing.PAPERBACK, book.getBacking());
        assertEquals("5.5x8.5", book.getDimensions());
        assertEquals(author, book.getAuthor());
        //assertEquals(100, book.getStock());
    }

    @Test
    public void testCreateCart() {
        // create a new cart
        Cart cart = facade.createCart(System.currentTimeMillis());

        // check that the cart was created and is initially empty
        assertNotNull(cart);
        assertTrue(cart.getLines().isEmpty());
    }

    @Test
    public void testUpdateCart1() {
        // create a new cart
        Cart cart = facade.createCart(System.currentTimeMillis());
        int cId = cart.getId(); // sample cart ID
        List<Integer> sIds = Arrays.asList(2, 3); // sample stock IDs to remove from the cart
        List<Integer> quantities = Arrays.asList(1, 2); // sample quantities for the books to add
        long now = System.currentTimeMillis(); // current timestamp

        Cart updatedCart = facade.updateCart(cId, sIds, quantities, now);
        assertNotNull(updatedCart);
        assertEquals(cId, updatedCart.getId());
        Collection<CartLine> lines = updatedCart.getLines();
        assertNotNull(lines);
        

        assertEquals(2, lines.size());
    }
    
    
    @Test
    public void testUpdateCart2() {
        // create a new cart
        Cart cart = facade.createCart(System.currentTimeMillis());
        int cId = cart.getId(); // sample cart ID
        Integer sId = 1; // sample stock ID to add to the cart
        long now = System.currentTimeMillis(); // current timestamp

        Cart updatedCart = facade.updateCart(cId, sId, now);
        assertNotNull(updatedCart);
        assertEquals(cId, updatedCart.getId());
        Collection<CartLine> lines = updatedCart.getLines();
        assertNotNull(lines);
        assertEquals(1, lines.size());

    }

    @Test
    public void testCreateOrder() {
        // create a new country
        Country country = facade.createCountry("United States", "USD", 1.0);

        // create a new address
        Address address = facade.createAddress("123 Main St.", "123 Main St", "Anytown",
                "CA", "12345", country, null, null, null);

        // create a new customer
        Customer customer = facade.createCustomer("John", "Doe", address,
                "555-1234", "john.doe@example.com", new Date(), new Date(),
                new Date(), new Date(), 0.0, null, null);

        // create a new cart
        Cart cart = facade.createCart(System.currentTimeMillis());

        // create a new order
        Order order = facade.createOrder(customer, new Date(), cart, null,
                null, null, OrderState.DELIVERED, address, address, null);

        // check that the order was created and has the expected attributes
        assertNotNull(order);
        assertEquals(customer, order.getCustomer());

        assertEquals(address, order.getBillingAddress());
        assertEquals(address, order.getShippingAddress());
    }

}
