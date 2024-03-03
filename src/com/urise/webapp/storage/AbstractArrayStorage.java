package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public final void doSave(Resume resume, Object searchKey) throws StorageException {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Хранилище переполненно");
        }
        insertResume(resume, (Integer) searchKey);
        size++;
    }

    public void doRemove(String uuid, Object index) {
        removeResume((Integer) index);
        storage[size] = null;
        size--;
    }

    @Override
    protected boolean isExist(Object key) {
        return (int) key >= 0;
    }

    public final void doUpdate(Resume resume, Object searchKey) {
        storage[(int) searchKey] = resume;
    }

    public final Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }


    public final int size() {
        return size;
    }
    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final Object getSearchKey(String uuid) {
        return getIndex(uuid);
    }


    @Override
    public String toString() {
        return Arrays.toString(storage);
    }

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void removeResume(int index);

    protected abstract int getIndex(String uuid);


}
