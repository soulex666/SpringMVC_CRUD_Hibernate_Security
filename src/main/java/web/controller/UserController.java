package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private static final String REDIRECT = "redirect:/";

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "index";
    }

    @PostMapping("/adduser")
    public String addUser(@RequestParam(name = "first_name", defaultValue = "---") String firstName,
                          @RequestParam(name = "last_name", defaultValue = "---") String lastName,
                          @RequestParam(name = "age", defaultValue = "0") Byte age) {

        userService.saveUser(firstName, lastName, age);
        return REDIRECT;
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable(name = "id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "details";
    }

    @PostMapping("/saveuser")
    public String saveUser(@RequestParam(name = "id") Long id,
                           @RequestParam(name = "first_name") String firstName,
                          @RequestParam(name = "last_name") String lastName,
                          @RequestParam(name = "age") Byte age) {

        User user = userService.getUserById(id);

        if (user != null) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAge(age);
            userService.update(user);
        }

        return REDIRECT;
    }

    @PostMapping("/deleteuser")
    public String deleteUser(@RequestParam(name = "id") Long id) {

        userService.removeUserById(id);

        return REDIRECT;
    }
}
