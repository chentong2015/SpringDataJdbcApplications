package demo.transaction;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionConcurrency {

    // TODO. 根据事务默认传播方式，传递事务会被追加到外层统一事务中，导致传递事务不可能被并发执行
    @Transactional
    public void testTransactionConcurrency() {
        // 去掉外层事务的封装，才能执行并发查询(相互独立)
        // repo1.selectRecords();
        // repo2.selectRecords();
        // repo3.selectRecords();
        // repo4.selectRecords();

        System.out.println("Done");
    }
}