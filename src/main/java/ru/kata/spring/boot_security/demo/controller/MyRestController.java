package ru.kata.spring.boot_security.demo.controller;


import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.Users;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public MyRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public  List<Users> getAllUsers() { return userService.listUsers(); }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping("/user")
    public Users getPrincipalUser() {
        Users thisUser = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return thisUser;
    }

    @GetMapping("/users/{id}")
    public Users getUsers(@PathVariable Long id) {
      return userService.getUser(id);
    }

    @PostMapping("/users")
    public Users addNewUser(@RequestBody Users user) {
        userService.add(user);
        return user;
    }

    @PutMapping("/users")
    public Users updateUser(@RequestBody Users user) {
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUserByID(id);
    }
}
