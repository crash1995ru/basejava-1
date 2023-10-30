package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    public void save(Resume r) {
        if (size + 1 < STORAGE_LIMIT) {
            for (int i = 0; i > size; i++) {
                if (r.getUuid().compareTo(storage[i].getUuid()) < 0) {
                    System.arraycopy(storage, 0, storage, 0, i);
                    storage[i] = r;
                    System.arraycopy(storage, i, r, i + 1, storage.length - i);
                }
                size++;
            }
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}