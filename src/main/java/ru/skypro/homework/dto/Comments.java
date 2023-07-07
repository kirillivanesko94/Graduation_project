package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Comments {
    //общее количество комментариев
    private int count;
    private Comment[] results;
}
