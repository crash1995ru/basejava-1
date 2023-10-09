package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int storageSize;

    private int findIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private void printErrorMessage(String uuid) {
        System.err.printf("Error: %s is not found \n", uuid);
    }

    public void save(Resume r) {
        if (size() == storage.length) {
            System.err.println("Error: Storage is full");
        }
        int index = findIndex(r.getUuid());
        if (index == -1) {
            storage[storageSize++] = r;
        } else {
            System.err.println("Error: The value is present in the list " + r.getUuid());
        }
    }

    public void update(Resume r, String uuid) {
        int index = findIndex(r.getUuid());
        if (index != -1) {
            storage[index].setUuid(uuid);
        } else {
            printErrorMessage(uuid);
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            printErrorMessage(uuid);
        }
        return null;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            storage[index] = storage[storageSize - 1];
            storage[storageSize - 1] = null;
            storageSize--;
        } else {
            printErrorMessage(uuid);
        }
    }

    public int size() {
        return storageSize;
    }

    public void clear() {
        Arrays.fill(storage, null);
        storageSize = 0;
    }
}
