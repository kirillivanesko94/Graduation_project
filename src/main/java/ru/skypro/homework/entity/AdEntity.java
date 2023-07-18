package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class AdEntity {
    @OneToOne
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
