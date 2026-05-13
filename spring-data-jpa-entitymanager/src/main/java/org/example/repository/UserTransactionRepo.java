package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 利用Spring框架事务和EntityManager来执行数据更新
@Repository
public class UserTransactionRepo {

    @PersistenceContext
    private EntityManager entityManager;

    // TODO. 新建单独的事务来删除数据，小批量提交和回滚
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int cleanUserRecordsNames(List<String> names) {
        String deleteQuery = "delete from t_users where name in (:names)";
        return entityManager.createNativeQuery(deleteQuery)
                .setParameter("names", names)
                .executeUpdate();
    }
}
