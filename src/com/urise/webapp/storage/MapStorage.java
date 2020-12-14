package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private static Map<String, Resume> storage = new HashMap<>();

     public int getKey(String uuid) {

        return -1;
    }

    public void saveResume(Resume resume, int index) {

    }

    public void deleteResume(int index) {

    }

    public void updateResume(Resume resume, int index) {

    }

    public Resume getResume(int index) {

        return null;
    }

    public void clear() {

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
