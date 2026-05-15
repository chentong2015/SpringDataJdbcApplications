package demo.controller;

import demo.entity.User;
import demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{email}")
    public User findByName(@PathVariable("email") String email) {
        System.out.println(email);
        return this.userService.findUserByEmail(email);
    }
}
