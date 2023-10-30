package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.err.println("Error: Storage is full");
        }
        int index = getIndex(r.getUuid());
        if (index == -1) {
            storage[size++] = r;
        } else {
            System.err.println("Error: The value is present in the list " + r.getUuid());
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            printErrorMessage(uuid);
        }
        return null;
    }
}