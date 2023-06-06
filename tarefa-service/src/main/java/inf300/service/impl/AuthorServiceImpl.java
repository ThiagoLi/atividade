package inf300.service.impl;

import inf300.domain.Author;
import inf300.service.spec.AuthorService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * AuthorServiceImpl provides an implementation of the AuthorService interface
 * for managing authors. The Singleton pattern is used to ensure that only one
 * instance of AuthorService exists, and the class provides methods for creating
 * authors, retrieving random authors, and retrieving authors by their IDs.
 *
 * @author esoft
 */
public final class AuthorServiceImpl implements AuthorService {

    private static AuthorService instance;
    private final List<Author> authorsById;

    public synchronized static AuthorService getInstance() {
        if (instance == null) {
            instance = new AuthorServiceImpl();
        }
        return instance;
    }

    private AuthorServiceImpl() {
        this(new ArrayList<>());
    }

    private AuthorServiceImpl(List<Author> authorsById) {
        this.authorsById = authorsById;
    }

    @Override
    public Author getAnAuthorAnyAuthor(Random random) {
        return authorsById.get(random.nextInt(authorsById.size()));
    }

    @Override
    public Author createAuthor(String fname, String mname, String lname,
            Date birthdate, String bio) {
        Author author = new Author(fname, mname, lname, birthdate, bio);
        authorsById.add(author);
        return author;
    }

    @Override
    public Author getAuthorById(int id) {
        return authorsById.get(id);
    }

}
