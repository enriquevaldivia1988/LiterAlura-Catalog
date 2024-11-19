package org.example.literaluracatalog;

import org.example.literaluracatalog.entity.AuthorEntity;
import org.example.literaluracatalog.entity.BookEntity;
import org.example.literaluracatalog.model.Book;
import org.example.literaluracatalog.service.GutendexService;
import org.example.literaluracatalog.repository.AuthorRepository;
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

    @Autowired
    private AuthorRepository authorRepository;

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
                        books.forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthorName()));
                    }
                    break;
                case 2:
                    List<BookEntity> storedBooks = bookRepository.findAll();
                    if (storedBooks.isEmpty()) {
                        System.out.println("No stored books found.");
                    } else {
                        storedBooks.forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthorName()));
                    }
                    break;
                case 3:
                    System.out.print("Enter language to filter books: ");
                    String language = scanner.nextLine();
                    List<BookEntity> booksByLanguage = bookRepository.findByLanguage(language);
                    if (booksByLanguage.isEmpty()) {
                        System.out.println("No books found for the specified language.");
                    } else {
                        booksByLanguage.forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthorName()));
                    }
                    break;
                case 4:
                    List<AuthorEntity> authors = authorRepository.findAll();
                    if (authors.isEmpty()) {
                        System.out.println("No authors found.");
                    } else {
                        authors.forEach(author -> System.out.println(author.getName() + " (Born: " + author.getBirthYear() + ", Died: " + author.getDeathYear() + ")"));
                    }
                    break;
                case 5:
                    System.out.print("Enter year to find authors alive in that year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    List<AuthorEntity> authorsAlive = gutendexService.getAuthorsAliveInYear(year);
                    if (authorsAlive.isEmpty()) {
                        System.out.println("No authors found alive in the specified year.");
                    } else {
                        authorsAlive.forEach(author -> System.out.println(author.getName() + " (Born: " + author.getBirthYear() + ", Died: " + author.getDeathYear() + ")"));
                    }
                    break;
                case 6:
                    System.out.print("Enter language to count books: ");
                    String countLanguage = scanner.nextLine();
                    long bookCount = gutendexService.getBookCountByLanguage(countLanguage);
                    System.out.println("Number of books in " + countLanguage + ": " + bookCount);
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
        System.out.println("4. List all authors");
        System.out.println("5. List authors alive in a specific year");
        System.out.println("6. Count books by language");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }
}
