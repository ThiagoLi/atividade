package inf300.service.impl;


import inf300.domain.Store;
import inf300.service.spec.StoreService;
import inf300.util.RandomUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author esoft
 */
public final class StoreServiceImpl implements StoreService {

    private static StoreService instance;

    private final List<Store> storesById;

    public synchronized static StoreService getInstance() {
        if (instance == null) {
            instance = new StoreServiceImpl();
        }
        return instance;
    }

    private StoreServiceImpl() {
        this(new ArrayList<>());
    }

    private StoreServiceImpl(List<Store> storesById) {
        this.storesById = storesById;
    }

    @Override
    public Store getAStoreAnyStore(Random random) {
        return storesById.get(RandomUtil.getRandomInt(random, 0, storesById.size() - 1));
    }

    @Override
    public synchronized Store createStore(String name, String phone) {
        Store store = new Store(storesById.size(), name, phone);
        storesById.add(store);
        return store;
    }

}
