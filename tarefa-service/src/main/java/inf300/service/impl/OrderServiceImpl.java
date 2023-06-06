package inf300.service.impl;

import inf300.domain.Address;
import inf300.domain.CCTransaction;
import inf300.domain.Cart;
import inf300.domain.Country;
import inf300.domain.CreditCard;
import inf300.domain.Customer;
import inf300.domain.Order;
import inf300.domain.Book;
import inf300.domain.OrderLine;
import inf300.domain.OrderState;
import inf300.domain.ShipType;
import inf300.domain.Subject;
import inf300.service.spec.BookService;
import inf300.service.spec.OrderService;
import inf300.service.spec.OrderService.CounterBook;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


import java.util.HashMap;
import java.util.Map;


/**
 * OrderServiceImpl class implements the OrderService interface and provides
 * methods for managing orders. It utilizes data structures to store and
 * retrieve order information and relies on the BookService interface for
 * book-related functionality.
 *
 * @author esoft
 */
public final class OrderServiceImpl implements OrderService {

    private static OrderService instance;

    private final List<Order> ordersById;
    private final LinkedList<Order> ordersByCreation;
    private final BookService bookService;

    /**
     * Singleton Pattern: The class implements a singleton pattern to ensure
     * that only one instance of OrderServiceImpl is created. The instance
     * variable holds the singleton instance, and the getInstance() method
     * returns that instance.
     *
     * Obs: BookService Dependency: The class has a dependency on the
     * BookService interface, which is passed as a parameter to the
     * getInstance() method or the constructor. This dependency is stored in the
     * bookService variable.
     *
     * @param bookService
     * @return
     */
    public synchronized static OrderService getInstance(
            BookService bookService) {
        if (instance == null) {
            instance = new OrderServiceImpl(bookService);
        }
        return instance;
    }

    private OrderServiceImpl(BookService bookService) {
        this(new ArrayList<>(), new LinkedList<>(), bookService);
    }

    OrderServiceImpl(List<Order> ordersById, LinkedList<Order> ordersByCreation, BookService bookService) {
        this.ordersById = ordersById;
        this.ordersByCreation = ordersByCreation;
        this.bookService = bookService;
    }

    /**
     * Order Confirmation: The confirmBuy(...) method confirms a buy order for a
     * customer. It performs various operations, such as updating the stock
     * quantities for the items in the cart, and creates a new order with the
     * provided details. The created order is then returned.
     *
     * @param customer
     * @param discount
     * @param cart
     * @param comment
     * @param ccType
     * @param ccNumber
     * @param ccName
     * @param ccExpiry
     * @param ccCountry
     * @param shipping
     * @param shippingDate
     * @param billingAddress
     * @param shippingAddress
     * @param now
     * @return
     */
    @Override
    public synchronized Order confirmBuy(Customer customer, double discount, Cart cart,
            String comment, CreditCard ccType, long ccNumber, String ccName,
            Date ccExpiry, Country ccCountry, ShipType shipping, Date shippingDate,
            Address billingAddress, Address shippingAddress, long now) {

        CCTransaction ccTransact = new CCTransaction(ccType, ccNumber, ccName,
                ccExpiry, "", cart.total(customer.getDiscount()),
                new Date(now), shippingAddress.getCountry());
        return createOrder(customer, new Date(now), cart, comment, shipping,
                shippingDate, OrderState.NEW, customer.getAddress(),
                shippingAddress, ccTransact);
    }

    @Override
    public List<OrderLine> getOrderLineByIdOrder(int idOrder) {
        return this.ordersById.stream().filter(o -> o.getId() == idOrder).flatMap(o -> o.getLines().stream()).collect(Collectors.toList());
    }

    @Override
    public int getCountOrders() {
        return this.ordersById.size();
    }
    
    
     @Override
    public int getCountOrderLines() {
        return this.ordersById.stream().filter(o -> o.getLines() != null).mapToInt(o -> o.getLines().size()).sum();
    }

    @Override
    public List<Order> getOrdersByStatus(OrderState state) {
        return this.ordersById.stream().filter(o -> o.getStatus() == state).collect(Collectors.toList());
    }

    @Override
    public Order updateOrder(Order order) {
        return order;
    }

    @Override
    public synchronized Order createOrder(Customer customer, Date date, Cart cart,
            String comment, ShipType shipType, Date shipDate,
            OrderState status, Address billingAddress, Address shippingAddress,
            CCTransaction cc) {
        int id = ordersById.size();
        Order order = new Order(
                id, customer, date, cart, comment, shipType,
                shipDate, status,
                billingAddress,
                shippingAddress, cc);
        ordersById.add(order);
        ordersByCreation.addFirst(order);
        customer.logOrder(order);
        cart.clear();
        return order;
    }

    @Override
    public Order getOrderById(int id) {
        return this.ordersById.get(id);
    }

    @Override
    public List<CounterBook> getBestSellers(Subject subject) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CounterAuthor> getTopAuthorBySubject(Subject subject, int top) {
    	BookService bookService.getInstance(null);
    	List<? extends Book> books;
    	books = bookService.getBooksBySubject(subject);
      //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    

}
