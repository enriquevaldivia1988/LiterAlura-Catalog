package org.example.literaluracatalog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Book {
    private int id;
    private String title;
    private String author;

    @JsonProperty("download_count")
    private int downloadCount;
}

