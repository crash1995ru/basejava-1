package tests;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SortedArrayStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SortedArrayStorageTest {
    private static final SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();


    @Test
    void saveOrderedList() {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");

        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);

        SortedArrayStorage tempArray = new SortedArrayStorage();
        tempArray.save(r1);
        tempArray.save(r2);
        tempArray.save(r3);

        Assertions.assertArrayEquals(new SortedArrayStorage[]{tempArray}, new SortedArrayStorage[]{ARRAY_STORAGE});
    }
}