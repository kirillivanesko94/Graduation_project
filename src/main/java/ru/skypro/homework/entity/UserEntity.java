package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserEntity {
    @Id
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String image;
}
