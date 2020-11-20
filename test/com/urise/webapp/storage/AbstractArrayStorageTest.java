package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {

    private static final int STORAGE_LIMIT = 10_000;
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
        storage.delete("uuid2");
        storage.get("uuid2");
    }

    @Test
    public void update() {
        Resume newResume = new Resume("uuid2");
        storage.update(newResume);
        Assert.assertSame(newResume, storage.get("uuid2"));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Assert.assertEquals(storage.get("uuid1").getUuid(), "uuid1");
        Assert.assertEquals(storage.get("uuid2").getUuid(), "uuid2");
        Assert.assertEquals(storage.get("uuid3").getUuid(), "uuid3");
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(UUID_2, storage.get("uuid2").getUuid());
    }

    @Test
    public void save() {
        Resume r = new Resume("uuid4");
        storage.save(r);
        Assert.assertEquals("uuid4", storage.get("uuid4").getUuid());
        // Или так? Как правильней? Или оба варианта не правильно?

        Storage TestStorage = new ArrayStorage();
        TestStorage.save(new Resume("uuid1"));
        TestStorage.save(new Resume("uuid2"));
        TestStorage.save(new Resume("uuid3"));
        TestStorage.save(new Resume("uuid4"));
        Assert.assertArrayEquals(TestStorage.getAll(), storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("asdf");

    }

    @Test(expected = StorageException.class)
    public void getOverflow() throws Exception {
        int i = storage.size();
        for (; i < STORAGE_LIMIT; i++) {
            storage.save(new Resume("uuid" + (i + 1)));
        }
        storage.save(new Resume("uuid6"));
        if (storage.size() <= STORAGE_LIMIT) {
            Assert.fail("переполнение произошло раньше времени");
        }
    }

}