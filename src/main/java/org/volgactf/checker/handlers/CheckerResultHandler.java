package org.volgactf.checker.handlers;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import org.volgactf.checker.dto.CheckerResult;
import org.volgactf.checker.dto.CheckerStatus;

import java.util.function.Consumer;

public class CheckerResultHandler implements Handler<AsyncResult<CheckerResult>> {
    private final Consumer<CheckerResult> checkerResultConsumer;

    public CheckerResultHandler(Consumer<CheckerResult> checkerResultConsumer) {
        this.checkerResultConsumer = checkerResultConsumer;
    }

    @Override
    public void handle(AsyncResult<CheckerResult> event) {
        if (event.succeeded()){
            checkerResultConsumer.accept(event.result());
        }
        else{
            checkerResultConsumer.accept(new CheckerResult(CheckerStatus.INTERNAL_ERROR, null, null));
        }
    }

}
