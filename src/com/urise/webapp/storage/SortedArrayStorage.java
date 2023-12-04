package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void insertResume(Resume resume, int index) {
        int insertIndex = -index - 1;
            System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex - 1);
        }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertResume(Resume resume) {

    }

    @Override
    protected void removeResume(int index) {

    }
}