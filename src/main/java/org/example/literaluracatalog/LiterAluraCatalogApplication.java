package org.example.literaluracatalog;

import org.example.literaluracatalog.entity.BookEntity;
import org.example.literaluracatalog.model.Book;
import org.example.literaluracatalog.service.GutendexService;
import org.example.literaluracatalog.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiterAluraCatalogApplication implements CommandLineRunner {

    @Autowired
    private GutendexService gutendexService;

    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraCatalogApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showMenu();
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter book title to search: ");
                    String title = scanner.nextLine();
                    List<Book> books = gutendexService.getBooks(title);
                    if (books.isEmpty()) {
                        System.out.println("No books found.");
                    } else {
                        books.forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthor()));
                    }
                    break;
                case 2:
                    List<BookEntity> storedBooks = bookRepository.findAll();
                    if (storedBooks.isEmpty()) {
                        System.out.println("No stored books found.");
                    } else {
                        storedBooks.forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthor()));
                    }
                    break;
                case 3:
                    System.out.print("Enter language to filter books: ");
                    String language = scanner.nextLine();
                    List<BookEntity> booksByLanguage = bookRepository.findByLanguage(language);
                    if (booksByLanguage.isEmpty()) {
                        System.out.println("No books found for the specified language.");
                    } else {
                        booksByLanguage.forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthor()));
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu:");
        System.out.println("1. Search book by title");
        System.out.println("2. List all stored books");
        System.out.println("3. List books by language");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }
}
