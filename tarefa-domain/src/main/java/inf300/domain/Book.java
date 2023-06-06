package inf300.domain;

import java.util.Date;


public class Book extends Item {

    private String title ;
    private Date pubDate ;
    private String publisher;
    private String desc;
    private Book related1;
    private Book related2;
    private Book related3;
    private Book related4;
    private Book related5;
    private String thumbnail;
    private String image;
    private double srp;
    private Date avail;
    private String isbn;
    private int page;
    private String dimensions;
    private Author author;
    private Backing	backing;
    private Subject	subject;

    public Book(int id, String title, Date pubDate, String publisher, Subject subject, String desc, String thumbnail, String image, double srp, Date avail, String isbn, int page, Backing backing, String dimensions, Author author) {
        super(id, title);
        this.title = title;
        this.pubDate = pubDate;
        this.publisher = publisher;
        this.desc = desc;
        this.thumbnail = thumbnail;
        this.image = image;
        this.srp = srp;
        this.avail = avail;
        this.isbn = isbn;
        this.page = page;
        this.dimensions = dimensions;
        this.author = author;
        this.backing = backing;
        this.subject = subject;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Book getRelated1() {
        return related1;
    }

    public void setRelated1(Book related1) {
        this.related1 = related1;
    }

    public Book getRelated2() {
        return related2;
    }

    public void setRelated2(Book related2) {
        this.related2 = related2;
    }

    public Book getRelated3() {
        return related3;
    }

    public void setRelated3(Book related3) {
        this.related3 = related3;
    }

    public Book getRelated4() {
        return related4;
    }

    public void setRelated4(Book related4) {
        this.related4 = related4;
    }

    public Book getRelated5() {
        return related5;
    }

    public void setRelated5(Book related5) {
        this.related5 = related5;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getSrp() {
        return srp;
    }

    public void setSrp(double srp) {
        this.srp = srp;
    }

    public Date getAvail() {
        return avail;
    }

    public void setAvail(Date avail) {
        this.avail = avail;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Backing getBacking() {
        return backing;
    }

    public void setBacking(Backing backing) {
        this.backing = backing;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
	public String toString() {
	    return "Item{id=" + getId() + ", title=" + getTitle() + "}Book{subject=" + getSubject() + "}";
	}

}
