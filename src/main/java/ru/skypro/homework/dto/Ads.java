package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ads {
    //общее количество объявлений
    private int count;
    private Ad[] results;
}
