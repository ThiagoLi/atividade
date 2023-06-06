package inf300.domain;

/* 
 * CartLine.java - Class stores the necessary data for a single item in
 *                 a single shopping cart.
 *
 ************************************************************************
 *
 *
 ************************************************************************/
import java.io.Serializable;
import java.util.Objects;

/**
 * <pre>
 * The CartLine class represents a line item in a shopping cart. 
 * It has two properties: qty and stock. qty is an integer representing the 
 * quantity of the item, while stock is an instance of the ItemStock class 
 * representing the item being added to the cart.
 *
 * The constructor of CartLine takes in two parameters: qty and stock. 
 * qty represents the quantity of the item being added to the cart, while stock 
 * represents the stock of the item being added to the cart.
 *
 * The setQty method allows the quantity of the item in the cart to be updated. 
 * The getQty method returns the quantity of the item in the cart. 
 * The getStock method returns the ItemStock object representing the item being 
 * added to the cart.
 *
 * The class implements the Serializable interface, indicating that it can be 
 * serialized (converted into a byte stream) and deserialized (converted back 
 * from a byte stream). The serialVersionUID field is used to provide version 
 * control for serialized instances of the class.
 * </pre>
 * <img src="./doc-files/CartLine.png" alt="CartLine">
 */
public class CartLine implements Serializable {

    private static final long serialVersionUID = 7390646727961714957L;

    private int qty;
    private final ItemStock itemStock;

    /**
     *
     * @param qty
     * @param itemStock
     */
    public CartLine(int qty, ItemStock itemStock) {
        this.qty = qty;
        this.itemStock = itemStock;
    }

    /**
     *
     * @param qty
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     *
     * @return
     */
    public int getQty() {
        return qty;
    }

    public ItemStock getItemStock() {
        return itemStock;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CartLine other = (CartLine) obj;
        if (this.qty != other.qty) {
            return false;
        }
        return Objects.equals(this.itemStock, other.itemStock);
    }

    @Override
    public String toString() {
        return "CartLine{" + "qty=" + qty + ", itemStock=" + itemStock + '}';
    }
    
    

}
