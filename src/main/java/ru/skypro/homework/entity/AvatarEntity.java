package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * Entity class for user's avatar
 *
 * @autor Kirill
 */
@Entity
@Data
public class AvatarEntity {
    /**
     * Field id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Field user
     */
    @OneToOne(mappedBy = "avatarEntity")
    @JoinColumn(name = "user_id")
    private UserEntity user;
    /**
     * Field data
     */
    private byte[] data;
    /**
     * Field name of file
     */
    private String fileName;
    /**
     * Field type of media
     */
    private String mediaType;
}
