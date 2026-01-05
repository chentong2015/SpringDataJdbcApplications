package demo.services;

import demo.entity.User;
import demo.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserCrudRepository userRepository;

    @Autowired
    public UserService(UserCrudRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    // TODO. 自定义定义Specification/Predicate来实现复杂的条件查询
    public List<User> getUsersByNameLike(String likeName) {
        Specification<User> specification = (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + likeName + "%");

        return userRepository.findAll(specification);
    }
}
