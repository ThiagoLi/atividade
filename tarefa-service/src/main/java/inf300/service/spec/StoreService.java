package inf300.service.spec;

import inf300.domain.Store;
import java.util.Random;

/**
 *
 * @author esoft
 */
public interface StoreService {
    
    Store getAStoreAnyStore(Random random);

    Store createStore(String name, String phone);
}
