package org.example.transaction_fail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO. 不要自定义事务的提交过程，容易造成异常且无法控制
@Service
public class CustomEmTransactionKO {

    private final EntityManager entityManager;

    public CustomEmTransactionKO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // TODO. 不能对共享的EntityManager(被Spring容器管理)手动开启事务
    // Not allowed to create transaction on shared EntityManager
    private long executeUpdate(List<Long> recordIds) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            long count = entityManager.createQuery("delete from query").executeUpdate();
            transaction.commit();
            entityManager.flush();
            return count;
        } catch (Exception exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }
    }
}
