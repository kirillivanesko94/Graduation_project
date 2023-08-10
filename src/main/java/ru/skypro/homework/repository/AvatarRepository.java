package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.AvatarEntity;

public interface AvatarRepository extends JpaRepository<AvatarEntity, Integer> {
}
