package org.example.literaluracatalog.repository;

import org.example.literaluracatalog.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    List<AuthorEntity> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(int year, int year2);
    Optional<AuthorEntity> findByName(String name);
}
