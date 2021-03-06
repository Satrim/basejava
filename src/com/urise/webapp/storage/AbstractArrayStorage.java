package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

abstract public class AbstractArrayStorage extends AbstractStorage {
    static final int STORAGE_LIMIT = 5;
    Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    public int size() {
        return size;
    }

    public void deleteResume(Object index) {
        if ((int) index == STORAGE_LIMIT - 1) {
            storage[STORAGE_LIMIT - 1] = null;
            size--;
            return;
        }
        delResume((int) index);
        storage[size - 1] = null;
        size--;
    }

    public void updateResume(Resume resume, Object index) {
        storage[(int) index] = resume;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public Resume getResume(Object index) {
        return storage[(int) index];
    }

    public void saveResume(Resume resume, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("База резюме переполнена ", resume.getUuid());
        } else {
            addResume(resume, (int) index);
            size++;
        }
    }

    public abstract void delResume(int index);

    public abstract void addResume(Resume resume, int index);

    public abstract Object getKey(String uuid);
}
