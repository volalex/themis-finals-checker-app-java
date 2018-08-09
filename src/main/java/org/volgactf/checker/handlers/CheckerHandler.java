package org.volgactf.checker.handlers;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import org.volgactf.checker.dto.CheckerResult;
import org.volgactf.checker.dto.CheckerStatus;
import org.volgactf.checker.utils.ExSupplier;


public class CheckerHandler implements Handler<Future<CheckerResult>> {
    private final ExSupplier<CheckerResult> resultSupplier;

    public CheckerHandler(ExSupplier<CheckerResult> checkerResultSupplier) {
        this.resultSupplier = checkerResultSupplier;
    }

    @Override
    public void handle(Future<CheckerResult> event) {
        try {
            event.complete(resultSupplier.get());
        }catch (Exception e){
            //TODO: Add logging
            event.complete(new CheckerResult(CheckerStatus.INTERNAL_ERROR, null, null));
        }

    }
}
