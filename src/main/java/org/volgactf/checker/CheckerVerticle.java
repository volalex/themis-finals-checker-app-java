package org.volgactf.checker;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.*;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.ext.web.handler.BasicAuthHandler;
import io.vertx.ext.web.handler.BodyHandler;
import org.volgactf.checker.dto.CheckerData;
import org.volgactf.checker.handlers.CheckerHandler;
import org.volgactf.checker.handlers.CheckerResultHandler;

public class CheckerVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        final AppProperties appProperties = new AppProperties();
        final Vertx vertx = getVertx();
        WebClientOptions webClientOptions = new WebClientOptions()
                .setConnectTimeout(10000)
                .setIdleTimeout(10000);
        final WebClient client = WebClient.create(vertx,webClientOptions);
        final HttpServer server = vertx.createHttpServer();
        final Router router = Router.router(vertx);
        final Checker checker = new Checker();

        AuthHandler authHandler = BasicAuthHandler.create((authInfo, resultHandler) -> {
            if (authInfo.getString("username").equals(appProperties.getCheckerUsername()) &&
                    appProperties.getCheckerPassword().equals(authInfo.getString("password"))){
                resultHandler.handle(Future.succeededFuture(new User()));
            }
            else{
                resultHandler.handle(Future.failedFuture("Unauthorized"));
            }
        });


        router.route("/*").handler(authHandler);
        router.route("/*").handler(BodyHandler.create());
        router.post("/push").handler(ctx->{
            if (ctx.user()!=null){
                CheckerData checkerData = CheckerData.fromJson(ctx.getBodyAsJson());
                vertx.executeBlocking(
                        new CheckerHandler(()->checker.push(checkerData)),
                        false,
                        new CheckerResultHandler((result)-> client.postAbs(checkerData.reportUrl).putHeader(HttpHeaders.AUTHORIZATION.toString(),
                                "Basic "+appProperties.getMasterBasicAuth())
                                .sendJsonObject(new JsonObject()
                                        .put("status",result.getCheckerStatus().getCode())
                                        .put("flag",checkerData.params.flag)
                                        .put("label",result.getAdjunct())
                                        .put("message",result.getMessage()), (res)-> System.out.println(res.succeeded() + res.cause().toString()))));
                ctx.response().setStatusCode(HttpResponseStatus.ACCEPTED.code()).end();
            }
            else{
                ctx.response().setStatusCode(HttpResponseStatus.UNAUTHORIZED.code()).end(HttpResponseStatus.UNAUTHORIZED.reasonPhrase());
            }
        });

        server.requestHandler(router::accept).listen(3333);
    }

    public static class User implements io.vertx.ext.auth.User {
        @Override
        public io.vertx.ext.auth.User isAuthorized(String authority, Handler<AsyncResult<Boolean>> resultHandler) {
            return this;
        }

        @Override
        public io.vertx.ext.auth.User clearCache() {
            return this;
        }

        @Override
        public JsonObject principal() {
            return null;
        }

        @Override
        public void setAuthProvider(AuthProvider authProvider) {
        }
    }
}
