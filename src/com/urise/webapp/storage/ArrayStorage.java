package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume r) {
        if (checkIndex(r)) {
            if (getIndex(r.getUuid()) == -1) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Резюме " + r.getUuid() + " уже существует");
            }
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
