package demo.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionPropagation {

    @Autowired
    private ExtraClass extraClass;

    // TODO. 声明外层方法执行时按照事务的方式执行，且需要注意额外事务的传播
    @Transactional
    public void runOuterMethod() {
        // jdbcTemplate.execute("insert ...");
        extraClass.runExtraMethod();
        System.out.println("Done");
    }

    // TODO. 被调用方法在执行时需要判断是否处于事务运行中，
    //  定义外层事务应该如何被传递到当前事务中
    //  REQUIRED表示外层事务必须有，且当前事务应该被添加到外层事务中
    @Component
    class ExtraClass {

        @Transactional(propagation = Propagation.REQUIRED)
        public void runExtraMethod() {
            System.out.println("Run outer extra method");
            // jdbcTemplate.execute("insert ...");
        }
    }
}
