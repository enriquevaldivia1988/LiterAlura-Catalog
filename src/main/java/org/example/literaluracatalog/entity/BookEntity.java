package org.example.literaluracatalog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000) // Ajustar la longitud máxima
    private String title;

    private String language;

    @Column(name = "download_count")
    private int downloadCount;

    private String authorName;
    private int authorBirthYear;
    private int authorDeathYear;
}

