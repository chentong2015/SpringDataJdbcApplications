package demo.repositories;

import demo.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// JpaSpecification 提供特定的Specification条件查询特征
@Repository
public interface UserCrudRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

    // 默认的查询与特定的方法名称一致
    User findByEmail(String email);

    // 提供特殊的匹配判断条件Specification来进行查询
    List<User> findAll(Specification<User> spec);

    // Spring Data JPA 将优化标注Modifying注解的查询
    @Modifying(clearAutomatically = true)
    void deleteUserByEmail(String email);

    @Modifying
    @Query(value = "update t_users u set u.name = :name where u.email like :e1%", nativeQuery = true)
    void updateUserNameByEmail(@Param("name") String newName, @Param("e1") String email);
}
