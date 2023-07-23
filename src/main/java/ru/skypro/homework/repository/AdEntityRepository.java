package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AdEntity;

import java.util.List;

public interface AdEntityRepository extends JpaRepository<AdEntity, Integer> {
    List<AdEntity> findAllByUserId(int id);
}
