package com.urise.webapp.storage;

public abstract class AbstractStorage implements Storage {
    int size = 0;

    public int size() {
        return size;
    }
}
