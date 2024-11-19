package org.example.literaluracatalog.service;

import org.example.literaluracatalog.entity.AuthorEntity;
import org.example.literaluracatalog.entity.BookEntity;
import org.example.literaluracatalog.model.Book;
import org.example.literaluracatalog.repository.AuthorRepository;
import org.example.literaluracatalog.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public GutendexService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.restTemplate = new RestTemplate();
        this.bookRepository = bookRepository;
    }

    /**
     * Obtiene libros desde la API de Gutendex y los guarda en la base de datos.
     *
     * @param query La consulta de búsqueda.
     * @return Una lista de libros desde la API.
     */
    public List<Book> getBooks(String query) {
        String url = "https://gutendex.com/books/?search=" + query;
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        if (response == null || response.getResults() == null) {
            return List.of();
        }

        List<Book> booksFromApi = response.getResults();

        List<BookEntity> bookEntities = booksFromApi.stream().map(book -> {
            BookEntity bookEntity = bookRepository.findByTitle(book.getTitle())
                    .orElse(new BookEntity());
            bookEntity.setTitle(book.getTitle());
            bookEntity.setLanguage(book.getLanguage());
            bookEntity.setDownloadCount(book.getDownloadCount());
            bookEntity.setAuthorName(book.getAuthorName());
            bookEntity.setAuthorBirthYear(book.getAuthorBirthYear());
            bookEntity.setAuthorDeathYear(book.getAuthorDeathYear());
            return bookEntity;
        }).collect(Collectors.toList());

        bookRepository.saveAll(bookEntities);

        List<AuthorEntity> authorEntities = booksFromApi.stream().map(book -> {
            AuthorEntity authorEntity = authorRepository.findByName(book.getAuthorName())
                    .orElse(new AuthorEntity());
            authorEntity.setName(book.getAuthorName());
            authorEntity.setBirthYear(book.getAuthorBirthYear());
            authorEntity.setDeathYear(book.getAuthorDeathYear());
            return authorEntity;
        }).collect(Collectors.toList());

        authorRepository.saveAll(authorEntities);

        return booksFromApi;
    }

    public long getBookCountByLanguage(String language) {
        return bookRepository.countByLanguage(language);
    }

    public List<AuthorEntity> getAuthorsAliveInYear(int year) {
        return authorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year, year);
    }

    /**
     * Clase interna para mapear la respuesta de Gutendex.
     */
    private static class GutendexResponse {
        private List<Book> results;

        public List<Book> getResults() {
            return results;
        }

        public void setResults(List<Book> results) {
            this.results = results;
        }
    }
}
