package org.volgactf.checker.dto;

public enum CheckerStatus {
    UP(101),
    CORRUPT(102),
    MUMBLE(103),
    DOWN(104),
    INTERNAL_ERROR(110);

    private int code;

    CheckerStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
