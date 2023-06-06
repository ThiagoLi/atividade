package inf300.facade.impl;

import inf300.domain.Subject;
import inf300.service.impl.AddressServiceImpl;
import inf300.service.impl.AuthorServiceImpl;
import inf300.service.impl.BookServiceImpl;
import inf300.service.impl.CartServiceImpl;
import inf300.service.impl.CountryServiceImpl;
import inf300.service.impl.CustomerServiceImpl;
import inf300.service.impl.ItemStockServiceImpl;
import inf300.service.impl.OrderServiceImpl;
import inf300.service.impl.StockServiceImpl;
import inf300.service.impl.StoreServiceImpl;
import inf300.service.spec.AddressService;
import inf300.service.spec.AuthorService;
import inf300.service.spec.BookService;
import inf300.service.spec.CartService;
import inf300.service.spec.CountryService;
import inf300.service.spec.CustomerService;
import inf300.service.spec.ItemStockService;
import inf300.service.spec.OrderService;
import inf300.service.spec.StockService;
import inf300.service.spec.StoreService;
import inf300.util.Populator;
import inf300.util.PopulatorImpl;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.junit.Test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 5, warmups = 1)
@Warmup(iterations = 3, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 200, timeUnit = TimeUnit.MILLISECONDS)
public class BenchmarkTest {

    public static void main(String[] args) throws Exception {
        final Options opt = new OptionsBuilder()
                .include(BenchmarkTest.class.getSimpleName())
                .forks(3)
                .result("target/result.txt")
                .shouldFailOnError(true)
                .build();
        new Runner(opt).run();
    }

    @Test(timeout = 360000)
    public void benchmarkTest()  {
        try {
            main(null);
        } catch (Exception ex) {
            Logger.getLogger(BenchmarkTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    @State(Scope.Benchmark)
    public static class ExecutionPlan {

        public BookMarketFacadeImpl facadeInstance;

        @Setup(Level.Trial)
        public void setUp() {
            CountryService countryService = CountryServiceImpl.getInstance();
            AddressService addressService = AddressServiceImpl.getInstance(countryService);
            ItemStockService itemStockService = ItemStockServiceImpl.getInstance();
            CartService cartService = CartServiceImpl.getInstance(itemStockService);
            BookService bookService = BookServiceImpl.getInstance();
            CustomerService customerService = CustomerServiceImpl.getInstance(addressService);
            AuthorService authorService = AuthorServiceImpl.getInstance();
            StockService stockService = StockServiceImpl.getInstance();
            OrderService orderService = OrderServiceImpl.getInstance(bookService);
            StoreService storeService = StoreServiceImpl.getInstance();
            facadeInstance = BookMarketFacadeImpl.getInstance(addressService,
                    cartService, countryService,
                    bookService, customerService,
                    authorService, stockService,
                    itemStockService, orderService,
                    storeService);
            Populator populator = new PopulatorImpl(addressService,
                    cartService, countryService,
                    bookService, customerService,
                    authorService, stockService,
                    itemStockService, orderService,
                    storeService);
            long seed = 0;
            long now = System.currentTimeMillis();
            int items = 10000;
            int customers = 10;
            int addresses = 10;
            int authors = 1000;
            int orders = 100000;
            int stores = 3;
            int stocks = 10;
            populator.populate(seed, now, items, customers, addresses, authors, orders, stores, stocks);
        }
    }

    @Benchmark
    public static void getTopAuthorBySubjectTest(ExecutionPlan plan) throws InterruptedException, Throwable {
        for (int i = 0; i < Subject.values().length; i++) {
            plan.facadeInstance.getTopAuthorBySubject(Subject.values()[i], 10);
        }
    }

}
