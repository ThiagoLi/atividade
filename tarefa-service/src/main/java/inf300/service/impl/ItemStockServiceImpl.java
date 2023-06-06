package inf300.service.impl;

import inf300.service.spec.ItemStockService;
import inf300.domain.Item;
import inf300.domain.ItemStock;
import inf300.domain.Stock;
import inf300.util.RandomUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * ItemStockServiceImpl provides a singleton implementation of the
 * ItemStockService interface and manages a collection of ItemStock objects.
 *
 * @author esoft
 */
public final class ItemStockServiceImpl implements ItemStockService {

    private static ItemStockService instance;

    private final List<ItemStock> itemStockById;

    /**
     * It is a synchronized static method that returns the singleton instance of
     * the ItemStockService. It follows the lazy initialization pattern, where
     * the instance is created only if it's null.
     *
     * The singleton pattern here guarantees the consistency of the
     * itemStockById list by providing a single, shared instance of the
     * ItemStockServiceImpl class that manages the list operations.
     *
     * @return
     */
    public synchronized static ItemStockService getInstance() {
        if (instance == null) {
            instance = new ItemStockServiceImpl();
        }
        return instance;
    }

    private ItemStockServiceImpl() {
        this(new ArrayList());
    }

    private ItemStockServiceImpl(List<ItemStock> itemStockById) {
        this.itemStockById = itemStockById;
    }

    @Override
    public ItemStock getAItemStockAnyItemStock(Random random) {
        return itemStockById.get(RandomUtil.getRandomInt(random, 0, itemStockById.size() - 1));
    }

    /**
     * It returns an Optional object containing the ItemStock with the given sId
     * if it exists in the itemStockById list. It uses the Optional.ofNullable()
     * method to handle the case where the item stock is not found.
     *
     * @param sId
     * @return
     */
    @Override
    public Optional<ItemStock> getItemStock(int sId) {
        return Optional.ofNullable(itemStockById.get(sId));
    }

    @Override
    public synchronized ItemStock createItemStock(Item item, Stock stock, double cost, int qty) {
        ItemStock itemStock = new ItemStock(itemStockById.size(), item, stock, cost, qty);
        itemStockById.add(itemStock);
        return itemStock;
    }

}
