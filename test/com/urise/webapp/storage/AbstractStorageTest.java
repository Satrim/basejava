package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AbstractStorageTest {

    Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }


    @Before
    public void setUp() {
        storage.clear();
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

    @Test(expected = ExistStorageException.class)
    public void saveExistResumeException() {
        storage.save(new Resume(UUID_2));
    }

    @Test(expected = StorageException.class)
    public void saveNullUuid() {
        storage.save(new Resume(null));
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
        Resume getResume = new Resume(UUID_2);
        assertEquals(getResume, storage.get(UUID_2));
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
    public void getAllResume() {
        Resume resume1 = new Resume(UUID_1);
        Resume resume2 = new Resume(UUID_2);
        Resume resume3 = new Resume(UUID_3);
        Resume[] resumes = new Resume[]{resume1, resume2, resume3};
        Arrays.sort(resumes);
        assertArrayEquals(resumes, storage.getAll());
        assertEquals(3, storage.size());
    }

    @Test
    public void deleteResume() {
        storage.delete(UUID_2);
        assertEquals(2, storage.size());
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
    public void updateResume() {
        Resume updateResume = new Resume(UUID_2);
//        updateResume.setID(1);
        storage.update(updateResume);
        assertEquals(updateResume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistException() {
        storage.update(new Resume("updateResume"));
    }

    @Test(expected = StorageException.class)
    public void updateNullUuid() {
        storage.update(new Resume(null));
    }
}
