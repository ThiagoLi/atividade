package inf300.util;

import com.github.javafaker.Faker;
import com.github.javafaker.Internet;
import com.github.javafaker.Name;
import com.github.javafaker.PhoneNumber;
import inf300.domain.Address;
import inf300.domain.Author;
import inf300.domain.Backing;
import inf300.domain.Book;
import inf300.domain.CCTransaction;
import inf300.domain.Cart;
import inf300.domain.Country;
import inf300.domain.CreditCard;
import inf300.domain.Customer;
import inf300.domain.Item;
import inf300.domain.ItemStock;
import inf300.domain.OrderState;
import inf300.domain.ShipType;
import inf300.domain.Stock;
import inf300.domain.Subject;
import inf300.service.spec.AddressService;
import inf300.service.spec.AuthorService;
import java.util.Date;
import java.util.List;
import java.util.Random;
import inf300.service.spec.BookService;
import inf300.service.spec.CartService;
import inf300.service.spec.CountryService;
import inf300.service.spec.CustomerService;
import inf300.service.spec.ItemStockService;
import inf300.service.spec.OrderService;
import inf300.service.spec.StockService;
import inf300.service.spec.StoreService;

/**
 * <pre>
 * PopulatorImpl is responsible for populating various entities such as
 * countries, addresses, customers, authors, books, stores, stocks, item stocks,
 * and orders in a system.
 *
 * The PopulatorImpl class uses the Faker library to generate fake data for the
 * entities. It has dependencies on several services including AddressService,
 * CartService, CountryService, BookService, CustomerService, AuthorService,
 * StockService, ItemStockService, OrderService, and StoreService.
 *
 * The populate() method is used to initiate the population process. It checks
 * if the population has already been done before proceeding. If not, it
 * generates a random seed value and the current timestamp. It also specifies
 * the number of items, customers, addresses, authors, orders, stores, and
 * stocks to be generated. Then, it calls the populate() method with the
 * specified parameters.
 *
 * The populate(long seed, long now, int items, int customers, int addresses,
 * int authors, int orders, int stores, int stocks) method is the core method
 * responsible for populating the entities. It starts by populating the list of
 * countries with their respective currencies and exchange rates.
 *
 * Next, it generates the specified number of addresses using the Faker library
 * and associates them with a randomly selected country.
 *
 * Then, it creates customers by generating random names, addresses, phone
 * numbers, email addresses, registration dates, last visit dates, login dates,
 * account expiration dates, discounts, birthdates, and additional data. The
 * customer data is generated based on the specified number and the current
 * timestamp.
 *
 * After that, it creates authors with random first names, middle names, last
 * names, birthdates, and biographies.
 *
 * Next, it creates books with random titles, publication dates, publishers,
 * subjects, descriptions, thumbnails, images, suggested retail prices (SRP),
 * availability dates, ISBN numbers, page counts, backing types, dimensions, and
 * associated authors. The book data is generated based on the specified number
 * and random values.
 *
 * Once the books are created, it retrieves all the books from the book service
 * and stores them in the all list.
 *
 * Lastly, it creates relationships between books by randomly selecting related
 * books from the all list.
 *
 * Overall, this code demonstrates a data population process where fake data is
 * generated for various entities in a system using the Faker library. The
 * population process follows a specific order to ensure the necessary
 * dependencies between entities are met.
 * </pre>
 *
 * @author esoft
 */
public class PopulatorImpl implements Populator {

    private final Faker faker = new Faker();
    private final AddressService addressService;
    private final CartService cartService;
    private final CountryService countryService;
    private final BookService bookService;
    private final CustomerService customerService;
    private final AuthorService authorService;
    private final StockService stockService;
    private final ItemStockService itemStockService;
    private final OrderService orderService;
    private final StoreService storeService;

    private boolean populated = false;

    private List<Book> all;

    public PopulatorImpl(AddressService addressService,
            CartService cartService,
            CountryService countryService,
            BookService bookService,
            CustomerService customerService,
            AuthorService authorService,
            StockService stockService,
            ItemStockService itemStockService,
            OrderService orderService,
            StoreService storeService) {
        this.addressService = addressService;
        this.cartService = cartService;
        this.countryService = countryService;
        this.bookService = bookService;
        this.customerService = customerService;
        this.authorService = authorService;
        this.stockService = stockService;
        this.itemStockService = itemStockService;
        this.orderService = orderService;
        this.storeService = storeService;
    }

    @Override
    public synchronized void populate() {
        if (!populated) {
            long seed = 0;
            long now = System.currentTimeMillis();
            int items = 100000;
            int customers = 1000;
            int addresses = 1000;
            int authors = 100;
            int orders = 100000;
            int stores = 10;
            int stocks = 200;
            this.populate(seed, now, items, customers, addresses, authors, orders, stores, stocks);
            populated = true;

        }

    }

    /**
     *
     * @param seed
     * @param now
     * @param items
     * @param customers
     * @param addresses
     * @param authors
     * @param orders
     * @param stores
     * @param stocks
     * @return
     */
    @Override
    public boolean populate(long seed, long now, int items, int customers,
            int addresses, int authors, int orders, int stores, int stocks) {

        System.out.println("Beginning population.");
        Random rand = new Random(seed);
        populateCountries();
        populateAddresses(addresses, rand);
        populateCustomers(customers, rand, now);
        populateAuthor(authors, rand);
        populateBooks(items, rand);
        populateStores(stores, rand);
        populateStocks(stocks, rand);
        populateItemStocks(items, stocks, rand, now);
        populateOrders(orders, rand, now);

        System.out.println("Finished population.");
        return true;
    }

    private void populateCountries() {
        String[] countries = {"United States", "United Kingdom", "Canada",
            "Germany", "France", "Japan", "Netherlands",
            "Italy", "Switzerland", "Australia", "Algeria",
            "Argentina", "Armenia", "Austria", "Azerbaijan",
            "Bahamas", "Bahrain", "Bangla Desh", "Barbados",
            "Belarus", "Belgium", "Bermuda", "Bolivia",
            "Botswana", "Brazil", "Bulgaria", "Cayman Islands",
            "Chad", "Chile", "China", "Christmas Island",
            "Colombia", "Croatia", "Cuba", "Cyprus",
            "Czech Republic", "Denmark", "Dominican Republic",
            "Eastern Caribbean", "Ecuador", "Egypt",
            "El Salvador", "Estonia", "Ethiopia",
            "Falkland Island", "Faroe Island", "Fiji",
            "Finland", "Gabon", "Gibraltar", "Greece", "Guam",
            "Hong Kong", "Hungary", "Iceland", "India",
            "Indonesia", "Iran", "Iraq", "Ireland", "Israel",
            "Jamaica", "Jordan", "Kazakhstan", "Kuwait",
            "Lebanon", "Luxembourg", "Malaysia", "Mexico",
            "Mauritius", "New Zealand", "Norway", "Pakistan",
            "Philippines", "Poland", "Portugal", "Romania",
            "Russia", "Saudi Arabia", "Singapore", "Slovakia",
            "South Africa", "South Korea", "Spain", "Sudan",
            "Sweden", "Taiwan", "Thailand", "Trinidad",
            "Turkey", "Venezuela", "Zambia"};

        double[] exchanges = {1, .625461, 1.46712, 1.86125, 6.24238, 121.907,
            2.09715, 1842.64, 1.51645, 1.54208, 65.3851,
            0.998, 540.92, 13.0949, 3977, 1, .3757,
            48.65, 2, 248000, 38.3892, 1, 5.74, 4.7304,
            1.71, 1846, .8282, 627.1999, 494.2, 8.278,
            1.5391, 1677, 7.3044, 23, .543, 36.0127,
            7.0707, 15.8, 2.7, 9600, 3.33771, 8.7,
            14.9912, 7.7, .6255, 7.124, 1.9724, 5.65822,
            627.1999, .6255, 309.214, 1, 7.75473, 237.23,
            74.147, 42.75, 8100, 3000, .3083, .749481,
            4.12, 37.4, 0.708, 150, .3062, 1502, 38.3892,
            3.8, 9.6287, 25.245, 1.87539, 7.83101,
            52, 37.8501, 3.9525, 190.788, 15180.2,
            24.43, 3.7501, 1.72929, 43.9642, 6.25845,
            1190.15, 158.34, 5.282, 8.54477, 32.77, 37.1414,
            6.1764, 401500, 596, 2447.7};

        String[] currencies = {"Dollars", "Pounds", "Dollars", "Deutsche Marks",
            "Francs", "Yen", "Guilders", "Lira", "Francs",
            "Dollars", "Dinars", "Pesos", "Dram",
            "Schillings", "Manat", "Dollars", "Dinar", "Taka",
            "Dollars", "Rouble", "Francs", "Dollars",
            "Boliviano", "Pula", "Real", "Lev", "Dollars",
            "Franc", "Pesos", "Yuan Renmimbi", "Dollars",
            "Pesos", "Kuna", "Pesos", "Pounds", "Koruna",
            "Kroner", "Pesos", "Dollars", "Sucre", "Pounds",
            "Colon", "Kroon", "Birr", "Pound", "Krone",
            "Dollars", "Markka", "Franc", "Pound", "Drachmas",
            "Dollars", "Dollars", "Forint", "Krona", "Rupees",
            "Rupiah", "Rial", "Dinar", "Punt", "Shekels",
            "Dollars", "Dinar", "Tenge", "Dinar", "Pounds",
            "Francs", "Ringgit", "Pesos", "Rupees", "Dollars",
            "Kroner", "Rupees", "Pesos", "Zloty", "Escudo",
            "Leu", "Rubles", "Riyal", "Dollars", "Koruna",
            "Rand", "Won", "Pesetas", "Dinar", "Krona",
            "Dollars", "Baht", "Dollars", "Lira", "Bolivar",
            "Kwacha"};

        System.out.print("Creating " + countries.length + " countries...");

        for (int i = 0; i < countries.length; i++) {
            countryService.createCountry(countries[i], currencies[i], exchanges[i]);
        }

        System.out.println(" Done");
    }

    private void populateAddresses(int number, Random rand) {
        System.out.print("Creating " + number + " addresses...");

        for (int i = 0; i < number; i++) {
            if (i % 10000 == 0) {
                System.out.print(".");
            }

            final com.github.javafaker.Address fa = faker.address();
            final String street1 = fa.streetAddress();
            final String street2 = fa.streetAddress(true);
            final String city = fa.cityName();
            final String state = fa.state();
            final String zip = fa.zipCode();
            final String latitude = fa.latitude();
            final String longitude = fa.longitude();
            final String buildingNumber = fa.buildingNumber();

            Country country = countryService.getCountryByName(fa.country());
            if (country == null) {
                country = countryService.getACountryAnyCountry(rand);
            }
            addressService.createAddress(street1, street2,
                    city, state, zip, country, latitude, longitude, buildingNumber);
        }

        System.out.println(" Done");
    }

    private void populateCustomers(int number, Random rand, long now) {
        System.out.print("Creating " + number + " customers...");

        for (int i = 0; i < number; i++) {
            if (i % 10000 == 0) {
                System.out.print(".");
            }

            final Name name = faker.name();
            final PhoneNumber phoneNumber = faker.phoneNumber();
            final Internet internet = faker.internet();
            //
            final String fname = name.firstName();
            final String lname = name.lastName();
            final Address address = addressService.getAnAddressAnyAddress(rand);
            final String phone = phoneNumber.phoneNumber();
            final String email = internet.emailAddress();
            final Date since = new Date(now - RandomUtil.getRandomInt(rand, 1, 730) * 86400000 /* a day */);
            final Date lastVisit = new Date(now - RandomUtil.getRandomInt(rand, 1, 730) * 86400000 + RandomUtil.getRandomInt(rand, 0, 60) * 86400000 /* a day */);
            final Date login = new Date(now);
            final Date expiration = new Date(now + 7200000 /* 2 hours */);
            final double discount = rand.nextInt(51);
            final Date birthdate = RandomUtil.getRandomBirthdate(rand);
            final String data = RandomUtil.getRandomString(rand, 100, 500);

            customerService.createCustomer(fname, lname, address,
                    phone, email, since, lastVisit,
                    login, expiration, discount, birthdate,
                    data);
        }

        System.out.println(" Done");
    }

    private void populateAuthor(int number, Random rand) {
        System.out.print("Creating " + number + " authors...");

        for (int i = 0; i < number; i++) {
            if (i % 10000 == 0) {
                System.out.print(".");
            }
            final String fname = RandomUtil.getRandomFname(rand);
            final String mname = RandomUtil.getRandomString(rand, 1, 20);
            final String lname = RandomUtil.getRandomLname(rand);
            final Date birthdate = RandomUtil.getRandomBirthdate(rand);
            final String bio = RandomUtil.getRandomString(rand, 125, 500);
            authorService.createAuthor(fname, mname, lname, birthdate, bio);
        }

        System.out.println(" Done");
    }

    private void populateBooks(int number, Random rand) {

        System.out.print("Creating " + number + " books...");

        for (int i = 0; i < number; i++) {
            if (i % 10000 == 0) {
                System.out.print(".");
            }

            final com.github.javafaker.Book fbook = faker.book();
            final String title = fbook.title();
            final Date pubDate = RandomUtil.getRandomPublishdate(rand);
            final String publisher = fbook.publisher();
            final Subject subject = Subject.values()[rand.nextInt(Subject.values().length)];
            final String desc = RandomUtil.getRandomString(rand, 100, 500);
            final String thumbnail = "img" + i % 100 + "/thumb_" + i + ".gif";
            final String image = "img" + i % 100 + "/image_" + i + ".gif";
            final double srp = RandomUtil.getRandomInt(rand, 100, 99999) / 100.0;
            final Date avail = new Date(pubDate.getTime()
                    + RandomUtil.getRandomInt(rand, 1, 30) * 86400000 /* a day */);
            final String isbn = RandomUtil.getRandomString(rand, 13, 13);
            final int page = RandomUtil.getRandomInt(rand, 20, 9999);
            final Backing backing = Backing.values()[rand.nextInt(Backing.values().length)];
            final String dimensions = (RandomUtil.getRandomInt(rand, 1, 9999) / 100.0) + "x"
                    + (RandomUtil.getRandomInt(rand, 1, 9999) / 100.0) + "x"
                    + (RandomUtil.getRandomInt(rand, 1, 9999) / 100.0);
            final Author author = authorService.getAnAuthorAnyAuthor(rand);
            bookService.createBook(title, pubDate, publisher,
                    subject, desc, thumbnail,
                    image, srp, avail, isbn,
                    page, backing, dimensions, author
            );
        }

        all = (List<Book>) bookService.getAllBooks();

        for (int i = 0; i < number; i++) {
            Book book = all.get(i);
            Book[] relatedArray = new Book[]{
                all.get(RandomUtil.getRandomInt(rand, 0, number - 1)),
                all.get(RandomUtil.getRandomInt(rand, 0, number - 1)),
                all.get(RandomUtil.getRandomInt(rand, 0, number - 1)),
                all.get(RandomUtil.getRandomInt(rand, 0, number - 1)),
                all.get(RandomUtil.getRandomInt(rand, 0, number - 1))
            };
            book.setRelated1(relatedArray[0]);
            book.setRelated2(relatedArray[1]);
            book.setRelated3(relatedArray[2]);
            book.setRelated4(relatedArray[3]);
            book.setRelated5(relatedArray[4]);
        }

        System.out.println(" Done");
    }

    void populateStores(int numberStore, Random rand) {
        System.out.print("Creating " + numberStore + " stores...");
        for (int i = 0; i < numberStore; i++) {
            final String name = RandomUtil.getRandomString(rand, 3, 20);
            final String phone = RandomUtil.getRandomString(rand, 1, 20);
            storeService.createStore(name, phone);
        }
        System.out.println(" Done");
    }

    void populateStocks(int stocks, Random rand) {
        System.out.print("Creating " + stocks + " stock...");
        for (int i = 0; i < stocks; i++) {
            Country country = countryService.getCountryById(i % countryService.getCountCountries());
            Address address = addressService.createAddress("123 Main St", "", "Anytown", "CA", "12345", country, "", "", "");
            stockService.createStock(address, storeService.getAStoreAnyStore(rand));

        }
        System.out.println(" Done");
    }

    void populateItemStocks(int numberBook, int nStocks, Random rand, long now) {
        System.out.print("Creating " + (numberBook * nStocks) + " itens stock...");
        for (int j = 0; j < nStocks; j++) {
            final Stock stock = stockService.getStockById(j).get();
            for (int i = 0; i < numberBook; i++) {
                final Item item = all.get(i);
                final double srp = RandomUtil.getRandomInt(rand, 100, 99999) / 100.0;
                final double cost = srp * RandomUtil.getRandomInt(rand, 50, 100) / 100.0;
                int quantity = RandomUtil.getRandomInt(rand, 1, 300);
                itemStockService.createItemStock(item, stock, cost, quantity);
            }
        }
        System.out.println(" Done");
    }

    void populateOrders(int number, Random rand, long now) {

        System.out.print("Creating " + number + " orders...");

        for (int i = 0; i < number; i++) {
            if (i % 10000 == 0) {
                System.out.print(".");
            }
            int nStocks = RandomUtil.getRandomInt(rand, 1, 5);
            Cart cart = new Cart(-1, null);
            String comment = RandomUtil.getRandomString(rand, 20, 100);
            for (int j = 0; j < nStocks; j++) {
                ItemStock stock = itemStockService.getAItemStockAnyItemStock(rand);
                int quantity = RandomUtil.getRandomInt(rand, 1, 300);
                cart.changeLine(stock, quantity);
            }

            Customer customer = customerService.getACustomerAnyCustomer(rand);
            CCTransaction ccTransact = new CCTransaction(
                    CreditCard.values()[rand.nextInt(CreditCard.values().length)],
                    RandomUtil.getRandomLong(rand, 1000000000000000L, 9999999999999999L),
                    RandomUtil.getRandomString(rand, 14, 30),
                    new Date(now + RandomUtil.getRandomInt(rand, 10, 730) * 86400000 /* a day */),
                    RandomUtil.getRandomString(rand, 15, 15),
                    cart.total(customer.getDiscount()),
                    new Date(now),
                    countryService.getACountryAnyCountry(rand));
            long orderDate = now - RandomUtil.getRandomInt(rand, 53, 60) * 86400000 /* a day */;
            long shipDate = orderDate + RandomUtil.getRandomInt(rand, 0, 7) * 86400000 /* a day */;
            orderService.createOrder(
                    customer,
                    new Date(orderDate),
                    cart, comment,
                    ShipType.values()[rand.nextInt(ShipType.values().length)],
                    new Date(shipDate),
                    OrderState.values()[rand.nextInt(OrderState.values().length)],
                    addressService.getAnAddressAnyAddress(rand),
                    addressService.getAnAddressAnyAddress(rand),
                    ccTransact);
        }

        System.out.println(" Done");
    }

}
