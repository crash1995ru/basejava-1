package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public abstract class AbstractArrayStorageTest {

    private static final int INITIAL_SIZE = 3;
    private static final String UUID_1 = "uuid_1";
    private static final String UUID_2 = "uuid_2";
    private static final String UUID_3 = "uuid_3";
    private static final String UUID_4 = "uuid_4";
    private static final Resume resume1 = new Resume(UUID_1);
    private static final Resume resume2 = new Resume(UUID_2);
    private static final Resume resume3 = new Resume(UUID_3);
    private static final Resume resume4 = new Resume(UUID_4);

    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws ExistStorageException, StorageException {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void save() throws ExistStorageException, StorageException, NotExistStorageException {
        storage.save(resume4);
        assertGet(resume4);
        assertSize(INITIAL_SIZE + 1);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws ExistStorageException, StorageException {
        storage.save(resume2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistElement() throws NotExistStorageException {
        storage.delete(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws NotExistStorageException {
        storage.delete(UUID_3);
        assertSize(INITIAL_SIZE - 1);
        assertGet(resume3);
    }

    @Test
    public void size() {
        assertSize(INITIAL_SIZE);
    }

    @Test
    public void update() throws NotExistStorageException {
        Resume expected = new Resume(UUID_3);
        storage.update(expected);

        Resume actual = storage.get(UUID_3);
        Assert.assertSame(expected, actual);
    }

    @Test
    public void get() throws NotExistStorageException {
        Resume actual = storage.get(UUID_3);
        Assert.assertSame(resume3  , actual);
    }

    @Test
    public void getAll() {
        Resume[] actual = storage.getAll();
        Resume[] expected = new Resume[]{resume1, resume2, resume3};
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public final void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws ExistStorageException, StorageException {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }

        storage.save(new Resume());
    }

    private void assertGet(Resume resume) throws NotExistStorageException {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSize(int expected) {
        Assert.assertEquals(expected, storage.size());
    }
}