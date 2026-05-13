package demo.transaction.template;

public interface CustomTransactionListener {

    void beforeTransaction();

    void afterTransaction();
}
