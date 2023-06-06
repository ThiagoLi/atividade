package inf300.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import static org.junit.Assert.*;

public class BookTest {

    private Book book1;
    private Book book2;

    @Before
    public void setUp() {
        String fname = "John";
        String mname = "F.";
        String lname = "Doe";
        Date birthdate = new Date(0);
        String bio = "John F. Doe is a fictional character.";

        Author author = new Author(fname, mname, lname, birthdate, bio);
        Date pubDate = new Date(0);
        Date avail = new Date(0);
        book1 = new Book(1, "Example Book", pubDate, "Example Publisher",
                Subject.ARTS, "Example Description", "thumbnail.jpg",
                "image.jpg", 9.99, avail, "1234567890", 200, Backing.PAPERBACK,
                "20x30cm", author);
        book2 = new Book(2, "Example Book", pubDate, "Example Publisher",
                Subject.ARTS, "Example Description", "thumbnail.jpg",
                "image.jpg", 9.99, avail, "1234567890", 200, Backing.PAPERBACK,
                "20x30cm", author);
    }
    
    @Test
    public void testSetRelated() {
        book1.setRelated1(book2);
        book1.setRelated2(book2);
        book1.setRelated3(book2);
        book1.setRelated4(book2);
        book1.setRelated5(book2);
        assertEquals( book2, book1.getRelated1());
        assertEquals( book2, book1.getRelated2());
        assertEquals( book2, book1.getRelated3());
        assertEquals( book2, book1.getRelated4());
        assertEquals( book2, book1.getRelated5());
        
    }

    @Test
    public void testGetTitle() {
        assertEquals("Example Book", book1.getTitle());
    }

    @Test
    public void testSetThumbnail() {
        book1.setThumbnail("new_thumbnail.jpg");
        assertEquals("new_thumbnail.jpg", book1.getThumbnail());
    }



    @Test
    public void testSetImage() {
        book1.setImage("new_image.jpg");
        assertEquals("new_image.jpg", book1.getImage());
    }

    @Test
    public void testGetAuthor() {
        assertEquals("John", book1.getAuthor().getFname());
    }

    @Test
    public void testGetSrp() {
        assertEquals(9.99, book1.getSrp(), 0.001);
    }


    @Test
    public void testGetDesc() {
        assertEquals("Example Description", book1.getDesc());
    }

    @Test
    public void testGetPage() {
        assertEquals(200, book1.getPage());
    }

    @Test
    public void testGetBacking() {
        assertEquals(Backing.PAPERBACK, book1.getBacking());
    }

    @Test
    public void testGetPubDate() {
        assertNotNull(book1.getPubDate());
    }

    @Test
    public void testSetPubDate() {
        Date newDate = new Date(1645742400000L); // Feb 25 2022
        book1.setPubDate(newDate);
        assertEquals(newDate, book1.getPubDate());
    }

    @Test
    public void testGetPublisher() {
        assertEquals("Example Publisher", book1.getPublisher());
    }

    @Test
    public void testGetIsbn() {
        assertEquals("1234567890", book1.getIsbn());
    }

    @Test
    public void testGetId() {
        assertEquals(1, book1.getId());
    }

    @Test
    public void testGetDimensions() {
        assertEquals("20x30cm", book1.getDimensions());
    }

    @Test
    public void testGetSubject() {
        assertEquals(Subject.ARTS, book1.getSubject());
    }

    @Test
    public void testGetAvail() {
        assertNotNull(book1.getAvail());
    }
    
    
    @Test
    public void testToString() {
        assertEquals("Item{id=1, title=Example Book}Book{subject=ARTS}", book1.toString());
    }


}               
