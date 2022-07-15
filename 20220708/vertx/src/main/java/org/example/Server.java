package org.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class Server extends AbstractVerticle {
    public void start() {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());

        router.get("/").handler(ctx -> {
            ctx.response().end("Hello, world!\n");
        });

        router.post("/echo").handler(ctx -> {
            String body = ctx.body().asString();
            ctx.response().end(body + "\n");
        });

        server.requestHandler(router).listen(3000);
    }
}
