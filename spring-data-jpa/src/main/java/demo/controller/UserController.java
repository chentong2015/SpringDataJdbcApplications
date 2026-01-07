package demo.controller;

import demo.entity.User;
import demo.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // http://localhost:8080/user/create?email=[email]&name=[name]
    @RequestMapping("/user/create")
    public String create(String email, String name) {
        String userId = "";
        try {
            User user = new User(email, name);
            this.userService.save(user);
            userId = String.valueOf(user.getId());
        } catch (Exception ex) {
            return "Error" + ex.getMessage();
        }
        return "Create user with id = " + userId;
    }
}
