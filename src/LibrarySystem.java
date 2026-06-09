import java.util.ArrayList;
import java.util.List;

// 1. ABSTRACTION: Defining the 'contract' for what a Library should do.
// We don't care HOW the books are stored yet, just that they CAN be stored.
interface ILibraryAction {
    void addBook(Book book);
    void showInventory();
}

// 2. ENCAPSULATION: The Base 'Book' Class.
// We hide the internal data (private) and provide controlled access (getters).
abstract class Book {
    private String title;
    private String author;
    private String isbn;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    // Getters: Providing read access to private data
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }

    // This method will be implemented differently by subclasses (Polymorphism)
    public abstract void displayInfo();
}

// 3. INHERITANCE: Creating specialized types of books.
// 'PhysicalBook' and 'EBook' inherit all properties from 'Book'.
class PhysicalBook extends Book {
    private int shelfNumber;

    public PhysicalBook(String title, String author, String isbn, int shelfNumber) {
        super(title, author, isbn);
        this.shelfNumber = shelfNumber;
    }

    @Override
    public void displayInfo() {
        System.out.println("[Physical] Title: " + getTitle() + " | Author: " + getAuthor() +
                " | ISBN: " + getIsbn() + " | Shelf: " + shelfNumber);
    }
}

class EBook extends Book {
    private double fileSizeMB;

    public EBook(String title, String author, String isbn, double fileSizeMB) {
        super(title, author, isbn);
        this.fileSizeMB = fileSizeMB;
    }

    @Override
    public void displayInfo() {
        System.out.println("[E-Book]   Title: " + getTitle() + " | Author: " + getAuthor() +
                " | ISBN: " + getIsbn() + " | Size: " + fileSizeMB + "MB");
    }
}

// 4. THE SYSTEM LOGIC: Managing the collection
class LibraryManager implements ILibraryAction {
    private List<Book> bookList = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        bookList.add(book);
        System.out.println("System: Added '" + book.getTitle() + "' to inventory.");
    }

    @Override
    public void showInventory() {
        System.out.println("\n--- Current Library Inventory ---");
        // 5. POLYMORPHISM: Treating all objects as 'Book',
        // but the specific displayInfo() of each child class is called.
        for (Book b : bookList) {
            b.displayInfo();
        }
        System.out.println("----------------------------------\n");
    }
}

// MAIN CLASS: The Presentation Layer
public class LibrarySystem {
    public static void main(String[] args) {
        LibraryManager myLibrary = new LibraryManager();

        // Adding different types of books
        myLibrary.addBook(new PhysicalBook("Effective Java", "Joshua Bloch", "978-0134685991", 102));
        myLibrary.addBook(new EBook("Clean Code", "Robert C. Martin", "978-0132350884", 4.5));
        myLibrary.addBook(new PhysicalBook("The Pragmatic Programmer", "Andy Hunt", "978-0135957059", 205));

        // Displaying the results
        myLibrary.showInventory();
    }
}