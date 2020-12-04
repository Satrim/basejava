package com.urise.webapp.exception;

public class NameNullException extends RuntimeException {

    public NameNullException() {
        super("Имя задано некорректно");
    }

}
