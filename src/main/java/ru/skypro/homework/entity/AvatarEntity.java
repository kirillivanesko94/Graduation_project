package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Data
public class AvatarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(mappedBy = "avatarEntity")
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private byte[] data;
    private String fileName;
    private String mediaType;
}
