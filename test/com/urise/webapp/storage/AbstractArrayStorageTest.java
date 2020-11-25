package com.urise.webapp.storage;

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
    private static final String UUID_4 = "uuid4";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
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

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_2);
        storage.update(newResume);
        Assert.assertSame(newResume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistResume() {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
//        Assert.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
//        Assert.assertEquals(new Resume(UUID_2), storage.get(UUID_2));
//        Assert.assertEquals(new Resume(UUID_3), storage.get(UUID_3));

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
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void save() {
        storage.save(new Resume(UUID_4));
        Assert.assertEquals(UUID_4, storage.get(UUID_4).getUuid());
        Assert.assertEquals(4, storage.size());
    }

    @Test (expected = NullPointerException.class)
    public void saveNull() {
        storage.save(null);
    }

    @Test(expected = StorageException.class)
    public void getOverflow() throws Exception {
        for (int i = storage.size(); i <AbstractArrayStorage.STORAGE_LIMIT ; i++) {
            try {
                storage.save(new Resume("uuid" + (i + 1)));
            } catch (StorageException e) {
                Assert.fail("переполнение произошло раньше времени");
            }
        }
        storage.save(new Resume("uuid_overflow"));
    }
}