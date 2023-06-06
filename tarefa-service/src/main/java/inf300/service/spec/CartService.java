package inf300.service.spec;

import inf300.domain.Cart;
import java.util.List;

/**
 *
 * @author esoft
 */
public interface CartService {

    Cart createCart(long now);
    
    Cart getCart(int id);

    Cart updateCart(int cId, List<Integer> bIds, List<Integer> quantities, long now);
    
    Cart updateCart(int cId, Integer bId, long now);

    
}
