package inf300.service.spec;

import inf300.domain.Address;
import inf300.domain.Stock;
import inf300.domain.Store;
import java.util.Optional;
import java.util.Random;

/**
 *
 * @author esoft
 */
public interface StockService {

    Stock getAStockAnyStock(Random random);

    Stock createStock(Address address, Store store);

    Optional<Stock> getStockById(int sId);

}
