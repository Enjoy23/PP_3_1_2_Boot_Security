package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "usersIndex";
    }

    @GetMapping(value = "/new")
    public String createNewUser(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("roles", roleService.getRoles());
        return "new";
    }

    @PostMapping
    public String addNewUser(@ModelAttribute("user") User user){
       userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getRoles());
        System.out.println("Юзер найденный в гет: "+userService.getUserById(id));
        return "edit_user";
    }

    @PutMapping("/{id}/update")
    public String updateUserInfo(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        System.out.println("Юзер переданный в пост: "+user);
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
