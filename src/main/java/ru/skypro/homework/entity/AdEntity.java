package ru.skypro.homework.entity;

import lombok.Data;
import ru.skypro.homework.dto.Ad;

import javax.persistence.*;

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
