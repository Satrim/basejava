package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void checkSave(Resume r) {
        int index = getIndex(r.getUuid());
            index = (index * -1) - 1;
            for (int i = size; i > index; i--) {
                storage[i] = storage[i - 1];
            }
            storage[index] = r;
            size++;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
