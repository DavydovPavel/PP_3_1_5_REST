package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.kata.spring.boot_security.demo.models.Users;
import ru.kata.spring.boot_security.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/users")
    public String listUsers(Model model) {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getRoles());
        return "allUsers";
    }

    @GetMapping(value = "/admin/users/add")
    public String addUser(@ModelAttribute("user") Users user, Model model) {
        model.addAttribute("roles", roleService.getRoles());
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users")
    public String addUser(@ModelAttribute("user") Users user,
                          @RequestParam(value = "index", required = false) Long[] identifiers) {
        if (identifiers != null) {
            for (Long roleId : identifiers) {
                user.addRole(roleService.getRole(roleId));
            }
        } else {
            user.addRole(roleService.getName());
        }
        userService.add(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("user", userService.getUser(id));
        return "/edit";
    }

    @PostMapping("admin/users/{id}")
    public String updateUser(@ModelAttribute("user") Users user,
                             @RequestParam(value = "index", required = false) Long[] identifiers) {
        if (identifiers != null) {
            for (Long roleId : identifiers) {
                user.addRole(roleService.getRole(roleId));
            }
        } else {
            user.addRole(roleService.getName());
        }
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUserByID(id);
        return "redirect:/admin/users";
    }
}