package inf300.service.spec;

import inf300.domain.Item;
import inf300.domain.ItemStock;
import inf300.domain.Stock;
import java.util.Optional;
import java.util.Random;

/**
 *
 * @author esoft
 */
public interface ItemStockService {

    ItemStock createItemStock(Item item, Stock stock, double cost, int qty);

    ItemStock getAItemStockAnyItemStock(Random random);

    Optional<ItemStock> getItemStock(int sId);
    
}
