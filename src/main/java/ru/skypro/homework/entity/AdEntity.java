package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class AdEntity {
    @OneToOne
    @JoinColumn(name = "ad")
    private ImageEntity image;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;
    private Integer price;
    private String title;
    @ManyToOne
    @JoinColumn (name = "id")
    private UserEntity user;
    private String description;
    @OneToMany
    private List<CommentEntity> commentEntities;



}
