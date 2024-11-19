package org.example.literaluracatalog.controller;
import org.example.literaluracatalog.entity.AuthorEntity;
import org.example.literaluracatalog.entity.BookEntity;
import org.example.literaluracatalog.model.Book;
import org.example.literaluracatalog.repository.AuthorRepository;
import org.example.literaluracatalog.repository.BookRepository;
import org.example.literaluracatalog.service.GutendexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GutendexController {

    private final GutendexService gutendexService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public GutendexController(GutendexService gutendexService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.gutendexService = gutendexService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/books")
    public List<Book> searchBooks(@RequestParam String query) {
        return gutendexService.getBooks(query);
    }

    @GetMapping("/books/stored")
    public List<BookEntity> getStoredBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/language")
    public List<BookEntity> getBooksByLanguage(@RequestParam String language) {
        return bookRepository.findByLanguage(language);
    }

    @GetMapping("/authors")
    public List<AuthorEntity> getAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/authors/alive")
    public List<AuthorEntity> getAuthorsAliveInYear(@RequestParam int year) {
        return authorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year, year);
    }
}
