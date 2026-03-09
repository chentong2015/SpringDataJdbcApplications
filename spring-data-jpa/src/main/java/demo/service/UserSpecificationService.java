package demo.service;

import demo.entity.User;
import demo.repository.UserCrudRepository;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSpecificationService {

    private final UserCrudRepository userRepository;

    @Autowired
    public UserSpecificationService(UserCrudRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    // TODO. 自定义Predicate作为查询的where判断条件
    // Select *
    // from t_users
    // where name like %name%
    // and id < 10
    // and email in (
    //    select email
    //    from t_users
    //    where email like %default%@gmail.com
    // )
    public List<User> getUsersByNameLike(String name) {
        Specification<User> specification = (root, query, criteriaBuilder) -> {
            Predicate likeName = criteriaBuilder.like(root.get("name"), "%" + name + "%");
            Predicate lessThan = criteriaBuilder.lessThan(root.get("id"), 10);

            Subquery<String> subquery = query.subquery(String.class);
            Root<User> rootInternal = subquery.from(User.class);
            subquery.select(rootInternal.get("email"));
            subquery.where(criteriaBuilder.like(root.get("email"), "%default%@gmail.com"));

            Predicate notDefaultEmail = criteriaBuilder.isTrue(root.get("email").in(subquery));

            // 整合多个Predicate判断条件的逻辑
            return criteriaBuilder.and(lessThan, likeName, notDefaultEmail);
        };
        return userRepository.findAll(specification);
    }
}
