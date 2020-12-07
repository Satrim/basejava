package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorageTest extends AbstractArrayStorageTest {
    public ArrayStorageTest() {
        super(new ArrayStorage() {
            @Override
            public void saveResume(Resume resume, int index) {

            }

            @Override
            public void deleteResume(int index) {

            }

            @Override
            public void updateResume(Resume resume, int index) {

            }

            @Override
            public Resume getResume(int index) {
                return null;
            }
        });
    }
}