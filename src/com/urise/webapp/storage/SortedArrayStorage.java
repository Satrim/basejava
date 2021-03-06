package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void delResume(int index) {
        if (size - index + 1 >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size - index + 1);
        }
    }

    public void addResume(Resume resume, int index) {
        index = -index - 1;
        if (size - index >= 0) {
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        storage[index] = resume;
    }

    public Object getKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
