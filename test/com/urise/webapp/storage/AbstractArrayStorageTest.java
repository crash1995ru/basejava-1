package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import org.junit.Assert;
import org.junit.Before;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final Resume UUID_ONE = new Resume("uuid_1");
    private static final Resume UUID_TWO = new Resume("uuid_2");
    private static final Resume UUID_THREE = new Resume("uuid_3");
    private static final Resume UUID_FOUR = new Resume("uuid_4");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(UUID_ONE);
        storage.save(UUID_TWO);
        storage.save(UUID_THREE);
    }

    @org.junit.Test
    public void save() {
        storage.save(UUID_FOUR);
        Assert.assertEquals(UUID_FOUR, storage.get(UUID_FOUR.getUuid()));
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void saveExistElement() {
        storage.save(UUID_TWO);
    }

    @org.junit.Test
    public void delete() {
        int expected = 2;
        storage.delete(UUID_THREE.getUuid());
        Assert.assertEquals(expected, storage.size());
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void deleteNotExistElement() {
        storage.delete(UUID_FOUR.getUuid());
    }


    @org.junit.Test
    public void size() {
        int expected = 3;
        Assert.assertEquals(expected, storage.size());
    }

    @org.junit.Test
    public void update() {
        storage.update(UUID_THREE, UUID_FOUR.getUuid());
        Resume actual = storage.get("uuid4");
        Assert.assertEquals(UUID_FOUR, actual);
    }

    @org.junit.Test
    public void get() {
        Assert.assertEquals(UUID_ONE, storage.get(UUID_ONE.getUuid()));
    }

    @org.junit.Test
    public void getAll() {
        Resume[] list = storage.getAll();
        Resume[] expected = new Resume[]{UUID_ONE, UUID_TWO, UUID_THREE};
        Assert.assertArrayEquals(expected, list);
    }

    @org.junit.Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }
}