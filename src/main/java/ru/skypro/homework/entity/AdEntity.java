package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class AdEntity {
    private int author;
    private String image;
    @Id
    @GeneratedValue
    private int pk;
    private int price;
    private String title;
}
