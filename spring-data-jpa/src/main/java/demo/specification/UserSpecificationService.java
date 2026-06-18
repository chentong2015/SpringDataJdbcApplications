package demo.specification;

import demo.entity.User;
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

    // 创建关于子查询的Specification, 查询常量
    // where exists {
    //   select 1
    //   from t_users u
    //   where u.relatedId = 'relatedId'
    //   and u.id = 'id'
    // }
    public <T> Specification<T> buildSpecification(String relatedId, String id) {
        return (root, query, criteriaBuilder) -> {
            Subquery<Integer> subquery = query.subquery(Integer.class);
            Root<User> userRoot = subquery.from(User.class);

            subquery.select(criteriaBuilder.literal(1))
                    .where(criteriaBuilder.equal(userRoot.get("relatedId"), relatedId),
                            criteriaBuilder.equal(userRoot.get("id"), id));
            return criteriaBuilder.exists(subquery);
        };
    }
}