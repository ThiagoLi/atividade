package inf300.domain;

/* 
 * Address.java - Stores a country.
 *
 ************************************************************************
 *
 *
 ************************************************************************/
import java.io.Serializable;

/**
 * </pre>
 * The Country class represents a country and contains its id, name, currency,
 * and exchange rate. It has a constructor that takes these attributes as
 * arguments and initializes them. It also has getters for each attribute to
 * allow access to them.
 * 
 * The equals() method checks if the given object is an instance of Country and
 * has the same name as the current instance of the class. If this is the case,
 * then the two objects are considered equal and the method returns true.
 * Otherwise, it returns false.
 * 
 * The hashCode() method returns the hash code of the name attribute of the
 * country. This is because the equals() method checks only the name attribute
 * to determine if two countries are equal.
 *  </pre>
 * <img src="./doc-files/Country.png" alt="Country">
 */
public class Country implements Serializable {

    private static final long serialVersionUID = 5171617014956861344L;

    private final int id;
    private final String name;
    private final String currency;
    private final double exchange;

    /**
     *
     * @param id
     * @param name
     * @param currency
     * @param exchange
     */
    public Country(int id, String name, String currency, double exchange) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.exchange = exchange;
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
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @return
     */
    public double getExchange() {
        return exchange;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Country) {
            Country country = (Country) o;
            return name.equals(country.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Country{" + "id=" + id + ", name=" + name + ", currency=" + currency + ", exchange=" + exchange + '}';
    }

    
    
}
