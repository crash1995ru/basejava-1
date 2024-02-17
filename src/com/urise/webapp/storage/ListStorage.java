package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    protected Resume doGet(int searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void doUpdate(Resume resume, int searchKey) {
        storage.set(searchKey, resume);
    }

    @Override
    protected int getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (Objects.equals(storage.get(i).getUuid(), uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void doSave(Resume resume, int searchKey) {
        storage.add(resume);
    }

    @Override
    protected void doRemove(String uuid, int searchKey) {
        storage.remove(searchKey);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        List<Resume> storage1 = storage;
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
