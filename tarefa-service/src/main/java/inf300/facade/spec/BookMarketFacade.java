package inf300.facade.spec;

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
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * Interface BookMarket defines a set of methods that have to be implemented by
 * a class representing a BookMarket. The methods are used to perform various
 * operations related to the BookMarket, such as creating customers, creating
 * orders, getting books, updating carts, and so on.
 *
 * The interface defines methods such as createAddress, createAuthor,
 * createBook, createCart, createCountry, createCustomer, and createItemStock
 * that are used to create new entities in the system.
 *
 *
 * Other methods in the interface include getBook, getBooksByAuthor,
 * getBooksById, getBooksBySubject, and getBooksByTitle which are used to
 * retrieve books from the system based on different criteria.
 *
 * The interface also defines methods such as getCountryById and
 * getCountryByName to retrieve country information, and getCustomer and
 * getCustomerByUsername to retrieve customer information based on the customer
 * ID or username.
 *
 * Other methods include updateBook to update the details of a book, updateCart
 * to update the items in a cart, and confirmBuy to confirm a purchase order.
 *
 *
 * @author
 */
public interface BookMarketFacade extends Serializable {

    Address createAddress(String street1, String street2, String city, String state, String zip, Country country, String latitude, String longitude, String buildingNumber);

    Author createAuthor(String fname, String mname, String lname, Date birthdate, String bio);

    Book createBook(String title, Date pubDate, String publisher, Subject subject, String desc, String thumbnail, String image, double srp, Date avail, String isbn, int page, Backing backing, String dimensions, Author author);

    Cart createCart(long now);

    Country createCountry(String name, String currency, double exchange);

    Customer createCustomer(String fname, String lname, String street1, String street2, String city, String state, String zip, String countryName, String latitude, String longitude, String buildingNumber, String phone, String email, double discount, Date birthdate, String data, long now);

    Customer createCustomer(String fname, String lname, Address address, String phone, String email, Date since, Date lastVisit, Date login, Date expiration, double discount, Date birthdate, String data);

    ItemStock createItemStock(Item item, Stock stock, double cost, int qty);

    Order createOrder(Customer customer, Date date, Cart cart, String comment, ShipType shipType, Date shipDate, OrderState status, Address billingAddress, Address shippingAddress, CCTransaction cc);

    Stock createStock(Address address, Store store);

    Optional<Stock> getStockById(int sId);

    Store createStore(String name, String phone);

    Optional<Book> getBook(int bId);

    List<Book> getBooksByAuthor(String author);

    List<Book> getBooksBySubject(Subject subject);

    List<Book> getBooksByTitle(String title);

    Cart getCart(int id);

    Country getCountryById(int cId);

    Country getCountryByName(String countryName);

    int getCountCountries();

    Customer getCustomer(int cId);

    Optional<Customer> getCustomer(String username);

    List<Book> getNewBooks(Subject subject);

    Order getOrderById(int id);

    Book updateBook(Book book);

    Cart updateCart(int cId, Integer bId, long now);
    
    Cart updateCart(int cId, List<Integer> bIds, List<Integer> quantities, long now);
    
    List<Author> getTopAuthorBySubject(Subject subject, int top);

}
