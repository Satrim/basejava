package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NameNullException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private static List<Resume> storage = new ArrayList<>();
    public int size() {
        return storage.size();
    }

    public void clear() {
        storage.removeAll(storage);
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage.remove(index);
            storage.add(index, resume);
            System.out.println("Резюме " + storage.get(index).getUuid() + " обновлено");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            throw new ExistStorageException(resume.getUuid());
        } else if (resume.getUuid() == null) {
            throw new NameNullException();
        } else {
            storage.add(resume);
        }
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage.get(index);
        }
        throw new NotExistStorageException(uuid);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            storage.remove(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Resume[] getAll() {
        Resume[] allResume = new Resume[storage.size()];
        for (int i = 0; i < allResume.length; i++) {
            allResume[i] = storage.get(i);
        }
        return allResume;
    }
}
