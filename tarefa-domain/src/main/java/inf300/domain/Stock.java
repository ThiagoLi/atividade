package inf300.domain;


/**
 *
 * @author
 * <img src="./doc-files/Stock.png" alt="Stock">
 */
public class Stock{
    private final int id;
    private final Address address;
    private final Store store;

    public Stock(int id, Address address, Store store) {
        this.id = id;
        this.address = address;
        this.store = store;
    }



    public int getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Stock other = (Stock) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", address=" + address + ", store=" + store + '}';
    }
    
    
    
}

