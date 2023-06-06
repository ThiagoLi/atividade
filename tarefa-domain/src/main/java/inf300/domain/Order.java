package inf300.domain;

/*
 * Order.java - Order class stores data pertinent to a single order.
 * 
 ************************************************************************
 *
 ************************************************************************/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * This code defines the Order class, which represents a single order in a
 * bookstore. An Order object contains various data about the order, such as the
 * customer who placed it, the date it was placed, the items that were ordered,
 * and the billing and shipping addresses.
 * 
 * The class has several instance variables, including an id, a customer object,
 * a date object, subTotal, tax, total, shipType, shipDate, status,
 * billingAddress, shippingAddress, cc, and an ArrayList of OrderLine objects
 * representing the individual items in the order.
 * 
 * The class has a constructor that takes several parameters to initialize its
 * instance variables, including an id, a customer object, a date object, a cart
 * object representing the items in the order, and various other pieces of
 * information such as the shipping type, ship date, status, and billing and
 * shipping addresses. The constructor calculates the subTotal, tax, and total
 * values based on the items in the cart and the customer's discount, and
 * creates an OrderLine object for each item in the cart.
 * 
 * The class also has several getter methods to retrieve the various pieces of
 * data stored in an Order object.
 * 
 *
 * <img src="./doc-files/Order.png" alt="Order">
 */
public class Order implements Serializable, Context<OrderState> {

    private static final long serialVersionUID = -1106285234830970111L;

    private final int id;
    private final Customer customer;
    private final Date date;
    private final double subtotal;
    private final double tax;
    private final double total;
    private final ShipType shipType;
    private final Date shipDate;
    private OrderState status;
    private final Address billingAddress;
    private final Address shippingAddress;
    private final CCTransaction cc;
    private final ArrayList<OrderLine> lines;

    /**
     *
     * This is the constructor for the Order class. It creates an Order object
     * with the specified properties, including an id, customer, date, subtotal,
     * tax, total, shipType, shipDate, status, billingAddress, shippingAddress,
     * cc, and lines.
     * 
     * The subtotal is calculated by calling the subTotal method of the cart
     * object with the customer's discount as a parameter, while tax is set to a
     * fixed value of 8.25. The total is calculated by calling the total method
     * of the cart object with the customer's discount as a parameter.
     * 
     * The lines property is initialized as a new ArrayList of OrderLine objects
     * with the same size as the number of lines in the cart. For each CartLine
     * object in the cart, an OrderLine object is created using the Book,
     * quantity, customer's discount, and comment properties, and added to the
     * lines ArrayList.
     * 
     * <pre>
     * this.id = id;
     * this.customer = customer;
     * this.date = date;
     * subtotal = cart.subTotal(customer.getDiscount());
     * tax = 8.25;
     * total = cart.total(customer.getDiscount());
     * this.shipType = shipType;
     * this.shipDate = shipDate;
     * this.status = status;
     * this.billingAddress = billingAddress;
     * this.shippingAddress = shippingAddress;
     * this.cc = cc;
     * lines = new ArrayList&lt;OrderLine&gt;(cart.getLines().size());
     * for (CartLine cartLine : cart.getLines()) {
     * OrderLine line = new OrderLine(cartLine.getBook(),
     * cartLine.getQty(), customer.getDiscount(),
     * comment);
     * lines.add(line);
     * }
     * </pre>    // Test the getters and setters
     *
     * @param id
     * @param customer
     * @param date
     * @param cart
     * @param comment
     * @param shipType
     * @param shipDate
     * @param status
     * @param billingAddress
     * @param shippingAddress
     * @param cc
     */
    public Order(int id, Customer customer, Date date, Cart cart,
            String comment, ShipType shipType, Date shipDate, OrderState status,
            Address billingAddress, Address shippingAddress, CCTransaction cc) {
        this.id = id;
        this.customer = customer;
        this.date = date;
        subtotal = cart.subTotal(customer.getDiscount());
        tax = 8.25;
        total = cart.total(customer.getDiscount());
        this.shipType = shipType;
        this.shipDate = shipDate;
        this.status = status;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.cc = cc;
        lines = new ArrayList<OrderLine>(cart.getLines().size());
        for (CartLine cartLine : cart.getLines()) {
            OrderLine line = new OrderLine(cartLine.getItemStock(),
                    cartLine.getQty(), customer.getDiscount(),
                    comment);
            lines.add(line);
        }
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @return
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     *
     * @return
     */
    public double getTax() {
        return tax;
    }

    /**
     *
     * @return
     */
    public double getTotal() {
        return total;
    }

    /**
     *
     * @return
     */
    public ShipType getShipType() {
        return shipType;
    }

    /**
     *
     * @return
     */
    public Date getShipDate() {
        return shipDate;
    }

    /**
     *
     * @return
     */
    public OrderState getStatus() {
        return status;
    }

    public void setStatus(OrderState status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     *
     * @return
     */
    public Address getShippingAddress() {
        return shippingAddress;
    }

    /**
     *
     * @return
     */
    public CCTransaction getCC() {
        return cc;
    }    /**
     *
     * This is the constructor for the Order class. It creates an Order object
     * with the specified properties, including an id, customer, date, subtotal,
     * tax, total, shipType, shipDate, status, billingAddress, shippingAddress,
     * cc, and lines.
     * 
     * The subtotal is calculated by calling the subTotal method of the cart
     * object with the customer's discount as a parameter, while tax is set to a
     * fixed value of 8.25. The total is calculated by calling the total method
     * of the cart object with the customer's discount as a parameter.
     * 
     * The lines property is initialized as a new ArrayList of OrderLine objects
     * with the same size as the number of lines in the cart. For each CartLine
     * object in the cart, an OrderLine object is created using the Book,
     * quantity, customer's discount, and comment properties, and added to the
     * lines ArrayList.
     * 
     * <pre>
     * this.id = id;
     * this.customer = customer;
     * this.date = date;
     * subtotal = cart.subTotal(customer.getDiscount());
     * tax = 8.25;
     * total = cart.total(customer.getDiscount());
     * this.shipType = shipType;
     * this.shipDate = shipDate;
     * this.status = status;
     * this.billingAddress = billingAddress;
     * this.shippingAddress = shippingAddress;
     * this.cc = cc;
     * lines = new ArrayList&lt;OrderLine&gt;(cart.getLines().size());
     * for (CartLine cartLine : cart.getLines()) {
     * OrderLine line = new OrderLine(cartLine.getBook(),
     * cartLine.getQty(), customer.getDiscount(),
     * comment);
     * lines.add(line);
     * }
     * </pre>  
     *
     * @return
     */
    public ArrayList<OrderLine> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", customer=" + customer + ", date=" + date + ", subtotal=" + subtotal + ", tax=" + tax + ", total=" + total + ", shipType=" + shipType + ", shipDate=" + shipDate + ", status=" + status + ", billingAddress=" + billingAddress + ", shippingAddress=" + shippingAddress + ", cc=" + cc + ", lines=" + lines + '}';
    }
    
    

}
