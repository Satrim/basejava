package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = searchResume(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            System.out.println("Резюме не найдено");
        }
    }

    public void save(Resume r) {
        if (size >= storage.length) {
            System.out.println("База резюме переполнена");
            return;
        }

        if (r.getUuid() == null) {
            System.out.println("Резюме задано некорректно");
            return;
        }

        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                System.out.println("Такое резюме уже существует");
                return;
            }
        }
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        int index = searchResume(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            System.out.println("Резюме не найдено");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = searchResume(uuid);
        if (index > -1) {
            if (index == storage.length - 1) {
                storage[storage.length - 1] = null;
                size--;
                return;
            }
            if (size - index + 1 >= 0) System.arraycopy(storage, index + 1, storage, index, size - index + 1);
            size--;
        } else {
            System.out.println("Резюме не найдено");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int searchResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
