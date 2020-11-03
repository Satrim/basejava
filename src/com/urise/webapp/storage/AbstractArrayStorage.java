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

    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) > -1) {
            System.out.println("Резюме " + resume.getUuid() + " уже существует");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("База резюме переполнена");
        }else if (resume.getUuid() == null) {
            System.out.println("Резюме задано некорректно");
        } else {
            checkSave(resume);
        }
    }

    public abstract void checkSave(Resume resume);

    protected abstract int getIndex(String uuid);
}
