package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void save(Resume resume) {
        if (size + 1 > STORAGE_LIMIT) {
            throw new IllegalArgumentException("Хранилище переполненно");
        } else if (getIndex(resume.getUuid()) > 0) {
            throw new IllegalArgumentException("Резюме присутствует в хранилище.");
        }
        insertResume(resume, getIndex(resume.getUuid()));
        size++;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new IllegalArgumentException("UUID не найден");
        } else {
            removeResume(index);
            size--;
        }
    }


    public int size() {
        return size;
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
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    protected void printErrorMessage(String uuid) {
        System.err.printf("Error: %s is not found \n", uuid);
    }

    @Override
    public String toString() {
        return Arrays.toString(storage);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void removeResume(int index);

}

