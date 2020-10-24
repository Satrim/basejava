package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(String uuid) throws IOException {
        int index = numResume(uuid);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        if (index > -1) {
            System.out.print("Введите новое имя: ");
            storage[index].setUuid(reader.readLine());
        } else {
            System.out.println("ERROR");
        }
    }

    public void save(Resume r) {
        if (r.getUuid() == null || size >= storage.length) {
            System.out.println("ERROR");
            return;
        }

        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                return;
            }
        }
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        if (numResume(uuid) > -1) {
            return storage[numResume(uuid)];
        } else return null;
    }

    public void delete(String uuid) {
        int index = numResume(uuid);
        if (index > -1) {
            if (index == storage.length - 1) {
                storage[storage.length - 1] = null;
                size--;
                return;
            }
            for (int i = index + 1; i < size; i++) {
                storage[i - 1] = storage[i];
            }
            size--;
        } else {
            System.out.println("ERROR");
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

    int numResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
