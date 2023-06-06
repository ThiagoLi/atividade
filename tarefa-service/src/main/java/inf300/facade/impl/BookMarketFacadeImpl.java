package inf300.facade.impl;

import inf300.service.spec.CartService;
import inf300.facade.spec.BookMarketFacade;
import inf300.service.spec.CountryService;
import inf300.service.spec.CustomerService;
import inf300.service.spec.OrderService;
import inf300.service.spec.AuthorService;
import inf300.service.spec.AddressService;
import inf300.service.spec.StockService;
import inf300.service.spec.BookService;
import inf300.service.spec.StoreService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import inf300.domain.Address;
import inf300.domain.Author;
import inf300.domain.Backing;
import inf300.domain.Book;
import inf300.domain.CCTransaction;
import inf300.domain.Cart;
import inf300.domain.Country;
import inf300.domain.Customer;
import inf300.domain.Item;
import inf300.domain.ItemStock;
import inf300.domain.Order;
import inf300.domain.OrderState;
import inf300.domain.ShipType;
import inf300.domain.Stock;
import inf300.domain.Store;
import inf300.domain.Subject;
import inf300.service.spec.ItemStockService;
import java.util.stream.Collectors;

/**
 * <pre>
 * The BookMarketFacadeImpl class acts as a facade that hides the
 * complexity of the BookMarket's subsystems from its clients. It contains
 * references to several services such as AddressService, CartService,
 * CountryService, BookService, CustomerService, AuthorService, StockService,
 * and OrderService.
 *
 * The BookMarketFacadeImpl class provides several methods to access the
 * functionality of these services, such as getting a list of books, creating a
 * customer or an author, getting an address, and getting a country.
 *
 * </pre>
 *
 * @author esoft
 */
public class BookMarketFacadeImpl implements BookMarketFacade {

    private static final long serialVersionUID = -3099048826035606338L;
    private static BookMarketFacadeImpl instance;
    private final AddressService addressService;
    private final CartService cartService;
    private final CountryService countryService;
    private final BookService bookService;
    private final CustomerService customerService;
    private final AuthorService authorService;
    private final StockService stockService;
    private final ItemStockService itemStockService;
    private final OrderService orderService;
    private final StoreService storeService;

    public BookMarketFacadeImpl(
            AddressService addressService,
            CartService cartService,
            CountryService countryService,
            BookService bookService,
            CustomerService customerService,
            AuthorService authorService,
            StockService stockService,
            ItemStockService itemStockService,
            OrderService orderService,
            StoreService storeService) {
        this.addressService = addressService;
        this.cartService = cartService;
        this.countryService = countryService;
        this.bookService = bookService;
        this.customerService = customerService;
        this.authorService = authorService;
        this.stockService = stockService;
        this.itemStockService = itemStockService;
        this.orderService = orderService;
        this.storeService = storeService;
    }

    public synchronized static BookMarketFacadeImpl getInstance(AddressService addressService,
            CartService cartService,
            CountryService countryService,
            BookService bookService,
            CustomerService customerService,
            AuthorService authorService,
            StockService stockService,
            ItemStockService itemStockService,
            OrderService orderService,
            StoreService storeService) {
        if (instance == null) {
            instance = new BookMarketFacadeImpl(addressService, cartService, countryService, bookService, customerService, authorService, stockService, itemStockService, orderService, storeService);
        }
        return instance;
    }

    public static void clear() {
        instance = null;
    }

    @Override
    public Country getCountryByName(String countryName) {

        return countryService.getCountryByName(countryName);
    }

    @Override
    public int getCountCountries() {
        return countryService.getCountCountries();
    }

    @Override
    public Stock createStock(Address address, Store store) {
        return stockService.createStock(address, store);
    }

    @Override
    public Store createStore(String name, String phone) {
        return storeService.createStore(name, phone);
    }

    @Override
    public Country getCountryById(int cId) {
        return countryService.getCountryById(cId);
    }

    @Override
    public Address createAddress(String street1, String street2,
            String city, String state, String zip, Country country,
            String latitude, String longitude, String buildingNumber) {

        return addressService.createAddress(street1, street2, city, state, zip, country, latitude, longitude, buildingNumber);
    }

    @Override
    public Customer getCustomer(int cId) {
        return customerService.getCustomer(cId);
    }

    @Override
    public Optional<Customer> getCustomer(String username) {
        return customerService.getCustomer(username);
    }

    @Override
    public Customer createCustomer(String fname, String lname, String street1,
            String street2, String city, String state, String zip,
            String countryName, String latitude, String longitude,
            String buildingNumber, String phone, String email, double discount,
            Date birthdate, String data, long now) {
        return customerService.createCustomer(fname, lname, street1, street2, city, state, zip, countryName, latitude, longitude, buildingNumber, phone, email, discount, birthdate, data, now);
    }

    @Override
    public Customer createCustomer(String fname, String lname, Address address,
            String phone, String email, Date since, Date lastVisit,
            Date login, Date expiration, double discount, Date birthdate,
            String data) {
        return customerService.createCustomer(fname, lname, address, phone, email, since, lastVisit, login, expiration, discount, birthdate, data);
    }

    @Override
    public Author createAuthor(String fname, String mname, String lname,
            Date birthdate, String bio) {
        return authorService.createAuthor(fname, mname, lname, birthdate, bio);
    }

    @Override
    public Optional<Book> getBook(int bId) {
        return bookService.getBook(bId);
    }

    @Override
    public List<Book> getBooksBySubject(Subject subject) {
        return (List<Book>) bookService.getBooksBySubject(subject);
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        return (List<Book>) bookService.getBooksByTitle(title);
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return (List<Book>) bookService.getBooksByAuthor(author);

    }

    @Override
    public List<Book> getNewBooks(Subject subject) {
        return (List<Book>) bookService.getNewBooks(subject);
    }

    @Override
    public Book createBook(String title, Date pubDate, String publisher,
            Subject subject, String desc, String thumbnail,
            String image, double srp, Date avail, String isbn,
            int page, Backing backing, String dimensions, Author author
    ) {

        return bookService.createBook(title, pubDate, publisher, subject, desc, thumbnail, image, srp, avail, isbn, page, backing, dimensions, author);
    }

    @Override
    public ItemStock createItemStock(Item item, Stock stock, double cost, int qty) {
        return itemStockService.createItemStock(item, stock, cost, qty);
    }

    @Override
    public Book updateBook(Book book) {
        return bookService.updateBook(book);
    }

    @Override
    public Cart getCart(int id) {
        return cartService.getCart(id);
    }

    @Override
    public Cart createCart(long now) {
        return cartService.createCart(now);
    }

    @Override
    public Cart updateCart(int cId, List<Integer> bIds,
            List<Integer> quantities, long now) {
        return cartService.updateCart(cId, bIds,
                quantities, now);
    }

    @Override
    public Cart updateCart(int cId, Integer bId, long now) {
        return cartService.updateCart(cId, bId, now);
    }

    @Override
    public Order createOrder(Customer customer, Date date, Cart cart,
            String comment, ShipType shipType, Date shipDate,
            OrderState status, Address billingAddress, Address shippingAddress,
            CCTransaction cc) {
        return orderService.createOrder(customer, date, cart, comment, shipType, shipDate, status, billingAddress, shippingAddress, cc);
    }

    @Override
    public Order getOrderById(int id) {
        return orderService.getOrderById(id);
    }

    @Override
    public Optional<Stock> getStockById(int sId) {
        return stockService.getStockById(sId);
    }

    @Override
    public Country createCountry(String name, String currency, double exchange) {
        return countryService.createCountry(name, currency, exchange);
    }

    @Override
    public List<Author> getTopAuthorBySubject(Subject subject, int top) {
        return orderService.getTopAuthorBySubject(subject, top).stream().map(ca -> ca.getAuthor()).collect(Collectors.toList());
    }

}
