package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void update(Resume r) {

    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size == 0) {
            storage[0] = r;
            size++;
        } else if (index > -1) {
            System.out.println("Такое резюме " + r.getUuid() + " уже существует");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("База резюме заполнена");
        } else {
            index = (index * -1) - 1;
            if (size - index >= 0) System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
