package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private static final Map<String, Resume> storage = new HashMap<>();

    public Object getKey(String uuid) {
        return storage.get(uuid);
    }

    public void saveResume(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    public void deleteResume(Object key) {
       storage.remove(key);
    }

    public void updateResume(Resume resume, Object key) {
        storage.put(resume.getUuid(), resume);
    }

    public Resume getResume(Object key) {
        return (Resume) key;
    }

    public void clear() {
        storage.clear();
    }

    public Resume[] getAll() {
        Resume[] resumes = storage.values().toArray(new Resume[0]);
        Arrays.sort(resumes);
        return resumes;
    }

    public int size() {
        return storage.size();
    }
}
