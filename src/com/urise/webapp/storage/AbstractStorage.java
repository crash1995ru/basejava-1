package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public final void save(Resume resume) throws StorageException, ExistStorageException {
        int searchKey = getSearchKey(resume.getUuid());
        if (searchKey >= 0) {
            throw new ExistStorageException("Резюме присутствует в хранилище.");
        }
        doSave(resume, searchKey);
    }

    public final void delete(String uuid) throws NotExistStorageException {
        doRemove(uuid, isNotExist(uuid));
    }

    public final void update(Resume resume) throws NotExistStorageException {
        doUpdate(resume, isNotExist(resume.getUuid()));
    }

    public final Resume get(String uuid) throws NotExistStorageException {
        return doGet(isNotExist(uuid));
    }

    private int isNotExist(String uuid) throws NotExistStorageException {
        int searchKey = getSearchKey(uuid);
        if (searchKey < 0) {
            throw new NotExistStorageException("Resume not exist");
        }
        return searchKey;
    }

    protected abstract Resume doGet(int searchKey);
    protected abstract void doUpdate(Resume resume, int searchKey);

    protected abstract int getSearchKey(String uuid);
    protected abstract void doSave(Resume resume, int searchKey) throws StorageException;
    protected abstract void doRemove(String uuid, int searchKey);
}