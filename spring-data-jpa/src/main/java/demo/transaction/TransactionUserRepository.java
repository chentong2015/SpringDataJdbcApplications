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

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    User findByEmail(String email);

    // 默认基于RuntimeException异常来回滚
    @Transactional(rollbackFor = RuntimeException.class)
    User deleteUserByEmail(String email);
}