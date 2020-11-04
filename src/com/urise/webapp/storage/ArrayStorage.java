package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    public void delResume(int index) {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }

    public void addResume(Resume resume, int index) {
        storage[size] = resume;
    }

    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
