package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        storage.get(UUID_2);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void DeleteNotExistException() {
        storage.delete(UUID_1);
        storage.delete(UUID_1);
    }

    @Test(expected = StorageException.class)
    public void deleteNullUuid() {
        storage.delete(null);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_2);
        storage.update(newResume);
        Assert.assertSame(newResume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistException() {
        storage.update(new Resume("updateResume"));
    }

    @Test(expected = StorageException.class)
    public void updateNullUuid() {
        storage.update(new Resume(null));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] getAllResume = new Resume[3];
        getAllResume[0] = new Resume(UUID_1);
        getAllResume[1] = new Resume(UUID_2);
        getAllResume[2] = new Resume(UUID_3);
        Assert.assertArrayEquals(getAllResume, storage.getAll());
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_2), storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistException() {
        storage.get("getResume");
    }

    @Test(expected = StorageException.class)
    public void getNullUuid() {
        storage.get(null);
    }

    @Test
    public void save() {
        Resume resume = new Resume("saveResume");
        storage.save(resume);
        Assert.assertSame(resume, storage.get("saveResume"));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResumeException() {
        storage.save(new Resume(UUID_2));
    }

    @Test(expected = StorageException.class)
    public void saveNullUuid() {
        storage.save(new Resume(null));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        for (int i = storage.size(); i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
            try {
                storage.save(new Resume("uuid" + (i + 1)));
            } catch (StorageException e) {
                Assert.fail("переполнение произошло раньше времени");
            }
        }
        storage.save(new Resume("uuid_overflow"));
    }
}