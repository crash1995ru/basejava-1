package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int storageSize;
    private boolean isPresentUuid;

    public void clear() {
        Arrays.fill(storage, null);
        storageSize = 0;
    }

    public void update(Resume r, String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                r.setUuid(uuid);
                storage[i] = r;
            }
        }
        isPresent(uuid);
    }

    public void save(Resume r) {
        if (size() == storage.length) {
            System.err.println("Error: Storage is full");
        }
        boolean isFoundValue = false;
        if (!Objects.isNull(r)) {
            for (int i = 0; i < storageSize; i++) {
                if (storage[i].getUuid().equals(r.getUuid())) {
                    System.err.println("Error: The value is present in the list " + r.getUuid());
                    isFoundValue = true;
                    break;
                }
            }
            if (!isFoundValue) storage[storageSize++] = r;
        }
    }

    public Resume get(String uuid) {
        if (!Objects.isNull(uuid)) {
            for (int i = 0; i < storageSize; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    isPresentUuid = true;
                    return storage[i];
                }
            }
        }
        isPresent(uuid);
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[storageSize - 1];
                storage[storageSize - 1] = null;
                storageSize--;
                isPresentUuid = true;
                break;
            }
        }
        isPresent(uuid);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);

    }

    public int size() {
        return storageSize;
    }

    private void isPresent(String uuid) {
        if (!isPresentUuid) {
            System.err.printf("Error: %s is not found \n", uuid);
        }
    }
}
