package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
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

    public void updateResume(Resume resume, int index) {
        storage.remove(index);
        storage.add(index, resume);
    }

    public void saveResume(Resume resume, int index) {
        if (resume.getUuid() == null) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage.add(resume);
        }
    }

    public int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public Resume getResume(int index) {
        return storage.get(index);
    }

    public void deleteResume(int index) {
        storage.remove(index);
    }

    public Resume[] getAll() {
        Resume[] allResume = new Resume[storage.size()];
        for (int i = 0; i < allResume.length; i++) {
            allResume[i] = storage.get(i);
        }
        return allResume;
    }
}
