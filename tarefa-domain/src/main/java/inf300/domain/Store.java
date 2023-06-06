package inf300.domain;

/**
 * 
 * <img src="./doc-files/Store.png" alt="Store">
 * @author esoft
 */
public class Store {
    
    private final int id;
    private final String name;
    private final String phone;

    public Store(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
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
        final Store other = (Store) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Store{" + "id=" + id + ", name=" + name + ", phone=" + phone + '}';
    }
    
    
    
}
