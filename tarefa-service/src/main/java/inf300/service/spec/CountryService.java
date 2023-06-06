package inf300.service.spec;

import inf300.domain.Country;
import java.util.Random;

/**
 *
 * @author esoft
 */
public interface CountryService {

    Country createCountry(String name, String currency, double exchange);

    Country getACountryAnyCountry(Random random);

    Country getCountryById(int cId);

    Country getCountryByName(String countryName);
    
    int getCountCountries();
    
    Country alwaysGetCountry(String name);

}
