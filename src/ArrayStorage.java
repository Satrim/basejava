import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (r.uuid == null || storage[storage.length - 1] != null) {
            return;
        }

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                return;
            }
        }
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                index = i;
                break;
            }
            if (i == size - 1 && !storage[i].uuid.equals(uuid)) {
                return null;
            }
        }
        return storage[index];
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                if (i == storage.length - 1) {
                    storage[storage.length - 1] = null;
                    size--;
                    return;
                }
                for (int j = i + 1; j < size; j++) {
                    storage[j - 1] = storage[j];
                    storage[j] = null;
                }
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
