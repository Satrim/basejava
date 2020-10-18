import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;
    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                Arrays.fill(storage, 0, i - 1, null);
                break;
            }
        }
    }

    void save(Resume r) {
        if (r.uuid == null || storage[storage.length - 1] != null) {
            return;
        }
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                for (int j = 0; j < i; j++) {
                    if (storage[j].uuid.equals(r.uuid)) {
                        System.out.println("Резюме с именем " + r.uuid + " уже существует");
                        return;
                    }
                }
                storage[i] = r;
                size++;
                break;
            }
        }
    }

    Resume get(String uuid) {
        int index = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return null;
            }
            if (uuid.equals(storage[i].uuid)) {
                index = i;
                break;
            }
        }
        return storage[index];
    }

    void delete(String uuid) {
        int index = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                index = i;
                break;
            }
        }

        if (index == storage.length - 1) {
            storage[index] = null;
        } else {
            for (int i = index + 1; i < storage.length; i++) {
                if (storage[i] == null) {
                    size = i - 1;
                    break;
                }
                storage[i - 1] = storage[i];
                storage[i] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int index = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                index++;
            } else {
                break;
            }
        }
        return Arrays.copyOf(storage, index);
    }

    int size() {
        return size;
    }
}
