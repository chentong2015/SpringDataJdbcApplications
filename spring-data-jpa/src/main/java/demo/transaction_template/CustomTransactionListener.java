package demo.transaction_template;

public interface CustomTransactionListener {

    void beforeTransaction();

    void afterTransaction();
}
