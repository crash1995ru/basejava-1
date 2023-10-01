import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int storageSize = 0;

    void clear() {
        storage = new Resume[10000];
        storageSize = 0;
    }

    void save(Resume r) {
        System.arraycopy(storage, 0, storage, 1, size());
        storage[0] = r;
        storageSize++;
    }

    Resume get(String uuid) {
        Resume rsl = new Resume();
        try {
            if (!Objects.isNull(uuid)) {
                for (Resume res : storage) {
                    if (res.uuid.equals(uuid)) {
                        rsl = res;
                        break;
                    }
                }
            }
        } catch (NullPointerException NPE) {
            System.err.println("UUID не найден");
        }
        return rsl;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                move(i);
                storageSize--;
                break;
            }
        }
        try {
            throw new IllegalArgumentException("UUiD не найден");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    void move(int index) {
        for (int i = index; i < size() - 1; i++) {
            storage[i] = storage[i + 1];
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
