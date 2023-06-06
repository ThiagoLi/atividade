package inf300.service.impl;

import inf300.domain.Country;
import inf300.service.spec.CountryService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * CountryServiceImpl provides methods for retrieving countries by name, ID, or
 * randomly, creating new countries, and getting the count of stored countries.
 * It uses a combination of a list and a map to efficiently store and retrieve
 * country objects.
 *
 * @author esoft
 */
public final class CountryServiceImpl implements CountryService {

    // It holds the singleton instance of the CountryService.
    private static CountryService instance;
    // It holds the countries in the order of their IDs
    private final List<Country> countryById;
    // It allows accessing countries by their names.
    private final Map<String, Country> countryByName;

    /**
     * This is the implementation of the singleton pattern's getInstance()
     * method. It ensures that only one instance of CountryServiceImpl is
     * created and returned. The method is synchronized to support thread-safe
     * initialization of the singleton.
     *
     * @return
     */
    public synchronized static CountryService getInstance() {
        if (instance == null) {
            instance = new CountryServiceImpl();
        }
        return instance;
    }

    private CountryServiceImpl() {
        this(new ArrayList<>(), new HashMap<>());
    }

    private CountryServiceImpl(List<Country> countryById, Map<String, Country> countryByName) {
        this.countryById = countryById;
        this.countryByName = countryByName;
    }

    /**
     * This method tries to retrieve a country from the countryByName map based
     * on its name. If the country does not exist, it creates a new country with
     * the provided name, an empty currency, and an exchange rate of 0. The
     * country is then added to the map and returned.
     *
     * @param name
     * @return
     */
    public Country alwaysGetCountry(String name) {
        Country country = countryByName.get(name);
        if (country == null) {
            country = createCountry(name, "", 0);
        }
        return country;
    }

    /**
     * This method returns a randomly selected country from the countryById
     * list. It uses the random parameter to generate a random index and
     * retrieves the corresponding country from the list.
     *
     * @param random
     * @return
     */
    @Override
    public Country getACountryAnyCountry(Random random) {
        return countryById.get(random.nextInt(countryById.size()));
    }

    /**
     * This method attempts to find a country by its name. It uses the Java
     * Stream API to filter the countryById list based on the provided
     * countryName. The comparison is case-insensitive. If a matching country is
     * found, it is returned; otherwise, null is returned.
     *
     * @param countryName
     * @return
     */
    @Override
    public Country getCountryByName(String countryName) {
        Optional<Country> opC = countryById.stream().filter(c -> c.getName().toUpperCase().equals(countryName.toUpperCase())).findFirst();
        return opC.isPresent() ? opC.get() : null;
    }

    @Override
    public Country getCountryById(int cId) {
        Country country = countryById.get(cId);
        return country;
    }

    /**
     * This method creates a new country with the provided name, currency, and
     * exchange rate. The country is assigned a unique ID based on the size of
     * the countryById list. It is then added to both countryById and
     * countryByName for easy retrieval. Finally, the newly created country is
     * returned.
     *
     * @param name
     * @param currency
     * @param exchange
     * @return
     */
    @Override
    public synchronized Country createCountry(String name, String currency, double exchange) {
        int id = countryById.size();
        Country country = new Country(id, name, currency, exchange);
        countryById.add(country);
        countryByName.put(name, country);
        return country;
    }

    @Override
    public int getCountCountries() {
        return countryById.size();
    }

}
