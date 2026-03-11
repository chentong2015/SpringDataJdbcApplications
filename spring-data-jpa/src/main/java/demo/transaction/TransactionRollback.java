package demo.transaction;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionRollback {

    // TODO. 设置以事务方式执行: 当抛出特定异常时自动回滚
    @Transactional(rollbackFor = NullPointerException.class)
    public void testException() {
        // jdbcTemplate.execute("insert ...");
        throw new NullPointerException();
    }
}
