package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleDao {
    Role getRole(long id);

    void addRole(Role role);

    void deleteRoleByID(long id);

    List<Role> getRoles();
    Role getName(String name);
}
