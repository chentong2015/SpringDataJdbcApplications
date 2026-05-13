package demo.transaction.template;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

public class TransactionTemplateHandler {

    private final TransactionTemplate transactionTemplate;

    public TransactionTemplateHandler(DataSource dataSource) {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager(dataSource);
        transactionTemplate = new TransactionTemplate(txManager);
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    }

    // 自定义编程式事务的提交
    public void executeWithListener(TransactionCallbackWithoutResult callback, CustomTransactionListener listener) {
        if (listener != null) {
            listener.beforeTransaction();
        }
        transactionTemplate.execute(callback);
        if (listener != null) {
            listener.afterTransaction();
        }
    }
}

