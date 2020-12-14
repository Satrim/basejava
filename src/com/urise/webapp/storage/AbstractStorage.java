package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        if (resume.getUuid() == null) {
            throw new StorageException("Имя задано некоректно", resume.getUuid());
        }
        int searchKey = getKey(resume.getUuid());
        if (searchKey > -1) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveResume(resume, searchKey);
        }
    }

    public void delete(String uuid) {
        if (uuid == null) {
            throw new StorageException("Имя задано некоректно", uuid);
        }
        int searchKey = getKey(uuid);
        if (searchKey > -1) {
            deleteResume(searchKey);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void update(Resume resume) {
        if (resume.getUuid() == null) {
            throw new StorageException("Имя задано некоректно", resume.getUuid());
        }
        int searchKey = getKey(resume.getUuid());
        if (searchKey > -1) {
            updateResume(resume, searchKey);
            System.out.println("Резюме " + resume.getUuid() + " обновлено");
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public Resume get(String uuid) {
        if (uuid == null) {
            throw new StorageException("Имя задано некоректно", uuid);
        }
        int searchKey = getKey(uuid);
        if (searchKey > -1) {
            return getResume(searchKey);
        }
        throw new NotExistStorageException(uuid);
    }

    public abstract int getKey(String uuid);

    public abstract void saveResume(Resume resume, int index);

    public abstract void deleteResume(int index);

    public abstract void updateResume(Resume resume, int index);

    public abstract Resume getResume(int index);
}
