package demo.service;

import demo.entity.User;
import demo.specification.UserCrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserCrudRepository userCrudRepository;

    public UserService(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    public User findUserByEmail(String email) {
        return this.userCrudRepository.findByEmail(email);
    }
}
