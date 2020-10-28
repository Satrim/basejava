package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

abstract public class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме " + uuid + " не существует");
            return null;
        }
        return storage[index];
    }

    public abstract void clear();

    public abstract void update(Resume r);

    public abstract void save(Resume r);

    public abstract void delete(String uuid);

    public abstract Resume[] getAll();

    protected abstract int getIndex(String uuid);

    // Не разобрался, что с этим делать

//    protected void noNameMethod(Resume r, String uuid) {
//        clear();
//        update(r);
//        save(r);
//        delete(uuid);
//        getAll();
//        getIndex(uuid);
//        size();
//        get(uuid);
//    }

}
