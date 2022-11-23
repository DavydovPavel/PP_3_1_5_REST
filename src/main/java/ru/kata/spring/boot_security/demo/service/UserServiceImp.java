package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.models.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserDao userDAO, PasswordEncoder passwordEncoder) {
        this.userDao = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void add(Users user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userDao.add(user);
    }

    @Override
    public List<Users> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public void deleteUserByID(long id) {
        userDao.deleteUserByID(id);
    }

    @Override
    public Users getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    public void updateUserByID(Users user, long id) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userDao.updateUserByID(user, id);
    }

}
