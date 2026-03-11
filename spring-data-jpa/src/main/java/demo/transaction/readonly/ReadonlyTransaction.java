package demo.transaction.readonly;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ReadonlyTransaction {

    // TODO. readOnly只适用于纯查询的逻辑
    // If transaction is effectively read-only, allowing for corresponding optimizations at runtime.
    @Transactional(readOnly = true)
    public void testTransaction() {
        // jdbcTemplate.execute("select ...");

        System.out.println("Done");
    }

    // TODO. readOnly不适用于传播事务的并行查询
    @Transactional(readOnly = true)
    public void testMultiTransaction() {
        // 根据事务默认传播方式, 并行查询会被加到同一个事务中, 造成串行执行
        // repo1.runTransaction();
        // repo2.runTransaction();

        System.out.println("Done");
    }
}
