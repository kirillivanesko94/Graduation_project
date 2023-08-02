package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;

public interface CommentEntityRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findAllByAdEntity_Pk(Integer adEntity_pk);
}
