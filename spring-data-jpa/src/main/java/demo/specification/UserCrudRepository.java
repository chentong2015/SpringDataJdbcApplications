package demo.specification;

import demo.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCrudRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

    // 自动解析成对应Query来执行
    User findByEmail(String email);

    // 提供特殊查询条件Specification来查询结果
    List<User> findAll(Specification<User> spec);

    // Modifying注解针对数据更待，标注后该查询语句将被优化
    @Modifying(clearAutomatically = true)
    void deleteUserByEmail(String email);

    // Modifying自定义执行修改的SQL，设置具名参数名称
    @Modifying
    @Query(value = "update t_users u set u.name = :name where u.email like :e1%", nativeQuery = true)
    void updateUserNameByEmail(@Param("name") String newName,
                               @Param("e1") String email);
}
