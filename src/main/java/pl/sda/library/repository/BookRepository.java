package pl.sda.library.repository;

import org.springframework.stereotype.Repository;
import pl.sda.library.model.Book;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class BookRepository {

//    Set<Book> books = new HashSet<Book>();
//
//    public BookRepository() {
//        books.add(new Book(1, "Jan Brzechwa", "Lokomotywa"));
//        books.add(new Book(2, "Marta Abramowicz", "Zakonnice odchodzą po cichu"));
//        books.add(new Book(3, "Katarzyna Miszczuk", "Szeptucha"));
//        books.add(new Book(4, "Justyna Kopińska", "Polska odwraca oczy"));
//        books.add(new Book(5, "Zośka Papużanka", "On"));
//        books.add(new Book(6, "ks. Jan Kaczkowski", "Życie na pełnej petardzie"));
//        books.add(new Book(7, "Katarzyna Bonda", "Okularnik"));
//        books.add(new Book(8, "Małgorzata Halber", "Najgorszy człowiek na świecie"));
//        books.add(new Book(9, "Łukasz Orbitowski", "Inna dusza"));
//        books.add(new Book(10, "Justyna Wydra", "Esesman i Żydówka"));
//    }

    private Set<Book> books = initialize();

    private Set<Book> initialize() {
        return new HashSet<>(Arrays.asList(
                new Book(1, "Testy", "Kaczanowski"),
                new Book(2, "Dzuma", "Camus"),
                new Book(3, "W pustyni i w puszczy", "Sienkiewicz"),
                new Book(4, "Pan Tadeusz", "Mickiewicz"),
                new Book(5, "Dziady", "Mickiewicz"),
                new Book(6, "Anioly i demony", "Brown"),
                new Book(7, "Gra o tron", "Martin"),
                new Book(8, "Harry Potter", "Rowling"),
                new Book(9, "Testy", "Kaczanowski"),
                new Book(10, "Testy", "Kaczanowski")
        ));
    }

    public Optional<Book> borrowBook(String title, LocalDate borrowedTill) {
        Optional<Book> any = books.stream()
                .filter(book -> title.equals(book.getTitle()))
                .filter(book -> book.getBorrowedTill() == null)
                .findAny();
        any.ifPresent(book -> book.setBorrowedTill(borrowedTill));
        return any;
    }

    public void returnBook(int id) {
        books.stream()
                .filter(book -> id == (book.getId()))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Book not found"))
                .setBorrowedTill(null);
    }

    public Book addNewBook(Book bookToAdd) {
        bookToAdd.setId(generateNextId());
        books.add(bookToAdd);
        return bookToAdd;
    }

    public void removeBook(int id) {
        Book bookToRemove = books.stream().filter(book -> id == book.getId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found"));
        books.remove(bookToRemove);
    }

    public Set<Book> getBooks(){
        return books;
    }
    private int generateNextId() {
//        int max=0;
//        for(Book book: books){
//            if(book.getId()>max){
//                max= book.getId();
//            }
//        }
//        return max+1;
        return books.stream()
                .mapToInt(Book::getId)
                .max().getAsInt() + 1;
    }
}
