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

    @Column(length = 500) // Ajustar la longitud máxima
    private String author;

    @Column(name = "download_count")
    private int downloadCount;
}

