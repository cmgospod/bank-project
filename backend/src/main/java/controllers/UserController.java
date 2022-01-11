package controllers;

import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;


public class UserController {
    static EntityManagerFactory entityManagerFactory;
    static User currentUser;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("my-pu");
    }

    //-------------------------------------------------
    static UserRepository userRepository= new UserRepository(entityManagerFactory);
    public static Handler createNewUser = ctx -> {
        User user = ctx.bodyAsClass(User.class);
        UserRepository.insert(user);
        ctx.status(HttpStatus.CREATED_201);
    };

    public static Handler returnUser = ctx -> {
        User potentialUser = ctx.bodyAsClass(User.class);
        User user = UserRepository.retrieve(potentialUser.getUsername());
        ctx.json(user);

    };
}