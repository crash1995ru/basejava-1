import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int storageSize = 0;

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
        storageSize = 0;
    }

    void save(Resume r) {
        if (!Objects.isNull(r)) {
            storage[storageSize++] = r;
        }
    }

    Resume get(String uuid) {
        if (!Objects.isNull(uuid)) {
            for (int iter = 0; iter < storageSize; iter++) {
                if (storage[iter].uuid.equals(uuid)) {
                    return storage[iter];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[storageSize - 1];
                storage[storageSize - 1] = null;
                storageSize--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);

    }

    int size() {
        return storageSize;
    }
}
