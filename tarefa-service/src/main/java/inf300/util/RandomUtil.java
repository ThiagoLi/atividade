package inf300.util;

/* 
 * RandomUtil.java - Some random utility functions needed by the servlets.
 *
 ************************************************************************
 
 *
 ************************************************************************/
import java.util.*;

/**
 * <pre>
 * RandomUtil class provides various methods to generate random values and
 * perform specific operations.
 *
 *
 * getRandomLname(Random rand): This method takes a Random object as input and
 * returns a random last name from the arrayLnames array.
 *
 * getRandomFname(Random rand): This method takes a Random object as input and
 * returns a random first name from the arrayFnames array.
 *
 * getRandomString(Random rand, int min, int max): This method generates a
 * random string of characters with a length between the specified min and max
 * values. It uses a character array chars to select random characters and
 * builds the string using a StringBuilder.
 *
 * getRandomInt(Random rand, int lower, int upper): This method generates a
 * random integer between the specified lower and upper bounds (inclusive) using
 * the nextInt() method of the Random object.
 *
 * getRandomLong(Random rand, long lower, long upper): This method generates a
 * random long integer between the specified lower and upper bounds (inclusive)
 * using the nextDouble() method of the Random object.
 *
 * getRandomBirthdate(Random rand): This method generates a random birthdate
 * (java.util.Date) between the years 1880 and 2000. It uses the getRandomInt()
 * method to generate random values for the year, month, and day, and creates a
 * GregorianCalendar object with these values.
 *
 * getRandomPublishdate(Random rand): This method generates a random publication
 * date (java.util.Date) between the years 1930 and 2000. It follows a similar
 * approach to getRandomBirthdate().
 *
 * DigSyl(int d, int n): This method takes an integer d and an integer n as
 * input and returns a string based on a digit-to-syllable mapping. Each digit
 * in d is converted to a corresponding syllable using a switch statement. The
 * resulting string is built based on the syllables.
 *
 * Overall, this RandomUtil class provides utility methods for generating random
 * values such as names, strings, integers, longs, and dates. It also includes a
 * method for converting digits to syllables.
 *
 * </pre>
 * <img src="./doc-files/RandomUtil.png"alt="RandomUtil">
 */
public class RandomUtil {

    private static final String[] arrayFnames = new String[]{
        "Alice", "Miguel", "Sophia", "Arthur", "Helena", "Bernardo", "Valentina", "Heitor", "Laura", "Davi", "Isabella", "Lorenzo", "Manuela", "Théo", "Júlia", "Pedro", "Heloísa", "Gabriel", "Luiza", "Enzo", "Maria", "Luiza", "Matheus", "Lorena", "Lucas", "Lívia", "Benjamin", "Giovanna", "Nicolas", "Maria", "Eduarda", "Guilherme", "Beatriz", "Rafael", "Maria", "Clara", "Joaquim", "Cecília", "Samuel", "Eloá", "Enzo", "Gabriel", "Lara", "João", "Miguel", "Maria", "Júlia", "Henrique", "Isadora", "Gustavo", "Mariana", "Murilo", "Emanuelly", "Pedro", "Henrique", "Ana", "Júlia", "Pietro", "Ana", "Luiza", "Lucca", "Ana", "Clara", "Felipe", "Melissa", "João", "Pedro", "Yasmin", "Isaac", "Maria", "Alice", "Benício", "Isabelly", "Daniel", "Lavínia", "Anthony", "Esther", "Leonardo", "Sarah", "Davi", "Lucca", "Sandro", "Elisa", "Bryan", "Antonella", "Eduardo", "Rafaela", "João", "Lucas", "Maria", "Cecília", "Victor", "Liz", "João", "Marina", "Cauã", "Nicole", "Antônio", "Maitê", "Vicente", "Isis", "Caleb", "Alícia", "Gael", "Luna", "Bento", "Rebeca", "Caio", "Agatha", "Emanuel", "Letícia", "Vinícius", "Maria-", "João", "Guilherme", "Gabriela", "Davi", "Lucas", "Ana", "Laura", "Noah", "Catarina", "João", "Gabriel", "Clara", "João", "Victor", "Ana", "Beatriz", "Luiz", "Miguel", "Vitória", "Francisco", "Olívia", "Kaique", "Maria", "Fernanda", "Otávio", "Emilly", "Augusto", "Maria", "Valentina", "Levi", "Milena", "Yuri", "Maria", "Helena", "Enrico", "Bianca", "Thiago", "Larissa", "Ian", "Mirella", "Victor", "Hugo", "Maria", "Flor", "Thomas", "Allana", "Henry", "Ana", "Sophia", "Luiz", "Felipe", "Clarice", "Ryan", "Pietra", "Arthur", "Miguel", "Maria", "Vitória", "Davi", "Luiz", "Maya", "Nathan", "Laís", "Pedro", "Lucas", "Ayla", "Davi", "Miguel", "Ana", "Lívia", "Raul", "Eduarda", "Pedro", "Miguel", "Mariah", "Luiz", "Henrique", "Stella", "Luan", "Ana", "Erick", "Gabrielly", "Martin", "Sophie", "Bruno", "Carolina", "Rodrigo", "Maria", "Laura", "Luiz", "Gustavo", "Maria", "Heloísa", "Arthur", "Gabriel", "Maria", "Sophia", "Breno", "Fernanda", "Kauê", "Malu", "Enzo", "Miguel", "Analu", "Fernando", "Amanda", "Arthur", "Henrique", "Aurora", "Luiz", "Otávio", "Maria", "Isis", "Carlos", "Eduardo", "Louise", "Tomás", "Heloise", "Lucas", "Gabriel", "Ana", "Vitória", "André", "Ana", "Cecília", "José", "Ana", "Liz", "Yago", "Joana", "Danilo", "Luana", "Anthony", "Gabriel", "Antônia", "Ruan", "Isabel", "Miguel", "Henrique", "Bruna", "Oliver"
    };
    private static final String[] arrayLnames = new String[]{
        "Ferreira",
        "Braga",
        "da Silva",
        "Della Coletta",
        "Zampirolli",
        "Fernandes",
        "Alves",
        "Costalonga",
        "Botteon",
        "Caliman",
        "de Oliveira",
        "Zanette",
        "Salvador",
        "Silva",
        "Zandonadi",
        "Pesca",
        "Falqueto",
        "Tosi",
        "da Costa",
        "de Souza",
        "Gomes",
        "Calmon",
        "Pereira",
        "Sossai detto Pegorer",
        "de Almeida",
        "de Jesus",
        "Martins",
        "Balarini",
        "Rodrigues",
        "Gonçalves",
        "Pizzol",
        "Moreira",
        "Vieira",
        "Venturim",
        "Bazoni",
        "Filete",
        "Almeida",
        "Oliveira",
        "dos Santos",
        "Falchetto",
        "Barbosa",
        "Breda",
        "Scaramussa",
        "de Barros",
        "Marques"};

    /**
     *
     * @param rand
     * @return
     */
    public static String getRandomLname(Random rand) {
        final int index = getRandomInt(rand, 0, arrayLnames.length - 1);
        return arrayLnames[index];
    }

    public static String getRandomFname(Random rand) {
        final int index = getRandomInt(rand, 0, arrayFnames.length - 1);
        return arrayFnames[index];
    }

    /**
     *
     * @param rand
     * @param min
     * @param max
     * @return
     */
    public static String getRandomString(Random rand, int min, int max) {
        StringBuilder newstring = new StringBuilder();
        final char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@', '#',
            '$', '%', '^', '&', '*', '(', ')', '_', '-', '=', '+',
            '{', '}', '[', ']', '|', ':', ';', ',', '.', '?', '/',
            '~', ' '}; //79 characters
        int strlen = getRandomInt(rand, min, max);
        for (int i = 0; i < strlen; i++) {
            newstring.append(chars[rand.nextInt(chars.length)]);
        }
        return newstring.toString();
    }

    /**
     *
     * @param rand
     * @param lower
     * @param upper
     * @return
     */
    public static int getRandomInt(Random rand, int lower, int upper) {
        return rand.nextInt(upper - lower + 1) + lower;
    }

    /**
     *
     * @param rand
     * @param lower
     * @param upper
     * @return
     */
    public static long getRandomLong(Random rand, long lower, long upper) {
        return (long) (rand.nextDouble() * (upper - lower + 1) + lower);
    }

    /**
     *
     * @param rand
     * @return
     */
    public static Date getRandomBirthdate(Random rand) {
        return new GregorianCalendar(
                RandomUtil.getRandomInt(rand, 1880, 2000),
                RandomUtil.getRandomInt(rand, 0, 11),
                RandomUtil.getRandomInt(rand, 1, 30)).getTime();
    }

    /**
     *
     * @param rand
     * @return
     */
    public static Date getRandomPublishdate(Random rand) {
        return new GregorianCalendar(
                RandomUtil.getRandomInt(rand, 1930, 2000),
                RandomUtil.getRandomInt(rand, 0, 11),
                RandomUtil.getRandomInt(rand, 1, 30)).getTime();
    }

    /**
     *
     * @param d
     * @param n
     * @return
     */
    public static String DigSyl(int d, int n) {
        StringBuilder resultString = new StringBuilder();
        String digits = Integer.toString(d);

        if (n > digits.length()) {
            int padding = n - digits.length();
            for (int i = 0; i < padding; i++) {
                resultString = resultString.append("BA");
            }
        }

        for (int i = 0; i < digits.length(); i++) {
            switch (digits.charAt(i)) {
                case '0':
                    resultString = resultString.append("BA");
                    break;
                case '1':
                    resultString = resultString.append("OG");
                    break;
                case '2':
                    resultString = resultString.append("AL");
                    break;
                case '3':
                    resultString = resultString.append("RI");
                    break;
                case '4':
                    resultString = resultString.append("RE");
                    break;
                case '5':
                    resultString = resultString.append("SE");
                    break;
                case '6':
                    resultString = resultString.append("AT");
                    break;
                case '7':
                    resultString = resultString.append("UL");
                    break;
                case '8':
                    resultString = resultString.append("IN");
                    break;
                case '9':
                    resultString = resultString.append("NG");
                    break;
                default:
                    break;
            }
        }

        return resultString.toString();
    }

}
