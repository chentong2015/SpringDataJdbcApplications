package org.example.transaction_fail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

public class ConcurrencyEmTransactionKO {

    @PersistenceContext
    private EntityManager entityManager;

    // TODO. 数据更新必须放在事务中 => 不同线程的更新必须具有不同独立的事务 !!
    // TransactionRequiredException: "Executing an update/delete query"
    @Transactional
    public int deleteUserByName(String username) {
        String deleteQuery = "delete from t_users where name = :username";

        return entityManager.createNativeQuery(deleteQuery)
                .setParameter("username", username)
                .executeUpdate();
    }
}
