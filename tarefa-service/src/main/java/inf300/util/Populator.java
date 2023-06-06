package inf300.util;

/**
 *
 * @author esoft
 */
public interface Populator {

    
    void populate();
    /**
     *
     * @param seed
     * @param now
     * @param items
     * @param customers
     * @param addresses
     * @param authors
     * @param orders
     * @param stores
     * @param stocks
     * @return
     */
    boolean populate(long seed, long now, int items, int customers, int addresses, int authors, int orders, int stores, int stocks);
    
}
