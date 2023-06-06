package inf300.domain;

import static org.junit.Assert.assertEquals;
import inf300.domain.Author;

import java.util.Date;

import org.junit.Test;

/**
 * This test class checks that the constructor of the Author class correctly
 * initializes the private fields of an Author object and that the getter
 * methods return the expected values. Note that in this example, we use JUnit 4
 * for testing, so you will need to include the JUnit 4 library in your project
 * for this test class to work.
 *
 * @author esoft
 */
public class AuthorTest {

    @Test
    public void testAuthorConstructorAndGetters() {
        String fname = "John";
        String mname = "F.";
        String lname = "Doe";
        Date birthdate = new Date(1);
        String bio = "John F. Doe is a fictional character.";

        Author author = new Author(fname, mname, lname, birthdate, bio);
             

        assertEquals(fname, author.getFname());
        assertEquals(mname, author.getMname());
        assertEquals(lname, author.getLname());
        assertEquals(birthdate, author.getBirthdate());
        assertEquals(bio, author.getBio());
    }
}
