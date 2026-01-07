package demo.repositories;

import demo.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// JpaSpecification 提供特定的Specification条件查询特征
@Repository
public interface UserCrudRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {

    // Spring Data JPA 自动为接口方法生成Proxy Impl实现查询逻辑
    User findByEmail(String email);

    // Spring Data JPA 将优化标注Modifying注解的查询
    @Modifying(clearAutomatically = true)
    void deleteUserByEmail(String email);

    // 自定义Native Query的原生查询
    @Modifying
    @Query(value = "update t_users u set u.name = :name where u.email like :e1%", nativeQuery = true)
    void updateUserNameByEmail(@Param("name") String newName, @Param("e1") String email);
}
