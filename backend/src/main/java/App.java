public class Application {

    // Declare dependencies
    public static TransactionDao transactionDao;
    public static UserDao userDao;

    public static void main(String[] args) {

        // Instantiate your dependencies
        bookDao = new BookDao();
        userDao = new UserDao();

        Javalin app = Javalin.create()
            .enableStaticFiles("/public", Location.CLASSPATH)
            .start(7000);

        app.routes(() -> {
            before(Filters.stripTrailingSlashes);
            before(LoginController.ensureLoginBeforeViewingAccounts);
            get(Path.Web.LOGIN, LoginController.serveLoginPage);
            post(Path.Web.LOGIN, LoginController.handleLoginPost);
            post(Path.Web.LOGOUT, LoginController.handleLogoutPost);
        });

        app.error(404, ViewUtil.notFound);
    }

}