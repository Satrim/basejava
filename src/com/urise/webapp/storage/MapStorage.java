package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private static Map<String, Resume> storage = new HashMap<>();
    private byte[] indexID = new byte[10_000];

    private int addID() {
        for (int i = 0; i < indexID.length; i++) {
            if (indexID[i] == 0) {
                indexID[i] = 1;
                return i;
            }
        }
        return -1;
    }

    private void delID(int id) {
        for (int i = 0; i < indexID.length; i++) {
            if (id == i) {
                indexID[i] = 0;
            }
        }
    }

    public int getIndex(String uuid) {
        for (Map.Entry<String, Resume> pair : storage.entrySet()) {
            if (pair.getKey().equals(uuid)) {
                return pair.getValue().getID();
            }
        }
        return -1;
    }

    public void saveResume(Resume resume, int index) {
        resume.setID(addID());
        storage.put(resume.getUuid(), resume);
    }

    public void deleteResume(int index) {
        for (Map.Entry<String, Resume> pair : storage.entrySet()) {
            if (pair.getValue().getID() == index) {
                storage.remove(pair.getKey());
                delID(index);
                break;
            }
        }
    }

    public void updateResume(Resume resume, int index) {
        for (Map.Entry<String, Resume> pair : storage.entrySet()) {
            if (resume.getUuid().equals(pair.getKey())) {
                Resume updateResume = new Resume(resume.getUuid());
                updateResume.setID(index);
                storage.put(resume.getUuid(), updateResume);
                break;
            }
        }
    }

    public Resume getResume(int index) {
        for (Map.Entry<String, Resume> pair : storage.entrySet()) {
            if (pair.getValue().getID() == index) {
                return pair.getValue();
            }
        }
        return null;
    }

    public void clear() {
        storage.clear();
        indexID = new byte[10_000];
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
