package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        if (storageSize + 1 < STORAGE_LIMIT) {
            int indexSave = getIndexSave(r);
            for (int i = storageSize; i >= indexSave; i--) {
                storage[i + 1] = storage[i];
            }
            storage[indexSave] = r;
            storageSize++;
        } else {
            System.err.println("Error: The value is present in the list " + r.getUuid());
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, storageSize, searchKey);
    }

    private int getIndexSave(Resume resume) {
        for (int i = 0; i < storageSize; i++) {
            if (resume.getUuid().compareTo(storage[i].getUuid()) < 0) {
                return i;
            }
        }
        return storageSize;
    }
}