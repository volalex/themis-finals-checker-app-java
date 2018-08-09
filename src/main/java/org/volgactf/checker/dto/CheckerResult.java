package org.volgactf.checker.dto;

public class CheckerResult {
    private final CheckerStatus checkerStatus;
    private final String adjunct;
    private final String message;

    public CheckerResult(CheckerStatus checkerStatus, String adjunct, String message) {
        this.checkerStatus = checkerStatus;
        this.adjunct = adjunct;
        this.message = message;
    }

    public CheckerStatus getCheckerStatus() {
        return checkerStatus;
    }

    public String getAdjunct() {
        return adjunct;
    }

    public String getMessage() {
        return message;
    }
}
