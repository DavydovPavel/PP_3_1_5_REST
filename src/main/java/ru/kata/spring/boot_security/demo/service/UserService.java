package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Users;

import java.util.List;

public interface UserService {
    void add(Users user);

    List<Users> listUsers();

    void deleteUserByID(long id);

    Users getUser(long id);

    void updateUserByID(Users user, long id);
}
