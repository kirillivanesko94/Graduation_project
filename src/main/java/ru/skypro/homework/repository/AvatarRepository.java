package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AvatarEntity;

/**
 * Repository interface for accessing AvatarEntity data from the database.
 */
public interface AvatarRepository extends JpaRepository<AvatarEntity, Integer> {
}
