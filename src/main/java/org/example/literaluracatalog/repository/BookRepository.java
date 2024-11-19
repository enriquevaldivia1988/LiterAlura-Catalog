package org.example.literaluracatalog.repository;

import org.example.literaluracatalog.entity.AuthorEntity;
import org.example.literaluracatalog.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByLanguage(String language);
    Optional<BookEntity> findByTitle(String title);
    long countByLanguage(String language);
}

