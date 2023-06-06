package inf300.domain;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddressTest {
	
    @Test
    public void testEquals() {
        // Criando uma instância de Country para ser usada como atributo na classe Address
        Country country = new Country(1, "Country", "Currency", 1.0);

        // Criando duas instâncias de Address com os mesmos atributos
        Address address1 = new Address(1, "Rua Carlos Roberto", "Jacob de Freitas", "Pindamonhangaba", "SP", "12430", country, "Latitude", "Longitude", "75");
        Address address2 = new Address(1, "Rua Carlos Roberto", "Jacob de Freitas", "Pindamonhangaba", "SP", "12430", country, "Latitude", "Longitude", "75");
        
        // Criando uma outra instância de Address com um ID diferente
        Address address3 = new Address(2, "Rua cacapava", "Santos", "Pindamonhangaba", "SP", "12430", country, "Latitude", "Longitude", "878");

        // Testa se duas instâncias de Address com os mesmos atributos são consideradas iguais
        assertTrue(address1.equals(address2));

        // Testa se duas instâncias de Address com IDs diferentes são consideradas diferentes
        assertFalse(address1.equals(address3));
    }

    @Test
    public void testHashCode() {
        // Cria uma instância de Country para ser usada como atributo na classe Address
        Country country = new Country(1, "Country", "Currency", 1.0);

        // Cria duas instâncias de Address com os mesmos atributos
        Address address1 = new Address(1, "Rua Carlos Roberto", "Jacob de Freitas", "Pindamonhangaba", "SP", "12430", country, "Latitude", "Longitude", "75");
        Address address2 = new Address(1, "Rua Carlos Roberto", "Jacob de Freitas", "Pindamonhangaba", "SP", "12430", country, "Latitude", "Longitude", "75");

        // Testa se duas instâncias de Address com os mesmos atributos possuem o mesmo código hash
        assertEquals(address1.hashCode(), address2.hashCode());
    }
}
