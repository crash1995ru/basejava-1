package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public interface Storage {

    void clear();

    void update(Resume r) throws NotExistStorageException;

    void save(Resume r) throws StorageException, ExistStorageException;

    Resume get(String uuid) throws NotExistStorageException;

    void delete(String uuid) throws NotExistStorageException;

    Resume[] getAll();

    int size();
}