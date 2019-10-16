package pl.sda.library.service;

import org.springframework.stereotype.Service;
import pl.sda.library.model.Book;
import pl.sda.library.repository.BookRepository;


import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {

    private final BookRepository bookRepository;

    public OrderService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private LocalDate setBorrowTill() {
        return LocalDate.now().plusDays(30);
    }

    public Optional<Book> borrowBook(String title) {
        return bookRepository.borrowBook(title, setBorrowTill());
    }

    public void returnBook(int bookId) {
        bookRepository.returnBook(bookId);
    }

    public Book addNewBook(String title, String author) {
        return bookRepository.addNewBook(new Book(title,author));
    }

    public void removeBook(int id) {
        bookRepository.removeBook(id);
    }

    public Set<Book> getBooks(){
        return bookRepository.getBooks();
    }
}
