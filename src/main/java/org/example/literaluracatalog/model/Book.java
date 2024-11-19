package org.example.literaluracatalog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Book {
    private int id;
    private String title;
    private String author;
    private String language;

    @JsonProperty("download_count")
    private int downloadCount;

    @JsonProperty("authors")
    private void unpackAuthors(List<Author> authors) {
        if (authors != null && !authors.isEmpty()) {
            this.author = authors.get(0).getName();
        } else {
            this.author = "Unknown";
        }
    }

    @JsonProperty("languages")
    private void unpackLanguages(List<String> languages) {
        if (languages != null && !languages.isEmpty()) {
            this.language = languages.get(0);
        } else {
            this.language = "Unknown";
        }
    }

    @Data
    private static class Author {
        private String name;
    }
}
