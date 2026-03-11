package demo.transaction;

import demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// Transactional标明所有方法都将以事务的方式执行
@Repository
@Transactional
public interface TransactionUserRepository extends CrudRepository<User, Long> {

    // TODO. 设置事务传播的不同类型
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    User findByEmail(String email);

    // @Transactional(rollbackFor = SQLException.class)
    // User updateByEmail(String email);
}