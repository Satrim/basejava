package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    private void checkNull(String uuid) {
        if (uuid == null) {
            throw new StorageException("Имя задано некоректно", uuid);
        }
    }

    public void save(Resume resume) {
        checkNull(resume.getUuid());
        Object searchKey = getKey(resume.getUuid());
        if (searchKey instanceof Integer) {
            if ((int) searchKey > -1) {
                throw new ExistStorageException(resume.getUuid());
            }
            saveResume(resume, searchKey);
        }
        saveResume(resume, searchKey);
    }

    public void delete(String uuid) {
        checkNull(uuid);
        Object searchKey = getKey(uuid);
        if (searchKey instanceof Integer) {
            if ((int) searchKey == -1) {
                throw new NotExistStorageException(uuid);
            }
            deleteResume(searchKey);
        }
        deleteResume(uuid);
    }

    public void update(Resume resume) {
        checkNull(resume.getUuid());
        Object searchKey = getKey(resume.getUuid());
        if (searchKey instanceof Integer) {
            if ((int) searchKey == -1) {
                throw new ExistStorageException(resume.getUuid());
            }
            updateResume(resume, searchKey);
            System.out.println("Резюме " + resume.getUuid() + " обновлено");
        }
        updateResume(resume, searchKey);
    }

    public Resume get(String uuid) {
        checkNull(uuid);
        Object searchKey = getKey(uuid);
        if (searchKey instanceof Integer) {
            if ((int) searchKey == -1) {
                throw new NotExistStorageException(uuid);
            }
            return getResume(searchKey);
        }
        return getResume(searchKey);
    }

    public abstract Object getKey(String uuid);

    public abstract void saveResume(Resume resume, Object key);

    public abstract void deleteResume(Object key);

    public abstract void updateResume(Resume resume, Object key);

    public abstract Resume getResume(Object Key);
}
