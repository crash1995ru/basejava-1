package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int storageSize = 0;

    protected abstract int getIndex(String uuid);

    public abstract void save(Resume resume);


    public int size() {
        return storageSize;
    }

    public void update(Resume r, String uuid) {
        int index = getIndex(r.getUuid());
        if (index != -1) {
            storage[index].setUuid(uuid);
        } else {
            printErrorMessage(uuid);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    public void clear() {
        Arrays.fill(storage, null);
        storageSize = 0;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[storageSize - 1];
            storage[storageSize - 1] = null;
            storageSize--;
        } else {
            printErrorMessage(uuid);
        }
    }

    protected void printErrorMessage(String uuid) {
        System.err.printf("Error: %s is not found \n", uuid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractArrayStorage that = (AbstractArrayStorage) o;
        return storageSize == that.storageSize && Arrays.equals(storage, that.storage);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(storageSize);
        result = 31 * result + Arrays.hashCode(storage);
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(storage);
    }
}