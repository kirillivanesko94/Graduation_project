package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    @ManyToOne()
    @JoinColumn (name = "id")
    private UserEntity user;
    private LocalDateTime createAt;
    private String text;
    @ManyToOne
    @JoinColumn (name = "adEntity.pk")
    private AdEntity adEntity;
}
