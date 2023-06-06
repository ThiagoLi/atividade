package inf300.service.impl;


import inf300.domain.Address;
import inf300.domain.Stock;
import inf300.domain.Store;
import inf300.service.spec.StockService;
import inf300.util.RandomUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 *
 * @author esoft
 */
public final class StockServiceImpl implements StockService {

    private static StockService instance;

    private final List<Stock> stocksById;

    public synchronized static StockService getInstance() {
        if (instance == null) {
            instance = new StockServiceImpl();
        }
        return instance;
    }

    private StockServiceImpl() {
        this(new ArrayList<>());
    }

    private StockServiceImpl(List<Stock> stocksById) {
        this.stocksById = stocksById;
    }

    @Override
    public Stock getAStockAnyStock(Random random) {
        return stocksById.get(RandomUtil.getRandomInt(random, 0, stocksById.size() - 1));
    }

    @Override
    public Optional<Stock> getStockById(int sId) {
        return Optional.ofNullable(stocksById.get(sId));
    }

    @Override
    public synchronized Stock createStock(Address address, Store store) {
        final Stock stock = new Stock(stocksById.size(), address, store);
        stocksById.add(stock);
        return stock;
    }

}
