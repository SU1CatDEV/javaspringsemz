package su1cat.sem4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class GlobalController {
    @Autowired
    public GlobalController() {
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/info")
    public String info(Model model) {
        model.addAttribute("name", "seminar 4");
        model.addAttribute("author", "zoya");
        model.addAttribute("github", "su1catdev");
        model.addAttribute("funfact", "python wasnt named after the snake, but instead after monty python");
        return "info";
    }

    @GetMapping("/create")
    public String createUserForm() {
        return "create-user";
    }

    @PostMapping("/create")
    public String createUser(String firstName, String username, RedirectAttributes redirectAttributes) {
        System.out.println("data received! redirecting..."); // placeholder for actually creating the user
        redirectAttributes.addFlashAttribute("firstName", firstName);
        redirectAttributes.addFlashAttribute("username", username);
        return "redirect:/created";
    }

    @GetMapping("/created")
    public String createdFeedback(Model model) {
        return "created";
    }
//
//    @GetMapping("/user-delete/{userId}")
//    public String deleteUser(@PathVariable(value="userId") Integer id, Model model) {
//        userService.deleteUser(id);
//        model.addAttribute("id", id);
//        return "user-delete";
//    }
//
//    @GetMapping("/user-update/{userId}")
//    public String updateUser(@PathVariable(value="userId") Integer id, Model model) {
//        User editing = userService.findById(id); // because im not about to reinvent the monstrosity which is backend prop drilling. no thats not a thing dont look it up.
//        model.addAttribute("editing", editing);
//        return "user-update";
//    }
//
//    @PostMapping("/user-update")
//    public String updateUser(User user) {
//        userService.updateUser(user);
//        return "redirect:/users";
//    }
//
//    @GetMapping("/user-json/{userId}")
//    public ResponseEntity getById(@PathVariable(value="userId") Integer id, Model model) {
//        User getted = userService.findById(id);
//        return new ResponseEntity(
//                getted,
//                HttpStatus.OK
//        );
//    }
}
