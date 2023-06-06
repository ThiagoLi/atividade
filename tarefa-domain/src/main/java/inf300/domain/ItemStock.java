package inf300.domain;

import inf300.domain.exception.StockException;
import java.io.Serializable;
import java.util.Objects;

/**
 * <pre>
 
 * 
 * </pre>
 * <img src="./doc-files/ItemStock.png" alt="ItemStock">
 *
 */
public class ItemStock implements Serializable {

    private final int id;

    private final Item item;
    private final Stock stock;
    private double cost;
    private int qty;

    public ItemStock(int id, Item item, Stock stock, double cost, int qty) {
        this.id = id;
        this.item = new TItem(item.getId(), item.getTitle());
        this.stock = stock;
        this.cost = cost;
        this.qty = qty;
    }
    
    class TItem extends Item{
        
        public TItem(int id, String title) {
            super(id, title);
        }
        
    }


    public int getId() {
        return id;
    }


    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantityOnHand() {
        return qty;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.qty = quantityOnHand;
    }

    public Item getItem() {
        return item;
    }

    /**
     *
     * @param amount
     */
    public void addQty(int amount) throws StockException {
        if (qty + amount < 0) {
            throw new StockException("Insufficient stock.");
        }
        qty += amount;

    }

    public Stock getStock() {
        return stock;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
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
        final ItemStock other = (ItemStock) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.cost) != Double.doubleToLongBits(other.cost)) {
            return false;
        }
        if (this.qty != other.qty) {
            return false;
        }
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        return Objects.equals(this.stock, other.stock);
    }

    @Override
    public String toString() {
        return "ItemStock{" + "id=" + id + ", item=" + item + ", stock=" + stock + ", cost=" + cost + ", qty=" + qty + '}';
    }
    
    



}
