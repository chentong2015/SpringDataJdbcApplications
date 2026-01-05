package demo.controller;

import demo.entity.User;
import demo.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserCrudRepository userRepository;

    @Autowired
    public UserController(UserCrudRepository userRepository) {
        this.userRepository = userRepository;
    }

    // http://localhost:8080/user/create?email=[email]&name=[name]
    @RequestMapping("/user/create")
    public String create(String email, String name) {
        String userId = "";
        try {
            User user = new User(email, name);
            userRepository.save(user);
            userId = String.valueOf(user.getId());
        } catch (Exception ex) {
            return "Error" + ex.getMessage();
        }
        return "Create user with id = " + userId;
    }
}
