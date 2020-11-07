package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Такого резюме " + uuid + " не существует", uuid);
    }
}
