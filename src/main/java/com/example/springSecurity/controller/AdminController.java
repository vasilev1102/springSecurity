package com.example.springSecurity.controller;

import com.example.springSecurity.service.UserService;
import org.springframework.validation.FieldError;
import com.example.springSecurity.model.User;
import com.example.springSecurity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("admin")
    public String pageForAdmin(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("admin/new")
    public String pageCreateUser(User user, Model model) {
        model.addAttribute("listRoles",roleService.findAllRole());
        return "create";
    }

    @PostMapping("admin/new")
    public String pageCreate(@RequestParam("role")ArrayList<Long> roles,
                             @ModelAttribute("user") User user) {
        user.setRoles(roleService.findByIdRoles(roles));
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("admin/delete/{id}")
    public String pageDelete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("admin/edit/{id}")
    public String pageEditUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user",userService.getById(id));
        model.addAttribute("listRoles",roleService.findAllRole());
        return "edit";
    }

    @PutMapping("admin/edit")
    public String pageEdit(@RequestParam("role")ArrayList<Long> roles, User user) {
            user.setRoles(roleService.findByIdRoles(roles));
            userService.save(user);
            return "redirect:/admin";

    }
}