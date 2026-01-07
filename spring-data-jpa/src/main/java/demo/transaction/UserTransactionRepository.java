package demo.transaction;

import demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

// Transactional 标明所有方法都将以事务的方式执行
@Repository
@Transactional
public interface UserTransactionRepository extends CrudRepository<User, Long> {

    // TODO. 设置事务传播的不同类型
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    User findByEmail(String email);

    // TODO. 定义事务在抛出什么异常时进行回滚
    // rollback a transaction for the listed checked exceptions
    @Transactional(rollbackFor = SQLException.class)
    User updateByEmail(String email);

    @Transactional(noRollbackFor = SQLException.class)
    User updateByNameIs(String name);
}