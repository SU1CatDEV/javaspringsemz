package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();

        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-delete/{userId}")
    public String deleteUser(@PathVariable(value="userId") Integer id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("id", id);
        return "user-delete";
    }

    @GetMapping("/user-update/{userId}")
    public String updateUser(@PathVariable(value="userId") Integer id, Model model) {
        User editing = userService.findById(id); // because im not about to reinvent the monstrosity which is backend prop drilling. no thats not a thing dont look it up.
        model.addAttribute("editing", editing);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-json/{userId}")
    public ResponseEntity getById(@PathVariable(value="userId") Integer id, Model model) {
        User getted = userService.findById(id);
        return new ResponseEntity(
                getted,
                HttpStatus.OK
        );
    }
}
