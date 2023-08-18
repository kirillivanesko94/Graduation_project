package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;

/**
 * Repository interface for accessing CommentEntity data from the database.
 */
public interface CommentEntityRepository extends JpaRepository<CommentEntity, Integer> {
    /**
     * Retrieves a list of comments based on the primary key of the associated AdEntity.
     *
     * @param adEntity_pk The primary key of the associated AdEntity.
     * @return A list of comments associated with the given AdEntity primary key.
     */
    List<CommentEntity> findAllByAdEntity_Pk(Integer adEntity_pk);
}
