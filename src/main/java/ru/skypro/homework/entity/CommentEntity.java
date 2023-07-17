package ru.skypro.homework.entity;

import lombok.Data;
import ru.skypro.homework.dto.User;

import javax.persistence.*;

@Entity
@Data
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    @ManyToOne()
    @JoinColumn (name = "id")
    private UserEntity user;
    private String authorImage;
    private String authorFirstName;
    private Long createAt;
    private String text;
}
