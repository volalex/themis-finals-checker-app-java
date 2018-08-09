package org.volgactf.checker.utils;

@FunctionalInterface
public interface ExSupplier<T> {
    T get() throws Exception;
}
