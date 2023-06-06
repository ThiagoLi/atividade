package inf300.service.spec;

import inf300.domain.Address;
import inf300.domain.Author;
import inf300.domain.Book;
import inf300.domain.CCTransaction;
import inf300.domain.CreditCard;
import inf300.domain.Cart;
import inf300.domain.Country;
import inf300.domain.Customer;
import inf300.domain.Order;
import inf300.domain.OrderLine;
import inf300.domain.OrderState;
import inf300.domain.ShipType;
import inf300.domain.Subject;
import java.util.Date;
import java.util.List;

/**
 *
 * @author esoft
 */
public interface OrderService {

    public static class CounterAuthor {

        /**
         *
         */
        public final Author author;
        public int count;

        public CounterAuthor(Author author, int count) {
            this.author = author;
            this.count = count;
        }

        public Author getAuthor() {
            return author;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "CounterAuthor{" + "author=" + author.getFname() + " " + author.getLname() + ", count=" + count + '}';
        }

    }

    public static class CounterBook {

        /**
         *
         */
        public final Book book;
        public int count;

        public CounterBook(Book book, int count) {
            this.book = book;
            this.count = count;
        }

        public Book getBook() {
            return this.book;
        }

        /**
         *
         * @return
         */
        public int getCounter() {
            return this.count;
        }
    }

    Order createOrder(Customer customer, Date date, Cart cart, String comment, ShipType shipType, Date shipDate, OrderState status, Address billingAddress, Address shippingAddress, CCTransaction cc);

    Order getOrderById(int id);

    Order confirmBuy(Customer customer, double discount, Cart cart,
            String comment, CreditCard ccType, long ccNumber, String ccName,
            Date ccExpiry, Country ccCountry, ShipType shipping, Date shippingDate,
            Address billingAddress, Address shippingAddress, long now);

    List<OrderLine> getOrderLineByIdOrder(int idOrder);

    int getCountOrders();
    
    int getCountOrderLines();

    List<? extends Order> getOrdersByStatus(OrderState state);

    Order updateOrder(Order order);

    List<CounterAuthor> getTopAuthorBySubject(Subject subject, int top);

    List<CounterBook> getBestSellers (Subject subject);
}
