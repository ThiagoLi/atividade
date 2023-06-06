package inf300.domain;

/*
 * Cart.java - Class stores the necessary components of a shopping cart.
 * 
 ************************************************************************
 *
 *
 ************************************************************************/
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 * <pre>
 * This code defines the Cart class, which represents a shopping cart in an
 * online bookstore. The Cart class has the following attributes:
 *
 * id: an integer that represents the unique identifier of the cart.
 *
 * time: a Date object that represents the time when the cart was created.
 *
 * linesByBookId: a HashMap that maps a book's ID to a CartLine object, which
 * represents a line in the cart containing a book and the quantity of that
 * book.
 *
 * aggregateCost: a double that represents the total cost of all the books in
 * the cart.
 *
 * aggregateQuantity: an integer that represents the total number of books in
 * the cart.
 *
 * The Cart class has the following methods:
 *
 * Cart(int id, Date time): a constructor that initializes the id, time, and
 * linesByBookId attributes and calls the clear() method to initialize the
 * aggregateCost and aggregateQuantity attributes.
 *
 * getId(): a method that returns the id attribute.
 *
 * getTime(): a method that returns the time attribute.
 *
 * setTime(Date time): a method that sets the time attribute to the specified
 * Date.
 *
 * clear(): a method that clears the linesByBookId attribute and sets the
 * aggregateCost and aggregateQuantity attributes to 0.
 *
 * getLines(): a method that returns a Collection of all the CartLine objects in
 * the linesByBookId attribute.
 *
 * increaseLine(Book book, int quantity): a method that increases the quantity
 * of a book in the cart by the specified quantity or adds the book to the cart
 * if it is not already in it.
 *
 * changeLine(Book book, int quantity): a method that changes the quantity of a
 * book in the cart to the specified quantity.
 *
 * subTotal(double discount): a method that calculates the subtotal cost of all
 * the books in the cart after applying the specified discount.
 *
 * tax(double discount): a method that calculates the tax amount on the subtotal
 * cost of all the books in the cart after applying the specified discount.
 *
 * shipCost(): a method that calculates the shipping cost of the cart based on
 * the total number of books in it.
 *
 * total(double discount): a method that calculates the total cost of the cart
 * after applying the specified discount, tax, and shipping cost.
 *
 * </pre>
 *
 * <img src="./doc-files/Cart.png" alt="Cart">
 */
public class Cart implements Serializable {

    private static final long serialVersionUID = -4194553499937996531L;

    private final int id;
    private Date time;
    private HashMap<Integer, CartLine> linesByItemStockId;
    private double aggregateCost;
    private int aggregateQuantity;

    public Cart(int id, Date time) {
        this.id = id;
        this.time = time;
        clear();
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
    public Date getTime() {
        return time;
    }

    /**
     *import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     *
     *
     */
    public void clear() {
        linesByItemStockId = new HashMap<Integer, CartLine>();
        aggregateCost = 0;
        aggregateQuantity = 0;
    }

    /**
     *
     * @return
     */
    public Collection<CartLine> getLines() {
        return linesByItemStockId.values();
    }

    /**
     *
     * @param itemStock
     * @param quantity
     */
    public void increaseLine(ItemStock itemStock, int quantity) {
        CartLine line = linesByItemStockId.get(itemStock.getId());
        if (line == null) {
            line = new CartLine(0, itemStock);
            linesByItemStockId.put(itemStock.getId(), line);
        }
        aggregateCost += itemStock.getCost() * quantity;
        aggregateQuantity += quantity;
        line.setQty(line.getQty() + quantity);
        if (quantity == 0) {
            linesByItemStockId.remove(itemStock.getId());
        }
    }

    /**
     *
     * @param itemStock
     * @param quantity
     */
    public void changeLine(ItemStock itemStock, int quantity) {
        CartLine line = linesByItemStockId.get(itemStock.getId());
        if (line == null) {
            line = new CartLine(0, itemStock);
            linesByItemStockId.put(itemStock.getId(), line);
        }
        aggregateCost += itemStock.getCost() * (quantity - line.getQty());
        aggregateQuantity += (quantity - line.getQty());
        line.setQty(quantity);
        if (quantity == 0) {
            linesByItemStockId.remove(itemStock.getId());
        }
    }

    /**
     *
     * The subTotal method calculates the subtotal cost of all the items in the
     * shopping cart, taking into account the given discount percentage.
     * <pre>
     * return aggregateCost * ((100 - discount) * 0.01);
     * </pre>
     *
     * @param discount
     * @return
     */
    public double subTotal(double discount) {
        return aggregateCost * ((100 - discount) * 0.01);
    }

    /**
     *
     * The tax method calculates the sales tax amount based on the subtotal cost
     * and the given discount percentage. The tax rate is fixed at 8.25%.
     * <pre>
     * return subTotal(discount) * 0.0825;
     * </pre>
     *
     * @param discount
     * @return
     */
    public double tax(double discount) {
        return subTotal(discount) * 0.0825;
    }

    /**
     *
     * The shipCost method calculates the shipping cost based on the total
     * number of items in the shopping cart. The shipping cost is fixed at $3.00
     * plus $1.00 per item.
     * <pre>
     * return 3.00 + (1.00 * aggregateQuantity);
     * </pre>
     *
     * @return
     */
    public double shipCost() {
        return 3.00 + (1.00 * aggregateQuantity);
    }

    /**
     *
     * The total method calculates the total cost of the shopping cart,
     * including the subtotal, shipping cost, and sales tax, taking into account
     * the given discount percentage.
     * <pre>
     * return subTotal(discount) + shipCost() + tax(discount);
     * </pre>
     *
     * @param discount
     * @return
     */
    public double total(double discount) {
        return subTotal(discount) + shipCost() + tax(discount);
    }

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", time=" + time + ", linesByItemStockId=" + linesByItemStockId + ", aggregateCost=" + aggregateCost + ", aggregateQuantity=" + aggregateQuantity + '}';
    }
    
    

}
