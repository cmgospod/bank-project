package com.revature;

import io.javalin.Javalin;



public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins).start(8080);
        app.post();
        app.get();
    }



}
