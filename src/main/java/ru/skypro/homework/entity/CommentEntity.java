package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity class for comment
 *
 * @autor Kirill
 */
@Entity
@Data
public class CommentEntity {
    /**
     * Field primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    /**
     * Field user
     */
    @ManyToOne()
    @JoinColumn (name = "id")
    private UserEntity user;
    /**
     * Field date of comment creation
     */
    private LocalDateTime createdAt;
    /**
     * Field text of comment
     */
    private String text;
    /**
     * Field advertisement
     */
    @ManyToOne
    @JoinColumn (name = "adEntity.pk")
    private AdEntity adEntity;
}
