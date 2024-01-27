package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void save(Resume resume) throws StorageException, ExistStorageException {
        int index = getIndex(resume.getUuid());
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Хранилище переполненно");
        } else if (index >= 0) {
            throw new ExistStorageException("Резюме присутствует в хранилище.");
        }
        insertResume(resume, index);
        size++;
    }

    public final void delete(String uuid) throws NotExistStorageException {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException("UUID не найден");
        }
        removeResume(index);
        storage[size] = null;
        size--;
    }

    public final int size() {
        return size;
    }

    public final void update(Resume resume) throws NotExistStorageException {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException("Resume not exist");
        }
        storage[index] = resume;
    }

    public final Resume get(String uuid) throws NotExistStorageException {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException("Resume not exist");
        }
        return storage[index];
    }

    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(storage);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void removeResume(int index);

}