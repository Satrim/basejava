package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

abstract public class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 5;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            if (index == STORAGE_LIMIT - 1) {
                storage[STORAGE_LIMIT - 1] = null;
                size--;
                return;
            }

            for (int i = index + 1; i < size; i++) {
                storage[i - 1] = storage[i];
            }
            size--;
        } else {
            System.out.println("Резюме " + uuid + " не найдено");
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
            System.out.println("Резюме " + storage[index].getUuid() + " обновлено");
        } else {
            System.out.println("Резюме с именем " + resume.getUuid() + " не найдено");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме " + uuid + " не существует");
            return null;
        }
        return storage[index];
    }

    public boolean checkIndex(Resume r) {

        if (size >= STORAGE_LIMIT) {
            System.out.println("База резюме переполнена");
            return false;
        }

        if (r.getUuid() == null) {
            System.out.println("Резюме задано некорректно");
            return false;
        }
        return true;
    }

    public abstract void save(Resume r);

    protected abstract int getIndex(String uuid);
}
