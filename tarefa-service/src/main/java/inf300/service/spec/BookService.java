package inf300.service.spec;

import inf300.domain.Author;
import inf300.domain.Backing;
import inf300.domain.Book;
import inf300.domain.Subject;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public interface BookService {

    Book createBook(String title, Date pubDate, String publisher, Subject subject, String desc, String thumbnail, String image, double srp, Date avail, String isbn, int page, Backing backing, String dimensions, Author author);

    Book getABookAnyBook(Random random);

    Optional<Book> getBook(int bId);

    Book getBookById(int bId);

    Book updateBook(Book book) throws UpdateException;

    List<? extends Book> getBooksByAuthor(String author);

    List<? extends Book> getAllBooks();

    List<? extends Book> getBooksBySubject(Subject subject);

    List<? extends Book> getBooksByTitle(String title);

    List<? extends Book> getNewBooks(Subject subject);

}
