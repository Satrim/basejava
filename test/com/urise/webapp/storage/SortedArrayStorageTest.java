package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage() {
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