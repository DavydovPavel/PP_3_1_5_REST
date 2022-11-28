package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Users;

import java.util.List;

public interface UserDao {

    Users getUser(long id);

    void add(Users user);

    List<Users> listUsers();

    void deleteUserByID(long id);

    void updateUser(Users updateUser);

    Users getName(String nickName);
}
