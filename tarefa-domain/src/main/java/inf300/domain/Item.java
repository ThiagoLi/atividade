package inf300.domain;

import java.util.Objects;

/**
 * <pre>
 * This class has two final attributes: an integer id and a String title. It
 * also has a constructor that takes in the id and title as parameters, as well
 * as getter methods for each attribute that return their respective values.
 * 
 * In addition, the class overrides two methods: equals() and hashCode(). The
 * equals() method checks if the object passed in as a parameter is an instance
 * of Item and compares their ids. The hashCode() method returns the value of
 * the id.
 * 
 * This class is a base class for other types of items in a system,
 * such as books, movies, or products. It's a good practice to create abstract
 * classes like this to avoid code duplication in subclasses that share common
 * attributes and behaviors.
 * </pre>
 * <img src="./doc-files/Item.png" alt="Item">
 * @author esoft
 */
public abstract class Item {

    private final int id;
    private final String title;

    public Item(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        final Item other = (Item) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.title, other.title);
    }



    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", title=" + title + '}';
    }
    
    

}
