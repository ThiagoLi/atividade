package inf300.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Date;

public class CCTransactionTest {

    @Test
    public void testCCTransaction() {
        
        CreditCard type = CreditCard.VISA; // Define o tipo de cartão de crédito como VISA
        long num = 1234567890123456L; // Número de cartão de crédito randomico
        String name = "Thiago de Lima"; // Nome do titular do cartão 
        Date expire = new Date(); // Data de vencimento do cartão 
        String authId = "AUTH123"; // ID Identificador 
        double amount = 500.0; // Valor da transação 
        Date date = new Date(); // Data da transação, o new date ir[a pegar a data atual
        Country country = new Country(1, "Country", "Currency", 1.0); // Criando um objeto Country 

        // Cria uma instância da classe CCTransaction
        CCTransaction transaction = new CCTransaction(type, num, name, expire, authId, amount, date, country);

       
        assertEquals(type, transaction.getType()); // Verifica se o tipo do cartão é o esperado
        assertEquals(num, transaction.getNum()); // Verifica se o número do cartão é o esperado
        assertEquals(name, transaction.getName()); // Verifica se o nome do titular do cartão de crédito é o esperado
        assertEquals(expire, transaction.getExpire()); // Verifica se a data de expiração é a esperada
        assertEquals(authId, transaction.getAuthId()); // Verifica se o ID identificador é o esperado
        assertEquals(amount, transaction.getAmount(), 0.0); // Verifica se o valor do pagamento é o esperado
        assertEquals(date, transaction.getDate()); // Verifica se a data da transação é a esperada
        assertEquals(country, transaction.getCountry()); // Verifica se o objeto Country é o esperado
    }
}