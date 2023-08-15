package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

public interface AuthService {
    /**
     * @param userName - user's login (email)
     * @param password - user's password
     */
    boolean login(String userName, String password);
    /**
     * @param register - full user information
     */

    boolean register(Register register);
}
