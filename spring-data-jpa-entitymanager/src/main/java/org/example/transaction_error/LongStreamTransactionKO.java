package org.example.transaction_error;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// TODO. 超长Stream流式数据查询: 避免将数据全部加载到内存
// 1. 必须保证查询的整个期间Connection Open以便流式获取 -> 使用@Transactional事务包裹
// 2. 超长Stream流式数据会造成超长事务 -> 导致异常或无法回滚[ORA-01555: snapshot too old: rollback]
public class LongStreamTransactionKO {

    private static final int BATCH_DELETE_SIZE = 1000;
    private final LongStreamTransactionRepo transactionRepo;

    public LongStreamTransactionKO(LongStreamTransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    // TODO. Streaming + 边读边删 + ResultSet 长时间不关闭
    // 流式查询百万级ID数据, 超长耗时删除必然导致事务失效
    @Transactional
    public void executeLongStreamUpdate(List<String> names) {
        try (Stream<Long> idStream = this.transactionRepo.findUserIdByEntityNames(names)) {
            List<Long> batchIds = new ArrayList<>(BATCH_DELETE_SIZE);
            idStream.forEach(id -> {
                batchIds.add(id);
                if (batchIds.size() == BATCH_DELETE_SIZE) {
                    // Execute batch delete query 超长耗时操作
                    batchIds.clear();
                }
            });
            if (!batchIds.isEmpty()) {
                // Execute batch delete query
            }
        }
    }
}
