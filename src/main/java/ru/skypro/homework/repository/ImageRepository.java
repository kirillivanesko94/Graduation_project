package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.ImageEntity;

/**
 * Repository interface for accessing ImageEntity data from the database.
 */
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
}
