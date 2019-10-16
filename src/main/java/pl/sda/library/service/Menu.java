package pl.sda.library.service;


import org.springframework.stereotype.Service;
import pl.sda.library.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

@Service
public class Menu {

    private final OrderService orderService;
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public Menu(OrderService orderService) {
        this.orderService = orderService;
    }

    public void displayMainMenu() {
        StringBuilder builder = new StringBuilder();
        builder
                .append("***************************\n")
                .append("* Welcome in JAVA Library *\n")
                .append("***************************\n")
                .append("1. Borrow book\n")
                .append("2. Return book\n")
                .append("3. Add new book\n")
                .append("4. Remove book\n")
                .append("5. List of all books\n")
                .append("0. Quit\n")
                .append("---------------------------\n")
                .append("Choose option: ");
        System.out.print(builder);
    }

    public String getDecision() {
//        Scanner scanner = new Scanner(System.in);
//        return scanner.nextLine();
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setDecision() {
        switch (getDecision()) {
            case "1":
                System.out.print("Put title to borrow: ");
                String title = getDecision();
                Optional<Book> optionalBook = orderService.borrowBook(title);
                if (optionalBook.isPresent()) {
                    System.out.println(optionalBook.get());
                } else {
                    System.out.println("--> Sorry, Currently we don't have this book in our library");
                }
                break;
            case "2":
                System.out.print("Put idBook to return: ");
                int id = Integer.valueOf(getDecision());
                orderService.returnBook(id);
                break;
            case "3":
                System.out.print("Put title of Book to add: ");
                String titleToAdd = getDecision();
                System.out.print("Put author of Book to add: ");
                String authorToAdd = getDecision();
                orderService.addNewBook(titleToAdd, authorToAdd);
                break;
            case "4":
                System.out.print("Put idBook to remove: ");
                int idToRemove = Integer.valueOf(getDecision());
                orderService.removeBook(idToRemove);
                break;
            case "5":
                System.out.println("===================\nList of all books:\n-------------------");
                orderService.getBooks().forEach(book -> System.out.println(book.toString()));
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("--> this option doesn't exist, try again <--");
        }
    }

}
