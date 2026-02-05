package demo.controller;

import demo.entity.User;
import demo.services.UserSpecificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserSpecificationService userService;

    public UserController(UserSpecificationService userService) {
        this.userService = userService;
    }

    // http://localhost:8080/user/creation?email=[email]&name=[name]
    @PostMapping("/user/creation")
    public String create(@RequestParam("email") String email, @RequestParam("name") String name) {
        User user = new User(email, name);
        this.userService.save(user);
        return "Create user with id = " + user.getId();
    }

    @GetMapping("/user/{name}")
    public List<User> findByNameLike(@PathVariable("name") String name) {
        return this.userService.getUsersByNameLike(name);
    }
}
