package org.volgactf.checker;

import org.volgactf.checker.dto.CheckerData;
import org.volgactf.checker.dto.CheckerResult;

public class Checker {
    public Checker() {
    }

    public CheckerResult push(CheckerData checkerData) throws Exception {
        Thread.sleep(3000);
        throw new IllegalStateException("Not implemented");
    }

    public CheckerResult pull(CheckerData checkerData) throws Exception {
        Thread.sleep(3000);
        throw new IllegalStateException("Not implemented");
    }
}
