package demo.transaction;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionBase {

    // TODO. 只有抛出特定异常时才回滚
    @Transactional(rollbackFor = NullPointerException.class)
    public void testException() {
        // jdbcTemplate.execute("insert ...");
        throw new NullPointerException();
    }

    // TODO. 并发时不同线程使用独立事务
    public void testTransactionConcurrency() {
        // 去掉外层事务的封装，才能执行并发查询(相互独立)
        // thread 1: repo1.selectARecords();
        // thread 2: repo2.selectBRecords();
        // thread 3: repo3.selectCRecords();
        System.out.println("Done");
    }
}
