package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AdEntity {
    @OneToOne
    private ImageEntity image;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    private int price;
    private String title;
    @ManyToOne
    @JoinColumn (name = "id")
    private UserEntity user;
    private String description;



}
