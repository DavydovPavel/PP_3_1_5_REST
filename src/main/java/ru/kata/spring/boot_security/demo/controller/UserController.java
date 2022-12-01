package ru.kata.spring.boot_security.demo.controller;

import ru.kata.spring.boot_security.demo.models.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    @GetMapping("/userPage")
    public String showUser(Model model) {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "userPanel";
    }

    @GetMapping("/index")
    public String index(Model model) {
        Users thisUser = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("thisUser", thisUser);
        return "index";
    }
}

