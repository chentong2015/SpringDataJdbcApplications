package demo.controller;

import demo.entity.User;
import demo.service.UserSpecificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserSpecController {

    private final UserSpecificationService userSpecService;

    public UserSpecController(UserSpecificationService userSpecService) {
        this.userSpecService = userSpecService;
    }

    // http://localhost:8080/user/creation?email=[email]&name=[name]
    @PostMapping("/user-spec/creation")
    public String create(@RequestParam("email") String email, @RequestParam("name") String name) {
        User user = new User(email, name);
        User userSaved = this.userSpecService.save(user);
        return "Create user with id = " + userSaved.getId();
    }

    @GetMapping("/user-spec/{name}")
    public List<User> findByNameLike(@PathVariable("name") String name) {
        return this.userSpecService.getUsersByNameLike(name);
    }
}
