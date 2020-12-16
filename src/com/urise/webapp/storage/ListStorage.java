package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private static List<Resume> storage = new ArrayList<>();

    public int size() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
    }

    public void updateResume(Resume resume, Object index) {
        storage.set((int) index, resume);
    }

    public void saveResume(Resume resume, Object index) {
        storage.add(resume);
    }

    public Object getKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public Resume getResume(Object index) {
        return storage.get((int) index);
    }

    public void deleteResume(Object index) {
        storage.remove((int) index);
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }
}
