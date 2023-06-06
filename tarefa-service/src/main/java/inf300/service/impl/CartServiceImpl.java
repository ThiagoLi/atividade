package inf300.service.impl;

import inf300.domain.Cart;
import inf300.service.spec.CartService;
import inf300.service.spec.ItemStockService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CartServiceImpl provides an implementation of the CartService interface for
 * managing shopping carts. The Singleton pattern is used to ensure that only
 * one instance of CartService exists, and the class provides methods for
 * retrieving carts by their IDs, creating new carts, and updating cart
 * contents.
 *
 * @author esoft
 */
public final class CartServiceImpl implements CartService {

    private static CartService instance;

    private final List<Cart> cartsById;
    private final ItemStockService itemStockService;

    public synchronized static CartService getInstance(ItemStockService itemStockService) {
        if (instance == null) {
            instance = new CartServiceImpl(itemStockService);
        }
        return instance;
    }

    private CartServiceImpl(ItemStockService itemStockService) {
        this(new ArrayList<>(), itemStockService);

    }

    private CartServiceImpl(List<Cart> cartsById, ItemStockService stockService) {
        this.cartsById = cartsById;
        this.itemStockService = stockService;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Cart getCart(int id) {
        return cartsById.get(id);
    }

    /**
     *
     * @param now
     * @return
     */
    @Override
    public synchronized Cart createCart(long now) {
        int id = cartsById.size();
        Cart cart = new Cart(id, new Date(now));
        cartsById.add(cart);
        return cart;
    }

    /**
     * This method updates a cart based on the provided parameters. It retrieves
     * the cart with the given ID using the getCart() method. Increases the line
     * item quantity in the cart by 1 by calling the increaseLine() method on
     * the cart object. Finally, it sets the timestamp of the cart to the
     * provided timestamp.
     *
     * @param cId
     * @param sId
     * @param sIds
     * @param quantities
     * @param now
     * @return
     */
    @Override
    public Cart updateCart(int cId, Integer sId, long now) {
        Cart cart = getCart(cId);

        if (sId != null) {
            cart.increaseLine(itemStockService.getItemStock(sId).get(), 1);
        }
        cart.setTime(new Date(now));
        return cart;
    }

    /**
     * This method updates a cart based on the provided parameters. It retrieves
     * the cart with the given ID using the getCart() method. It then iterates
     * over the sIds and quantities lists, and for each entry, it calls the
     * changeLine() method on the cart object to update the line item quantity.
     * Finally, it sets the timestamp of the cart to the provided timestamp.
     *
     * @param cId
     * @param sIds
     * @param quantities
     * @param now
     * @return
     */
    @Override
    public Cart updateCart(int cId, List<Integer> sIds,
            List<Integer> quantities, long now) {
        Cart cart = getCart(cId);

        for (int i = 0; i < sIds.size(); i++) {
            cart.changeLine(itemStockService.getItemStock(sIds.get(i)).get(), quantities.get(i));
        }

        cart.setTime(new Date(now));

        return cart;
    }

}
