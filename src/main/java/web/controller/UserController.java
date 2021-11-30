package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.Model.User;
import web.Service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showUsers(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsersPage", allUsers);
        return "all_users";
    }

    @GetMapping("/add-new-user")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user_info";
    }

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

//    @GetMapping("/update-user")
//    public String updateUser(@RequestParam("userId") int id, Model model) {
//        User user = userService.getUser(id);
//        model.addAttribute("user", user);
//        return "user_info";
//    }

    @GetMapping("/update-user/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "update_user";
    }
    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return "redirect:/";
    }
}
