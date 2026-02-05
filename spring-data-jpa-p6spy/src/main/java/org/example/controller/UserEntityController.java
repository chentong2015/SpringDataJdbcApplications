package org.example.controller;

import org.example.model.UserEntity;
import org.example.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserEntityController {

    private final UserRepository userRepository;

    public UserEntityController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/creation")
    public Long save() {
        UserEntity userEntity = new UserEntity("test email", "test new user");
        return this.userRepository.save(userEntity).getId();
    }

    @GetMapping("/all")
    public List<UserEntity> getAll() {
        return this.userRepository.findAllByIdAfter(400L);
    }
}
