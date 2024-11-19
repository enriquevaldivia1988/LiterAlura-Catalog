package org.example.literaluracatalog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Book {
    private int id;
    private String title;
    private String language;
    private int downloadCount;
    private String authorName;
    private int authorBirthYear;
    private int authorDeathYear;

    @JsonProperty("download_count")
    private void unpackDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    @JsonProperty("authors")
    private void unpackAuthors(List<Author> authors) {
        if (authors != null && !authors.isEmpty()) {
            Author firstAuthor = authors.get(0);
            this.authorName = firstAuthor.getName();
            this.authorBirthYear = firstAuthor.getBirthYear();
            this.authorDeathYear = firstAuthor.getDeathYear();
        } else {
            this.authorName = "Unknown";
            this.authorBirthYear = 0;
            this.authorDeathYear = 0;
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
        @JsonProperty("birth_year")
        private int birthYear;
        @JsonProperty("death_year")
        private int deathYear;
    }
}
