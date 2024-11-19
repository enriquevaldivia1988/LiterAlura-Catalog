package org.example.literaluracatalog.controller;
import org.example.literaluracatalog.entity.BookEntity;
import org.example.literaluracatalog.model.Book;
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

    public GutendexController(GutendexService gutendexService, BookRepository bookRepository) {
        this.gutendexService = gutendexService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public List<Book> searchBooks(@RequestParam String query) {
        return gutendexService.getBooks(query);
    }

    @GetMapping("/books/stored")
    public List<BookEntity> getStoredBooks() {
        return bookRepository.findAll();
    }

}
