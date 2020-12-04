package com.urise.webapp.storage;

import com.urise.webapp.exception.NameNullException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListStorageTest {
    AbstractStorage storage = new ListStorage();
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() {
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void saveResume() {
        Resume saveResume = new Resume("saveResume");
        storage.save(saveResume);
        assertEquals(saveResume, storage.get("saveResume"));
        assertEquals(4, storage.size());
    }

    @Test(expected = NameNullException.class)
    public void saveNull() {
        storage.save(null);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void getResume() {
        assertEquals(new Resume(UUID_2), storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistResume() {
        storage.get("NotExist");
    }

    @Test
    public void getAllResume() {
        //пока буду возвращать массив для MainArray
        Resume[] resumes = new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        assertArrayEquals(resumes, storage.getAll());
        assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteResume() {
        storage.delete(UUID_2);
        storage.get(UUID_2);
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void DeleteNotExistResume() {
        storage.delete(UUID_1);
        storage.delete(UUID_1);
    }

    @Test
    public void updateResume() {
        Resume newResume = new Resume(UUID_2);
        storage.update(newResume);
        Assert.assertSame(newResume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistResume() {
        storage.update(new Resume("updateResume"));
    }
}