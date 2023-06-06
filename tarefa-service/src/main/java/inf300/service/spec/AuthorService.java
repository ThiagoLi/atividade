package inf300.service.spec;

import inf300.domain.Author;
import java.util.Date;
import java.util.Random;

public interface AuthorService {

    Author createAuthor(String fname, String mname, String lname, Date birthdate, String bio);

    Author getAnAuthorAnyAuthor(Random random);

    Author getAuthorById(int id);
}
