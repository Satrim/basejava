package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

abstract public class AbstractArrayStorage extends AbstractStorage {
    static final int STORAGE_LIMIT = 10_000;
    Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    public int size() {
        return size;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            if (index == STORAGE_LIMIT - 1) {
                storage[STORAGE_LIMIT - 1] = null;
                size--;
                return;
            }
            delResume(index);
            storage[size - 1] = null;
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
            System.out.println("Резюме " + storage[index].getUuid() + " обновлено");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            throw new ExistStorageException(resume.getUuid());
        } else if (size >= STORAGE_LIMIT) {
            throw new StorageException("База резюме переполнена ", resume.getUuid());
        } else {
            addResume(resume, index);
            size++;
        }
    }

    public abstract void delResume(int index);

    public abstract void addResume(Resume resume, int index);

    public abstract int getIndex(String uuid);
}
