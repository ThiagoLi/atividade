package inf300.service.impl;

import inf300.domain.Author;
import inf300.domain.Backing;
import inf300.domain.Book;
import inf300.domain.Subject;
import inf300.service.spec.BookService;
import inf300.util.RandomUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

public final class BookServiceImpl implements BookService {

    private static BookService instance;

    private final List<Book> booksById;

    public synchronized static BookService getInstance() {
        if (instance == null) {
            instance = new BookServiceImpl();
        }
        return instance;
    }

    private BookServiceImpl() {
        this(new ArrayList<>());
    }

    private BookServiceImpl(List<Book> booksById) {
        this.booksById = booksById;
    }

    @Override
    public Optional<Book> getBook(int bId) {
        return Optional.ofNullable(booksById.get(bId));
    }

    @Override
    public Book getBookById(int bId) {
        return booksById.get(bId);
    }

    @Override
    public Book getABookAnyBook(Random random) {
        return booksById.get(RandomUtil.getRandomInt(random, 0, booksById.size() - 1));
    }

    @Override
    public List<Book> getBooksBySubject(Subject subject) {
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : booksById) {
            if (subject.equals(book.getSubject())) {
                books.add(book);
                if (books.size() > 50) {
                    break;
                }
            }
        }
        Collections.sort(books, (Book a, Book b) -> a.getTitle().compareTo(b.getTitle()));
        return books;
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : booksById) {
            if (book.getTitle().startsWith(title)) {
                books.add(book);
                if (books.size() > 50) {
                    break;
                }
            }
        }
        Collections.sort(books, (Book a, Book b) -> a.getTitle().compareTo(b.getTitle()));
        return books;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        Pattern regex = Pattern.compile("^" + author);
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : booksById) {
            if (regex.matcher(book.getAuthor().getLname()).matches()) {
                books.add(book);
                if (books.size() > 50) {
                    break;
                }
            }
        }
        Collections.sort(books, (Book a, Book b) -> a.getTitle().compareTo(b.getTitle()));
        return books;
    }



    @Override
    public List<? extends Book> getNewBooks(Subject subject) {
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : booksById) {
            if (subject.equals(book.getSubject())) {
                books.add(book);
            }
        }
        Collections.sort(books, new Comparator<Book>() {
            public int compare(Book a, Book b) {
                int result = b.getPubDate().compareTo(a.getPubDate());
                if (result == 0) {
                    result = a.getTitle().compareTo(b.getTitle());
                }
                return result;
            }
        });
        return books.subList(0, books.size() >= 50 ? 50 : books.size());
    }


    @Override
    public List<? extends Book> getAllBooks() {
        return booksById;
    }

    @Override
    public synchronized Book createBook(String title, Date pubDate, String publisher,
            Subject subject, String desc, String thumbnail,
            String image, double srp, Date avail, String isbn,
            int page, Backing backing, String dimensions, Author author
    ) {
        int id = booksById.size();
        Book book = new Book(id, title, pubDate, publisher, subject, desc,
                thumbnail, image, srp, avail, isbn, page, backing,
                dimensions, author);
        booksById.add(book);
        return book;
    }

    /**
     *
     * @param book
     * @return 
     */
    @Override
    public Book updateBook(Book book){
        return book;
    }



}
