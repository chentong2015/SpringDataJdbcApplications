package demo.transaction;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionPropagation {

    private final ExtraClass extraClass;

    public TransactionPropagation(ExtraClass extraClass) {
        this.extraClass = extraClass;
    }

    // TODO. 定义外层方法以事务的方式执行
    @Transactional
    public void runOuterMethod() {
        extraClass.runExtraMethod();
        System.out.println("Done");
    }

    @Component
    class ExtraClass {

        // TODO. 事务的传播设置: 定义外层事务应该如何被传递到当前事务中
        // REQUIRED表示外层事务必须有，且当前事务应该被添加到外层事务中
        @Transactional(propagation = Propagation.REQUIRED)
        public void runExtraMethod() {
            System.out.println("Run outer extra method");
            // jdbcTemplate.execute("insert ...");
        }
    }
}
