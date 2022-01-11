package controllers;

import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;

public class TransactionController {
    static EntityManagerFactory entityManagerFactory;
    static {entityManagerFactory = Persistence.createEntityManagerFactory("my-pu")};
    static UserRepository userRepository= new UserRepository(entityManagerFactory);
    public static Handler processTransaction = ctx -> {
        Transaction transaction = ctx.bodyAsClass(Transaction.class);
        TransactionRepository.insert(transaction);
    }
}
