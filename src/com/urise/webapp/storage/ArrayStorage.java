package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            System.out.println("Резюме с именем " + resume.getUuid() + " не найдено");
        }
    }

    @Override
    public void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("База резюме переполнена");
            return;
        }

        if (resume.getUuid() == null) {
            System.out.println("Резюме задано некорректно");
            return;
        }

        if (getIndex(resume.getUuid()) == -1) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Резюме " + resume.getUuid() + " уже существует");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        System.out.println("index " + index);
        if (index > -1) {
            if (index == storage.length - 1) {
                storage[storage.length - 1] = null;
                size--;
                return;
            }

            if (size - index + 1 >= 0) System.arraycopy(storage, index + 1, storage, index, size - index + 1);
            size--;
        } else {
            System.out.println("Резюме " + uuid + " не найдено");
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

//    public Resume get(String uuid) {
//        int index = getIndex(uuid);
//        if (index > -1) {
//            return storage[index];
//        }
//        System.out.println("Резюме " + uuid + " не найдено");
//        return null;
//    }
//
//    public int size() {
//        return size;
//    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
