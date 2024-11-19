package org.example.literaluracatalog.service;

import org.example.literaluracatalog.entity.BookEntity;
import org.example.literaluracatalog.model.Book;
import org.example.literaluracatalog.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;
    private final BookRepository bookRepository;

    public GutendexService(BookRepository bookRepository) {
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
        // Consumir la API de Gutendex
        String url = "https://gutendex.com/books/?search=" + query;
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        if (response == null || response.getResults() == null) {
            return List.of(); // Retorna lista vacía si no hay resultados
        }

        List<Book> booksFromApi = response.getResults();

        // Guardar los datos en la base de datos
        List<BookEntity> entities = booksFromApi.stream().map(book -> {
            BookEntity entity = new BookEntity();
            entity.setTitle(book.getTitle());
            entity.setAuthor(book.getAuthor() != null ? book.getAuthor() : "Desconocido");
            entity.setDownloadCount(book.getDownloadCount());
            return entity;
        }).collect(Collectors.toList());

        bookRepository.saveAll(entities);

        return booksFromApi;
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
